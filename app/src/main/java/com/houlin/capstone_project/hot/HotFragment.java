package com.houlin.capstone_project.hot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.houlin.capstone_project.BaseFragment;
import com.houlin.capstone_project.R;
import com.houlin.capstone_project.adapter.HotAdapter;
import com.houlin.capstone_project.data.bean.InTheaters;
import com.houlin.capstone_project.ui.custom.LoadingFrameLayout;
import com.houlin.capstone_project.ui.custom.MyRecyclerView;
import com.houlin.capstone_project.utils.RecyclerUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotFragment extends BaseFragment implements HotContract.View {

    @BindView(R.id.rv_show)
    MyRecyclerView mMyRecyclerView;
    @BindView(R.id.loading)
    LoadingFrameLayout mLoadingFrameLayout;

    private RecyclerView mRecyclerView;
    private HotAdapter mHotAdapter;
    private HotContract.Presenter mHotPresenter;

    public HotFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
        mHotPresenter = new HotPresenter();
        mHotPresenter.attach(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHotPresenter.detach();
    }

    private void init() {
        mRecyclerView = mMyRecyclerView.getRecyclerView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new RecyclerUtils.DividerItemDecoration());
        mHotAdapter = new HotAdapter(this);
        mRecyclerView.setAdapter(mHotAdapter);
        mMyRecyclerView.setRefreshListener(new MyRecyclerView.RefreshListener() {
            @Override
            public void onRefresh() {
                mHotPresenter.getData();
            }
        });
    }

    @Override
    public void showHot(InTheaters inTheaters) {
        mHotAdapter.getList().clear();
        mHotAdapter.getList().addAll(inTheaters.getSubjects());
        mHotAdapter.notifyDataSetChanged();

        mMyRecyclerView.refreshComplete();
    }

    @Override
    public void showContent() {
        mLoadingFrameLayout.showContent();
    }

    @Override
    protected void initData() {
        mHotPresenter.getData();
        mLoadingFrameLayout.showLoading();
    }
}
