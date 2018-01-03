package com.houlin.capstone_project.us;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.houlin.capstone_project.BaseFragment;
import com.houlin.capstone_project.R;
import com.houlin.capstone_project.adapter.UsAdapter;
import com.houlin.capstone_project.data.bean.UsBox;
import com.houlin.capstone_project.ui.custom.LoadingFrameLayout;
import com.houlin.capstone_project.ui.custom.MyRecyclerView;
import com.houlin.capstone_project.utils.RecyclerUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsFragment extends BaseFragment implements UsContract.View {

    private static final String TAG = "UsFragment";

    @BindView(R.id.rv_show)
    MyRecyclerView mMyRecyclerView;
    @BindView(R.id.loading)
    LoadingFrameLayout mLoadingFrameLayout;

    private RecyclerView mRecyclerView;
    private UsContract.Presenter mUsPresenter;
    private UsAdapter mUsAdapter;

    public UsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_us, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
        mUsPresenter = new UsPresenter();
        mUsPresenter.attach(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUsPresenter.detach();
    }

    private void init() {
        mRecyclerView = mMyRecyclerView.getRecyclerView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new RecyclerUtils.DividerItemDecoration());
        mUsAdapter = new UsAdapter(this);
        mRecyclerView.setAdapter(mUsAdapter);
        mMyRecyclerView.setRefreshListener(new MyRecyclerView.RefreshListener() {
            @Override
            public void onRefresh() {
                mUsPresenter.getData();
            }
        });
    }

    @Override
    public void showUs(UsBox usBox) {
        mUsAdapter.getList().clear();
        mUsAdapter.getList().addAll(usBox.getSubjects());
        mUsAdapter.notifyDataSetChanged();

        mMyRecyclerView.refreshComplete();
    }

    @Override
    public void showContent() {
        mLoadingFrameLayout.showContent();
    }

    @Override
    protected void initData() {
        mUsPresenter.getData();
        mLoadingFrameLayout.showLoading();
    }
}
