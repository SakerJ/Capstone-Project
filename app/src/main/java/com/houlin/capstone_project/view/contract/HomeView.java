package com.houlin.capstone_project.view.contract;

import com.houlin.capstone_project.model.bean.InTheaters;
import com.houlin.capstone_project.model.bean.Top250;
import com.houlin.capstone_project.model.bean.UsBox;

/**
 * Created by houlin on 2017/12/7.
 */

public interface HomeView {
    void showHot(InTheaters inTheaters);

    void showComing(InTheaters inTheaters);

    void showUsBox(UsBox usBox);

    void showTop250(Top250 top250);
}
