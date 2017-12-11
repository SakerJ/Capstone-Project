package com.houlin.capstone_project.presenter.contract;

import com.houlin.capstone_project.view.contract.HomeView;

/**
 * Created by houlin on 2017/12/7.
 */

public interface HomePresenter {

    void attach(HomeView homeView);

    void detach();

    void getData();
}
