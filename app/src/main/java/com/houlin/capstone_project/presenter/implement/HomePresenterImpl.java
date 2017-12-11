package com.houlin.capstone_project.presenter.implement;

import com.houlin.capstone_project.listener.ComingListener;
import com.houlin.capstone_project.listener.HotListener;
import com.houlin.capstone_project.listener.Top250Listener;
import com.houlin.capstone_project.listener.UsBoxListener;
import com.houlin.capstone_project.model.bean.InTheaters;
import com.houlin.capstone_project.model.bean.Top250;
import com.houlin.capstone_project.model.bean.UsBox;
import com.houlin.capstone_project.model.contract.HomeModel;
import com.houlin.capstone_project.model.implement.HomeModelImpl;
import com.houlin.capstone_project.presenter.contract.HomePresenter;
import com.houlin.capstone_project.view.contract.HomeView;

/**
 * Created by houlin on 2017/12/7.
 */

public class HomePresenterImpl implements HomePresenter, HotListener, ComingListener, UsBoxListener, Top250Listener {

    private HomeView mHomeView;
    private HomeModel mHomeModel;

    public HomePresenterImpl() {
        mHomeModel = new HomeModelImpl();
    }

    @Override
    public void attach(HomeView homeView) {
        mHomeView = homeView;
    }

    @Override
    public void detach() {
        mHomeView = null;
    }

    @Override
    public void getData() {
        getHot();
        getComing();
        getUs();
        getTop250();
    }

    private void getTop250() {
        mHomeModel.getTop250(this);
    }

    private void getUs() {
        mHomeModel.getUs(this);
    }

    private void getHot() {
        mHomeModel.getHot(this);
    }

    private void getComing() {
        mHomeModel.getComing(this);
    }

    // HotListener---------------

    @Override
    public void onResponse(InTheaters inTheaters) {
        // TODO: 2017/12/11 view可能为null
        mHomeView.showHot(inTheaters);
    }

    @Override
    public void onFailure() {

    }

    // ComingListener----------------

    @Override
    public void onComingResponse(InTheaters inTheaters) {
        mHomeView.showComing(inTheaters);
    }

    @Override
    public void onComingFailure() {

    }

    // UsBoxListener--------------

    @Override
    public void onUsResponse(UsBox usBox) {
        mHomeView.showUsBox(usBox);
    }

    @Override
    public void onUsFailure() {

    }

    // Top250Listener--------------

    @Override
    public void onTopResponse(Top250 top250) {
        mHomeView.showTop250(top250);
    }

    @Override
    public void onTopFailure() {

    }
}
