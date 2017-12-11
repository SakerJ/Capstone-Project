package com.houlin.capstone_project.view.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.houlin.capstone_project.R;
import com.houlin.capstone_project.presenter.contract.MainPresenter;
import com.houlin.capstone_project.presenter.implement.MainPresenterImpl;
import com.houlin.capstone_project.view.contract.MainView;
import com.houlin.capstone_project.view.ui.fragment.HomeFragment;
import com.houlin.capstone_project.view.ui.fragment.HotFragment;
import com.houlin.capstone_project.view.ui.fragment.UsFragment;
import com.houlin.capstone_project.view.ui.fragment.TopFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.rb_home)
    RadioButton mRbHome;
    @BindView(R.id.rb_recommend)
    RadioButton mRbRecommend;
    @BindView(R.id.rb_hot)
    RadioButton mRbHot;
    @BindView(R.id.rb_top250)
    RadioButton mRbTop;

    private MainPresenter mMainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainPresenter = new MainPresenterImpl();
        mMainPresenter.attach(this);
        mRbHome.setChecked(true);// 选中首页Tab
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detach();
    }

    // 形参与onCheckedChangeListener中onCheckedChanged的形参相同
    @OnCheckedChanged({R.id.rb_home, R.id.rb_recommend, R.id.rb_hot, R.id.rb_top250})
    void switchFragment(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (buttonView.getId()) {
            case R.id.rb_home:
                transaction.replace(R.id.fl_container, new HomeFragment());
                break;
            case R.id.rb_recommend:
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
