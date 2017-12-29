package com.houlin.capstone_project.ui.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.houlin.capstone_project.R;

/**
 * 带header和footer的RecyclerView
 *
 * @author houlin
 */

public class MyRecyclerView extends LinearLayout {

    private static final String TAG = "MyRecyclerView";

    private final int STATE_HIDE = 0;
    private final int STATE_PULL_TO_REFRESH = 1;
    private final int STATE_RELEASE_TO_REFRESH = 2;
    private final int STATE_REFRESHING = 3;
    private int curState = STATE_HIDE;

    private RecyclerView mRecyclerView;
    private LinearLayout mHeader;
    private TextView mTextView;

    private boolean isHeaderPull;
    private float downY;
    private boolean isLoad;
    private int headerHideHeight;
    private MarginLayoutParams mHeaderLayoutParams;
    private RefreshListener mRefreshListener;

    public MyRecyclerView(Context context) {
        this(context, null);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initEvent();
        resetHeader();
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setRefreshListener(RefreshListener refreshListener) {
        mRefreshListener = refreshListener;
    }

    private void initEvent() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 如果RecyclerView滑动到顶部，header便可滑动
                isHeaderPull = !recyclerView.canScrollVertically(-1);
                if (!recyclerView.canScrollVertically(1)) {
                    MarginLayoutParams params = (MarginLayoutParams) recyclerView.getLayoutParams();
                    Log.d(TAG, "onScrolled: " + params.topMargin);
                }
            }
        });
    }

    private void initView() {
        LayoutInflater.from(getContext())
                .inflate(R.layout.layout_recyclerview, this, true);
        mRecyclerView = findViewById(R.id.rv_recycler);
        mHeader = findViewById(R.id.header_recycler);
        mTextView = findViewById(R.id.tv_text);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = ev.getY() - downY;
                // 向下滑动且header可以下拉
                if (dy > 0 && isHeaderPull) {
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dy = event.getY() - downY;
                downY = event.getY();
                if (curState != STATE_REFRESHING) {
                    mHeaderLayoutParams.topMargin += dy / 2;
                }
                // 如果header完全显示后，状态将置为RELEASE_TO_REFRESH
                if (mHeaderLayoutParams.topMargin > 0) {
                    curState = STATE_RELEASE_TO_REFRESH;
                    mTextView.setText("释放刷新");
                } else {
                    mTextView.setText("下拉刷新");
                    curState = STATE_PULL_TO_REFRESH;
                }
                if (mHeaderLayoutParams.topMargin < headerHideHeight || !isHeaderPull) {
                    mHeaderLayoutParams.topMargin = headerHideHeight;
                    mRecyclerView.onTouchEvent(event);
                }
                mHeader.requestLayout();
                break;
            case MotionEvent.ACTION_UP:
                if (curState == STATE_PULL_TO_REFRESH) {
                    // 复原header
                    headerAnimate(null, 250, mHeaderLayoutParams.topMargin, headerHideHeight);
                    curState = STATE_HIDE;
                } else if (curState == STATE_RELEASE_TO_REFRESH) {
                    // 刷新
                    mTextView.setText("刷新中");
                    headerAnimate(null, 250, mHeaderLayoutParams.topMargin, 0);
                    if (mRefreshListener != null) {
                        mRefreshListener.onRefresh();
                    }
                    curState = STATE_REFRESHING;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed && !isLoad) {
            headerHideHeight = -mHeader.getHeight();
            mHeaderLayoutParams = (MarginLayoutParams) mHeader.getLayoutParams();
            // 隐藏header，设置header的topMargin
            mHeaderLayoutParams.topMargin = headerHideHeight;
            mHeader.requestLayout();
            isLoad = true;
        }
    }

    public void refreshComplete() {
        if (mHeaderLayoutParams == null) {
            return;
        }
        post(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("刷新成功");
                // 恢复header位置和header中的view
                headerAnimate(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        // 恢复header中的view
                        resetHeader();
                    }
                }, 500, mHeaderLayoutParams.topMargin, headerHideHeight);
                curState = STATE_HIDE;
            }
        });
    }

    private void resetHeader() {
        mTextView.setText("下拉刷新");
    }

    private void headerAnimate(final AnimatorListenerAdapter listener, final int duration, final int... progress) {
        // 由于动画操作的是topMargin，既不是view的属性，也没有setter方法。所有需要使用ValueAnimator
        ValueAnimator animator = ValueAnimator.ofInt(progress);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mHeaderLayoutParams.topMargin = (int) animation.getAnimatedValue();
                mHeader.requestLayout();
            }
        });
        if (listener != null) {
            animator.addListener(listener);
        }
        animator.setDuration(duration).start();
    }

    public interface RefreshListener {
        void onRefresh();
    }
}
