package com.houlin.capstone_project.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by houlin on 2017/12/5.
 */

public class ScreenUtils {

    public static float dp2px(int dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }
}
