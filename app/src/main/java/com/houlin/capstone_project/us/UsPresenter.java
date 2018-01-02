package com.houlin.capstone_project.us;

import com.houlin.capstone_project.BaseView;
import com.houlin.capstone_project.data.bean.UsBox;
import com.houlin.capstone_project.listener.UsBoxListener;

/**
 * @author houlin
 */

public class UsPresenter implements UsContract.Presenter, UsBoxListener {

    private UsContract.Model mModel;
    private UsContract.View mView;

    public UsPresenter() {
        mModel = new UsModel();
    }

    @Override
    public void attach(BaseView view) {
        mView = (UsContract.View) view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public void getData() {
        mModel.getUs(this);
    }

    // UsBoxListener------------------------

    @Override
    public void onUsResponse(UsBox body) {
        mView.showUs(body);
        mView.showContent();
    }

    @Override
    public void onUsFailure() {

    }
}
