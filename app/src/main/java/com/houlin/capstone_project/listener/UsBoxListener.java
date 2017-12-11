package com.houlin.capstone_project.listener;

import com.houlin.capstone_project.model.bean.UsBox;

/**
 * Created by houlin on 2017/12/9.
 */

public interface UsBoxListener {
    void onUsResponse(UsBox body);

    void onUsFailure();
}
