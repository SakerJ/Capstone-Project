package com.houlin.capstone_project.view.ui.fragment;

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
import com.houlin.capstone_project.model.bean.InTheaters;
import com.houlin.capstone_project.presenter.contract.HotPresenter;
import com.houlin.capstone_project.presenter.implement.HotPresenterImpl;
import com.houlin.capstone_project.utils.RecyclerUtils;
import com.houlin.capstone_project.view.contract.HotView;
import com.houlin.capstone_project.view.ui.custom.MyRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotFragment extends BaseFragment implements HotView {

    @BindView(R.id.rv_show)
    MyRecyclerView mMyRecyclerView;

    private RecyclerView mRecyclerView;
    private HotAdapter mHotAdapter;
    private HotPresenter mHotPresenter;

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

        mRecyclerView = mMyRecyclerView.getRecyclerView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new RecyclerUtils.DividerItemDecoration());
        mHotAdapter = new HotAdapter(this);
        mRecyclerView.setAdapter(mHotAdapter);

        mHotPresenter = new HotPresenterImpl();
        mHotPresenter.attach(this);
        mHotPresenter.getData();

        mMyRecyclerView.setRefreshListener(new MyRecyclerView.RefreshListener() {
            @Override
            public void onRefresh() {
                mHotPresenter.getData();
            }
        });
//        mMyRecyclerView.setRefreshListener(new MyRecyclerView.RefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Thread() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        mMyRecyclerView.refreshComplete();
//                    }
//                }.start();
//            }
//        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHotPresenter.detach();
    }

    @Override
    public void showHot(InTheaters inTheaters) {
        mHotAdapter.getList().clear();
        mHotAdapter.getList().addAll(inTheaters.getSubjects());
        mHotAdapter.notifyDataSetChanged();

        mMyRecyclerView.refreshComplete();
    }
}
