package com.houlin.capstone_project.presenter.implement;

import com.houlin.capstone_project.BaseView;
import com.houlin.capstone_project.listener.HotListener;
import com.houlin.capstone_project.model.bean.InTheaters;
import com.houlin.capstone_project.model.contract.HotModel;
import com.houlin.capstone_project.model.implement.HotModelImpl;
import com.houlin.capstone_project.presenter.contract.HotPresenter;
import com.houlin.capstone_project.view.contract.HotView;

/**
 * @author houlin
 */

public class HotPresenterImpl implements HotPresenter, HotListener {

    private HotModel mHotModel;
    private HotView mHotView;

    public HotPresenterImpl() {
        mHotModel = new HotModelImpl();
    }

    @Override
    public void attach(BaseView view) {
        mHotView = (HotView) view;
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
