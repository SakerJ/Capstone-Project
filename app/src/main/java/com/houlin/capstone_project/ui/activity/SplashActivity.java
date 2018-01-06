package com.houlin.capstone_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.houlin.capstone_project.BaseActivity;
import com.houlin.capstone_project.MyApplication;
import com.houlin.capstone_project.R;

public class SplashActivity extends BaseActivity {

    private final int DELAY_MILLIS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        MyApplication.getHandler().postDelayed(new StartRunnable(), DELAY_MILLIS);
    }

    private class StartRunnable implements Runnable {

        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }
}
