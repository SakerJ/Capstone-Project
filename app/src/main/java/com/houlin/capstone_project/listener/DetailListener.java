package com.houlin.capstone_project.listener;

import com.houlin.capstone_project.data.bean.Detail;

/**
 * Created by houlin on 2017/12/7.
 */

public interface DetailListener {

    void onResponse(Detail body);
    void onFailure();
}
