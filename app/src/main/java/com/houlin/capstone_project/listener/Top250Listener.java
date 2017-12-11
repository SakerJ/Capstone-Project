package com.houlin.capstone_project.listener;

import com.houlin.capstone_project.model.bean.Top250;

/**
 * Created by houlin on 2017/12/10.
 */

public interface Top250Listener {
    void onTopResponse(Top250 top250);

    void onTopFailure();
}
