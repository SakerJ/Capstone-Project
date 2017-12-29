package com.houlin.capstone_project.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.houlin.capstone_project.BaseFragment;
import com.houlin.capstone_project.R;
import com.houlin.capstone_project.adapter.HomeComingAdapter;
import com.houlin.capstone_project.adapter.HomeHotAdapter;
import com.houlin.capstone_project.adapter.HomePagerAdapter;
import com.houlin.capstone_project.adapter.HomeTopAdapter;
import com.houlin.capstone_project.adapter.HomeUsAdapter;
import com.houlin.capstone_project.data.bean.InTheaters;
import com.houlin.capstone_project.data.bean.Top250;
import com.houlin.capstone_project.data.bean.UsBox;
import com.houlin.capstone_project.ui.activity.MainActivity;
import com.houlin.capstone_project.ui.custom.LoadingFrameLayout;
import com.houlin.capstone_project.ui.custom.PlayViewPager;
import com.houlin.capstone_project.utils.RecyclerUtils;
import com.houlin.capstone_project.utils.ScreenUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements HomeContract.View {

    private static final String TAG = "HomeFragment";
    private final int RECYCLERVIEW_SPACE = 10;// RecyclerView间距dp
    private final int BANNER_TIME = 3000;// banner轮播间隔

    @BindView(R.id.rv_hot)
    RecyclerView mRvHot;
    @BindView(R.id.rv_coming)
    RecyclerView mRvComing;
    @BindView(R.id.rv_us)
    RecyclerView mRvUs;
    @BindView(R.id.rv_top)
    RecyclerView mRvTop;
    @BindView(R.id.loading)
    LoadingFrameLayout mLoadingFrameLayout;
    @BindView(R.id.vp_banner)
    PlayViewPager mVpBanner;
    @BindView(R.id.ll_banner)
    LinearLayout mLlBanner;

    private HomeContract.Presenter mPresenter;
    private HomeHotAdapter mHomeHotAdapter;
    private HomeComingAdapter mHomeComingAdapter;
    private HomeUsAdapter mHomeUsAdapter;
    private HomeTopAdapter mHomeTopAdapter;
    private HomePagerAdapter mHomePagerAdapter;

    private int curBannerPosition;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
        mPresenter = new HomePresenter();
        mPresenter.attach(this);

        mPresenter.getData();
        mLoadingFrameLayout.showLoading();
    }

    private void init() {
        // 正在热映RecyclerView
        mRvHot.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        // RecyclerView的item间距
        mRvHot.addItemDecoration(
                new RecyclerUtils.SpaceItemDecoration((int) ScreenUtils.dp2px(RECYCLERVIEW_SPACE)));
        mHomeHotAdapter = new HomeHotAdapter(this);
        mRvHot.setAdapter(mHomeHotAdapter);

        // 即将上映RecyclerView
        mRvComing.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        mRvComing.addItemDecoration(
                new RecyclerUtils.SpaceItemDecoration((int) ScreenUtils.dp2px(RECYCLERVIEW_SPACE)));
        mHomeComingAdapter = new HomeComingAdapter(this);
        mRvComing.setAdapter(mHomeComingAdapter);

        // 北美票房榜RecyclerView
        mRvUs.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        mRvUs.addItemDecoration(
                new RecyclerUtils.SpaceItemDecoration((int) ScreenUtils.dp2px(RECYCLERVIEW_SPACE)));
        mHomeUsAdapter = new HomeUsAdapter(this);
        mRvUs.setAdapter(mHomeUsAdapter);

        // Top250RecyclerView
        mRvTop.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        mRvTop.addItemDecoration(
                new RecyclerUtils.SpaceItemDecoration((int) ScreenUtils.dp2px(RECYCLERVIEW_SPACE)));
        mHomeTopAdapter = new HomeTopAdapter(this);
        mRvTop.setAdapter(mHomeTopAdapter);

        // banner
        mHomePagerAdapter = new HomePagerAdapter();
        mVpBanner.setAdapter(mHomePagerAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detach();
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacksAndMessages(null);
    }

    @OnClick({R.id.rl_hot, R.id.rl_coming, R.id.rl_us, R.id.rl_top})
    void click(View view) {
        MainActivity activity = (MainActivity) getActivity();
        switch (view.getId()) {
            case R.id.rl_hot:
                // TODO: 2017/12/13 传参
                activity.checkRadioButton(MainActivity.HOT);
                break;
            case R.id.rl_coming:
                // TODO: 2017/12/13 传参
                activity.checkRadioButton(MainActivity.HOT);
                break;
            case R.id.rl_us:
                activity.checkRadioButton(MainActivity.US);
                break;
            case R.id.rl_top:
                activity.checkRadioButton(MainActivity.TOP);
                break;
        }
    }

    @Override
    public void showHot(InTheaters inTheaters) {
        mHomeHotAdapter.getList().clear();
        mHomeHotAdapter.getList().addAll(inTheaters.getSubjects());
        mHomeHotAdapter.notifyDataSetChanged();
        mLoadingFrameLayout.showContent();
    }

    @Override
    public void showComing(InTheaters inTheaters) {
        mHomeComingAdapter.getSubjectsBeans().clear();
        mHomeComingAdapter.getSubjectsBeans().addAll(inTheaters.getSubjects());
        mHomeComingAdapter.notifyDataSetChanged();
    }

    @Override
    public void showUsBox(UsBox usBox) {
        mHomeUsAdapter.getList().clear();
        mHomeUsAdapter.getList().addAll(usBox.getSubjects());
        mHomeUsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTop250(Top250 top250) {
        mHomeTopAdapter.getList().clear();
        mHomeTopAdapter.getList().addAll(top250.getSubjects());
        mHomeTopAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBanner(List<Integer> resIds) {
        mHomePagerAdapter.getList().clear();
        mHomePagerAdapter.getList().addAll(resIds);
        mHomePagerAdapter.notifyDataSetChanged();
        // 指示器
        mLlBanner.removeAllViews();
        int size = resIds.size();
        for (int i = 0; i < size; i++) {
            ImageView dot = new ImageView(getContext());
            if (i == curBannerPosition) {
                dot.setImageResource(R.drawable.dot_selected);
            } else {
                dot.setImageResource(R.drawable.dot_default);
            }
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i == size - 1) {
                layoutParams.rightMargin = 30;
            } else {
                layoutParams.rightMargin = 10;
            }
            dot.setLayoutParams(layoutParams);
            mLlBanner.addView(dot);
        }

        mVpBanner.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
//                super.onPageSelected(position);
                position %= mHomePagerAdapter.getList().size();// 求无限轮播的真正position
                curBannerPosition = position;
                for (int i = 0; i < mLlBanner.getChildCount(); i++) {
                    ImageView dot = (ImageView) mLlBanner.getChildAt(i);
                    if (i == position) {
                        dot.setImageResource(R.drawable.dot_selected);
                    } else {
                        dot.setImageResource(R.drawable.dot_default);
                    }
                }
            }
        });
        // 设置ViewPager初始位置
        mVpBanner.setCurrentItem(1000 * mHomePagerAdapter.getList().size(),
                false);

        mVpBanner.start();
    }

    private void pagerPlay() {
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mVpBanner.setCurrentItem(mVpBanner.getCurrentItem() + 1);
                mHandler.postDelayed(this, BANNER_TIME);
            }
        }, BANNER_TIME);
    }

    private Handler mHandler = new Handler();

    @Override
    public void showError() {
        mLoadingFrameLayout.showError();
    }

    @Override
    public void showContent() {
        mLoadingFrameLayout.showContent();
    }

}
