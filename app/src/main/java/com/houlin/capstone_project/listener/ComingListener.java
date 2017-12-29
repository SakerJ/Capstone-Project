package com.houlin.capstone_project.listener;

import com.houlin.capstone_project.data.bean.InTheaters;

/**
 * Created by houlin on 2017/12/9.
 */

public interface ComingListener {
    void onComingResponse(InTheaters body);

    void onComingFailure();
}
