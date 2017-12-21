package com.houlin.capstone_project;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.android.gms.ads.MobileAds;

/**
 * @author houlin
 */

public class MyApplication extends Application {

    private static Context context;
    private static Handler handler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // 初始化admob
        MobileAds.initialize(context, "ca-app-pub-6273206636756153~6226484935");
    }

    public static Context getContext() {
        return context;
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
