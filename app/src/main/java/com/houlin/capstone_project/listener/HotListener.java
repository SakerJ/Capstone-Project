package com.houlin.capstone_project.listener;

import com.houlin.capstone_project.data.bean.InTheaters;

/**
 * Created by houlin on 2017/12/7.
 */

public interface HotListener {

    void onResponse(InTheaters body);
    void onFailure();
}
