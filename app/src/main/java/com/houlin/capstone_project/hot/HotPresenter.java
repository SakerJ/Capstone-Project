package com.houlin.capstone_project.hot;

import com.houlin.capstone_project.BaseView;
import com.houlin.capstone_project.listener.HotListener;
import com.houlin.capstone_project.data.bean.InTheaters;

/**
 * @author houlin
 */

public class HotPresenter implements HotContract.Presenter, HotListener {

    private HotContract.Model mHotModel;
    private HotContract.View mHotView;

    public HotPresenter() {
        mHotModel = new HotModel();
    }

    @Override
    public void attach(BaseView view) {
        mHotView = (HotContract.View) view;
    }

    @Override
    public void detach() {
        mHotView = null;
    }

    @Override
    public void getData() {
        mHotModel.getHot(this);
    }

    // HotListener------------------------

    @Override
    public void onResponse(InTheaters body) {
        mHotView.showHot(body);
    }

    @Override
    public void onFailure() {

    }
}
