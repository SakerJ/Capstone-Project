package com.houlin.capstone_project.view.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.houlin.capstone_project.R;
import com.houlin.capstone_project.adapter.ComingAdapter;
import com.houlin.capstone_project.adapter.HotAdapter;
import com.houlin.capstone_project.adapter.TopAdapter;
import com.houlin.capstone_project.adapter.UsAdapter;
import com.houlin.capstone_project.model.bean.InTheaters;
import com.houlin.capstone_project.model.bean.Top250;
import com.houlin.capstone_project.model.bean.UsBox;
import com.houlin.capstone_project.presenter.contract.HomePresenter;
import com.houlin.capstone_project.presenter.implement.HomePresenterImpl;
import com.houlin.capstone_project.utils.RecyclerUtils;
import com.houlin.capstone_project.utils.ScreenUtils;
import com.houlin.capstone_project.view.contract.HomeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements HomeView {

    private final int RECYCLERVIEW_SPACE = 10;// RecyclerView间距

    @BindView(R.id.rv_hot)
    RecyclerView mRvHot;
    @BindView(R.id.rv_coming)
    RecyclerView mRvComing;
    @BindView(R.id.rv_us)
    RecyclerView mRvUs;
    @BindView(R.id.rv_top)
    RecyclerView mRvTop;

    private HomePresenter mPresenter;
    private HotAdapter mHotAdapter;
    private ComingAdapter mComingAdapter;
    private UsAdapter mUsAdapter;
    private TopAdapter mTopAdapter;

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

        // 正在热映RecyclerView
        mRvHot.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        // RecyclerView的item间距
        mRvHot.addItemDecoration(
                new RecyclerUtils.SpaceItemDecoration((int) ScreenUtils.dp2px(RECYCLERVIEW_SPACE)));
        mHotAdapter = new HotAdapter(this);
        mRvHot.setAdapter(mHotAdapter);

        // 即将上映RecyclerView
        mRvComing.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        mRvComing.addItemDecoration(
                new RecyclerUtils.SpaceItemDecoration((int) ScreenUtils.dp2px(RECYCLERVIEW_SPACE)));
        mComingAdapter = new ComingAdapter(this);
        mRvComing.setAdapter(mComingAdapter);

        // 北美票房榜RecyclerView
        mRvUs.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        mRvUs.addItemDecoration(
                new RecyclerUtils.SpaceItemDecoration((int) ScreenUtils.dp2px(RECYCLERVIEW_SPACE)));
        mUsAdapter = new UsAdapter(this);
        mRvUs.setAdapter(mUsAdapter);

        // Top250RecyclerView
        mRvTop.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        mRvTop.addItemDecoration(
                new RecyclerUtils.SpaceItemDecoration((int) ScreenUtils.dp2px(RECYCLERVIEW_SPACE)));
        mTopAdapter = new TopAdapter(this);
        mRvTop.setAdapter(mTopAdapter);

        mPresenter = new HomePresenterImpl();
        mPresenter.attach(this);
        mPresenter.getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detach();
    }

    @Override
    public void showHot(InTheaters inTheaters) {
        mHotAdapter.getList().clear();
        mHotAdapter.getList().addAll(inTheaters.getSubjects());
        mHotAdapter.notifyDataSetChanged();
    }

    @Override
    public void showComing(InTheaters inTheaters) {
        mComingAdapter.getSubjectsBeans().clear();
        mComingAdapter.getSubjectsBeans().addAll(inTheaters.getSubjects());
        mComingAdapter.notifyDataSetChanged();
    }

    @Override
    public void showUsBox(UsBox usBox) {
        mUsAdapter.getList().clear();
        mUsAdapter.getList().addAll(usBox.getSubjects());
        mUsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTop250(Top250 top250) {
        mTopAdapter.getList().clear();
        mTopAdapter.getList().addAll(top250.getSubjects());
        mTopAdapter.notifyDataSetChanged();
    }

}
