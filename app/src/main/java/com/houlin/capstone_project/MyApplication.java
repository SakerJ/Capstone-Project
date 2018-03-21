package com.houlin.capstone_project;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.android.gms.ads.MobileAds;
import com.houlin.capstone_project.utils.ToastUtils;

/**
 * @author houlin
 */

public class MyApplication extends Application {

    private static Handler handler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化admob
        MobileAds.initialize(this, "ca-app-pub-6273206636756153~6226484935");
        ToastUtils.init(this);
    }

    public static Handler getHandler() {
        return handler;
    }

    public static boolean isRunOnUIThread() {
        Looper myLooper = Looper.myLooper();
        return myLooper == Looper.getMainLooper();
    }

    public static void runOnUIThread(Runnable runnable) {
        if (isRunOnUIThread()) {
            runnable.run();
        } else {
            handler.post(runnable);
        }
    }
}
