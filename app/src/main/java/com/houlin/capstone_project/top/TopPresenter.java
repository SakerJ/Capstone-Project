package com.houlin.capstone_project.top;

import com.houlin.capstone_project.BaseView;
import com.houlin.capstone_project.data.bean.Top250;
import com.houlin.capstone_project.listener.Top250Listener;

/**
 * @author houlin
 */

public class TopPresenter implements TopContract.Presenter, Top250Listener {

    private TopContract.Model mModel;
    private TopContract.View mView;

    private final int GET_COUNT = 30;
    private boolean isLoading;// 避免重复请求数据

    public TopPresenter() {
        mModel = new TopModel();
    }

    @Override
    public void attach(BaseView view) {
        mView = (TopContract.View) view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public void getData(int start, boolean isRefresh) {
        if (!isLoading) {
            isLoading = true;
            mModel.getTop(start, GET_COUNT, isRefresh, this);
        }
    }

    // Top250Listener----------------------

    @Override
    public void onTopResponse(Top250 top250, boolean isRefresh) {
        isLoading = false;
        if (top250.getSubjects().size() < GET_COUNT) {
            mView.loadFinish();
        }
        mView.showTop(top250, isRefresh);
        mView.showContent();
    }

    @Override
    public void onTopFailure() {

    }
}
