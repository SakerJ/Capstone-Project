package com.houlin.capstone_project.top;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.houlin.capstone_project.BaseFragment;
import com.houlin.capstone_project.R;
import com.houlin.capstone_project.adapter.TopAdapter;
import com.houlin.capstone_project.data.bean.Top250;
import com.houlin.capstone_project.ui.custom.LoadingFrameLayout;
import com.houlin.capstone_project.ui.custom.MyRecyclerView;
import com.houlin.capstone_project.utils.RecyclerUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopFragment extends BaseFragment implements TopContract.View {

    private static final String TAG = "TopFragment";

    @BindView(R.id.rv_show)
    MyRecyclerView mMyRecyclerView;
    @BindView(R.id.loading)
    LoadingFrameLayout mLoadingFrameLayout;

    private RecyclerView mRecyclerView;
    private TopContract.Presenter mPresenter;
    private TopAdapter mAdapter;

    public TopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
        mPresenter = new TopPresenter();
        mPresenter.attach(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detach();
    }

    private void init() {
        mRecyclerView = mMyRecyclerView.getRecyclerView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new RecyclerUtils.DividerItemDecoration());
        mAdapter = new TopAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mMyRecyclerView.setRefreshListener(new MyRecyclerView.RefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getData(0, true);
            }
        });
        // 上拉加载
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    // 加载更多
                    mPresenter.getData(mAdapter.getList().size(), false);
                }
            }
        });
    }

    @Override
    public void showTop(Top250 top250, boolean isRefresh) {
        if (isRefresh) {// 刷新操作才清空数据
            mAdapter.getList().clear();
        }
        mAdapter.getList().addAll(top250.getSubjects());
        mAdapter.notifyDataSetChanged();

        mMyRecyclerView.refreshComplete();
    }

    @Override
    public void loadFinish() {
        mAdapter.setLoading(false);
    }

    @Override
    public void showContent() {
        mLoadingFrameLayout.showContent();
    }

    @Override
    protected void initData() {
        mPresenter.getData(mAdapter.getList().size(), false);
        mLoadingFrameLayout.showLoading();
    }
}
