package com.houlin.capstone_project.ui.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * 用于轮播的ViewPager
 *
 * @author houlin
 */

public class PlayViewPager extends ViewPager {

    private static final String TAG = "LoopViewPager";

    private int loopTime = 3000;
    private LoopRunnable mLoopRunnable;
    private boolean isPlay;// 是否轮播
    private boolean isUser;// 上一个动作是否来自用户

    public PlayViewPager(Context context) {
        this(context, null);
    }

    public PlayViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public int getLoopTime() {
        return loopTime;
    }

    public void setLoopTime(int loopTime) {
        this.loopTime = loopTime;
    }

    private void init() {
        mLoopRunnable = new LoopRunnable();

        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (isPlay) {
                    if (state == SCROLL_STATE_DRAGGING) {// 用户拖拽时暂停轮播
                        pause();
                        isUser = true;
                    } else if (isUser && state == SCROLL_STATE_IDLE) {// 之前如果有用户动作，则重新轮播
                        start();
                        isUser = false;
                    }
                }
            }
        });
    }

    // 当view或view的祖先的可见性发生改变时回调
    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (isPlay) {
            if (visibility != VISIBLE) {// 当轮播状态且view不可见时，暂停轮播
                pause();
            } else {// 当轮播状态且view变为可见时，继续轮播
                start();
            }
        }
    }

    /**
     * 开始轮播：轮播状态置为true
     */
    public void start() {
        pause();// 防止Runnable重复
        postDelayed(mLoopRunnable, loopTime);
        isPlay = true;
    }

    /**
     * 暂停轮播：清空Runnable
     */
    public void pause() {
        removeCallbacks(mLoopRunnable);
    }

    /**
     * 停止轮播：轮播状态置为false
     */
    public void stop() {
        pause();
        isPlay = false;
    }

    private class LoopRunnable implements Runnable {

        @Override
        public void run() {
            setCurrentItem(getCurrentItem() + 1);
            postDelayed(this, loopTime);
        }
    }

}
