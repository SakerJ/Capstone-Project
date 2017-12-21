package com.houlin.capstone_project.utils;

import android.widget.Toast;

import com.houlin.capstone_project.MyApplication;

/**
 * @author houlin
 */

public class ToastUtils {

    private static Toast toast;

    public static void showToast(final String text, final int duration) {
        MyApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                if (toast == null) {
                    toast = Toast.makeText(MyApplication.getContext(), text, duration);
                } else {
                    toast.setText(text);
                    toast.setDuration(duration);
                }
                toast.show();
            }
        });
    }

    public static void showToast(final int resId, final int duration) {
        MyApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                if (toast == null) {
                    toast = Toast.makeText(MyApplication.getContext(), resId, duration);
                } else {
                    toast.setText(resId);
                    toast.setDuration(duration);
                }
                toast.show();
            }
        });
    }

}
