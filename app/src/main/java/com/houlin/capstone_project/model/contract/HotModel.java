package com.houlin.capstone_project.model.contract;

import com.houlin.capstone_project.listener.ComingListener;
import com.houlin.capstone_project.listener.HotListener;

/**
 * @author houlin
 */

public interface HotModel {

    void getHot(HotListener listener);

    void getComing(ComingListener listener);

}
