package com.houlin.capstone_project.home;

import com.houlin.capstone_project.BaseView;
import com.houlin.capstone_project.R;
import com.houlin.capstone_project.listener.ComingListener;
import com.houlin.capstone_project.listener.HotListener;
import com.houlin.capstone_project.listener.Top250Listener;
import com.houlin.capstone_project.listener.UsBoxListener;
import com.houlin.capstone_project.data.bean.InTheaters;
import com.houlin.capstone_project.data.bean.Top250;
import com.houlin.capstone_project.data.bean.UsBox;

import java.util.ArrayList;
import java.util.List;

/**
 * @author houlin
 */

public class HomePresenter implements HomeContract.Presenter, HotListener, ComingListener, UsBoxListener, Top250Listener {

    private HomeContract.View mHomeView;
    private HomeContract.Model mHomeModel;

    private boolean getHot;
    private boolean getComing;
    private boolean getUs;
    private boolean getTop;
    private boolean getBanner;

    public HomePresenter() {
        mHomeModel = new HomeModel();
    }

    @Override
    public void attach(BaseView view) {
        mHomeView = (HomeContract.View) view;
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
        getBanner();
    }

    private void getBanner() {
        List<Integer> resIds = new ArrayList<>();
        resIds.add(R.drawable.home_banner_1);
        resIds.add(R.drawable.home_banner_2);
        resIds.add(R.drawable.home_banner_3);
        resIds.add(R.drawable.home_banner_4);
        resIds.add(R.drawable.home_banner_5);
        mHomeView.showBanner(resIds);
        getBanner = true;
        showContent();
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

    private void showContent() {
        if (getHot && getComing && getUs && getTop && getBanner) {
            mHomeView.showContent();
        }
    }

    // HotListener---------------

    @Override
    public void onResponse(InTheaters inTheaters) {
        // TODO: 2017/12/11 view可能为null
        mHomeView.showHot(inTheaters);
        getHot = true;
        showContent();
    }

    @Override
    public void onFailure() {
        // TODO: 2017/12/25 view可能为null
        mHomeView.showError();
    }

    // ComingListener----------------

    @Override
    public void onComingResponse(InTheaters inTheaters) {
        mHomeView.showComing(inTheaters);
        getComing = true;
        showContent();
    }

    @Override
    public void onComingFailure() {
        mHomeView.showError();
    }

    // UsBoxListener--------------

    @Override
    public void onUsResponse(UsBox usBox) {
        mHomeView.showUsBox(usBox);
        getUs = true;
        showContent();
    }

    @Override
    public void onUsFailure() {
        mHomeView.showError();
    }

    // Top250Listener--------------

    @Override
    public void onTopResponse(Top250 top250, boolean isRefresh) {
        mHomeView.showTop250(top250);
        getTop = true;
        showContent();
    }

    @Override
    public void onTopFailure() {
        mHomeView.showError();
    }
}
