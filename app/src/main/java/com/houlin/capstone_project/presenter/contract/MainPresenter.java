package com.houlin.capstone_project.presenter.contract;

import android.support.v7.app.AppCompatActivity;

import com.houlin.capstone_project.view.contract.MainView;

/**
 * Created by houlin on 2017/12/6.
 */

public interface MainPresenter {

    void switchFragment(AppCompatActivity activity, int id);

    void attach(MainView mainView);

    void detach();
}
