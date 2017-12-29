package com.houlin.capstone_project.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.houlin.capstone_project.R;
import com.houlin.capstone_project.utils.ToastUtils;
import com.houlin.capstone_project.home.HomeFragment;
import com.houlin.capstone_project.hot.HotFragment;
import com.houlin.capstone_project.top.TopFragment;
import com.houlin.capstone_project.ui.fragment.UsFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rb_home)
    RadioButton mRbHome;
    @BindView(R.id.rb_hot)
    RadioButton mRbHot;
    @BindView(R.id.rb_us)
    RadioButton mRbUs;
    @BindView(R.id.rb_top250)
    RadioButton mRbTop;

    private final int FINISH_TIME = 2000;// 双击退出
    private long lastBackPressed;
    // fragment对应的tag
    public static final String HOME = "home";
    public static final String HOT = "hot";
    public static final String US = "us";
    public static final String TOP = "top";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragments();
        mRbHome.setChecked(true);// 选中首页Tab
    }

    private void initFragments() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_container, new HomeFragment(), HOME);
        transaction.add(R.id.fl_container, new HotFragment(), HOT);
        transaction.add(R.id.fl_container, new UsFragment(), US);
        transaction.add(R.id.fl_container, new TopFragment(), TOP);
        transaction.commitNow();// commit异步延迟，无回退栈操作时则使用commitNow
    }

    private void switchFragment(String fragmentTag) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(fragmentTag);
        if (fragment != null) {
            List<Fragment> fragments = manager.getFragments();
            FragmentTransaction transaction = manager.beginTransaction();
            for (Fragment subFragment : fragments) {
                transaction.hide(subFragment);
            }
            transaction.show(fragment);
            transaction.commit();
        }
    }

    // 通过选中RadioButton来切换fragment。因为用代码切换fragment，RadioButton不会跟随切换
    public void checkRadioButton(String tag) {
        switch (tag) {
            case HOME:
                mRbHome.setChecked(true);
                break;
            case HOT:
                mRbHot.setChecked(true);
                break;
            case US:
                mRbUs.setChecked(true);
                break;
            case TOP:
                mRbTop.setChecked(true);
                break;
        }
    }

    // 形参与onCheckedChangeListener中onCheckedChanged的形参相同
    @OnCheckedChanged({R.id.rb_home, R.id.rb_us, R.id.rb_hot, R.id.rb_top250})
    void checkedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
            return;
        }
        switch (buttonView.getId()) {
            case R.id.rb_home:
                switchFragment(HOME);
                break;
            case R.id.rb_hot:
                switchFragment(HOT);
                break;
            case R.id.rb_us:
                switchFragment(US);
                break;
            case R.id.rb_top250:
                switchFragment(TOP);
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastBackPressed > FINISH_TIME) {
            ToastUtils.showToast("再次点击退出应用", Toast.LENGTH_SHORT);
        } else {
            finish();
        }
        lastBackPressed = currentTimeMillis;
    }
}
