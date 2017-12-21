package com.houlin.capstone_project.presenter.implement;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.houlin.capstone_project.R;
import com.houlin.capstone_project.presenter.contract.MainPresenter;
import com.houlin.capstone_project.view.contract.MainView;
import com.houlin.capstone_project.view.ui.fragment.HomeFragment;
import com.houlin.capstone_project.view.ui.fragment.HotFragment;
import com.houlin.capstone_project.view.ui.fragment.UsFragment;
import com.houlin.capstone_project.view.ui.fragment.TopFragment;

/**
 * Created by houlin on 2017/12/4.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    public void attach(MainView mainView) {
        mMainView = mainView;
    }

    public void detach() {
        mMainView = null;
    }

    public void switchFragment(AppCompatActivity activity, int id) {

        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        switch (id) {
            case R.id.rb_home:
                transaction.replace(R.id.fl_container, new HomeFragment());
                break;
            case R.id.rb_us:
                transaction.replace(R.id.fl_container, new UsFragment());
                break;
            case R.id.rb_hot:
                transaction.replace(R.id.fl_container, new HotFragment());
                break;
            case R.id.rb_top250:
                transaction.replace(R.id.fl_container, new TopFragment());
                break;
        }
        transaction.commit();
    }
}
