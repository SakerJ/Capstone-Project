package com.houlin.capstone_project.detail;

import com.houlin.capstone_project.BaseView;
import com.houlin.capstone_project.data.bean.Detail;
import com.houlin.capstone_project.listener.DetailListener;

/**
 * @author houlin
 */

public class DetailPresenter implements DetailContract.Presenter, DetailListener {

    private DetailContract.Model mModel;
    private DetailContract.View mView;

    public DetailPresenter() {
        mModel = new DetailModel();
    }

    @Override
    public void attach(BaseView view) {
        mView = (DetailContract.View) view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public void initData(String id) {
        mModel.getDetail(id, this);
    }

    // DetailListener-------------

    @Override
    public void onResponse(Detail body) {
        mView.showData(body);
        mView.showContent();
    }

    @Override
    public void onFailure() {

    }
}
