package com.houlin.capstone_project.presenter.implement;

import com.houlin.capstone_project.BaseView;
import com.houlin.capstone_project.R;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author houlin
 */

public class HomePresenterImpl implements HomePresenter, HotListener, ComingListener, UsBoxListener, Top250Listener {

    private HomeView mHomeView;
    private HomeModel mHomeModel;

    private boolean getHot;
    private boolean getComing;
    private boolean getUs;
    private boolean getTop;
    private boolean getBanner;

    public HomePresenterImpl() {
        mHomeModel = new HomeModelImpl();
    }

    @Override
    public void attach(BaseView view) {
        mHomeView = (HomeView) view;
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
    public void onTopResponse(Top250 top250) {
        mHomeView.showTop250(top250);
        getTop = true;
        showContent();
    }

    @Override
    public void onTopFailure() {
        mHomeView.showError();
    }
}
