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
    public void getData() {
        mModel.getTop(0, 30, this);
    }

    // Top250Listener----------------------

    @Override
    public void onTopResponse(Top250 top250) {
        mView.showTop(top250);
    }

    @Override
    public void onTopFailure() {

    }
}
