package com.houlin.capstone_project.model.contract;

import com.houlin.capstone_project.listener.ComingListener;
import com.houlin.capstone_project.listener.HotListener;
import com.houlin.capstone_project.listener.Top250Listener;
import com.houlin.capstone_project.listener.UsBoxListener;

/**
 * Created by houlin on 2017/12/7.
 */

public interface HomeModel {

    void getHot(HotListener listener);

    void getComing(ComingListener listener);

    void getUs(UsBoxListener listener);

    void getTop250(Top250Listener listener);
}
