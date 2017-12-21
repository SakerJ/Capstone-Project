package com.houlin.capstone_project.view.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.houlin.capstone_project.MyApplication;
import com.houlin.capstone_project.R;

public class SplashActivity extends AppCompatActivity {

    private final int DELAY_MILLIS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 2017/12/7 全屏
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
