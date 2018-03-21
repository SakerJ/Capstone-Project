package com.houlin.capstone_project.utils;

import android.content.Context;
import android.widget.Toast;

import com.houlin.capstone_project.MyApplication;

/**
 * @author houlin
 */

public class ToastUtils {

    private static Toast toast;

    private ToastUtils() {
    }

    public static void init(Context context) {
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }

    public static void showToast(final String text, final int duration) {
        if (toast == null) return;
        MyApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                toast.setText(text);
                toast.setDuration(duration);
                toast.show();
            }
        });
    }

    public static void showToast(final int resId, final int duration) {
        if (toast == null) return;
        MyApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                toast.setText(resId);
                toast.setDuration(duration);
                toast.show();
            }
        });
    }

}
