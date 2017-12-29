package com.houlin.capstone_project.home;

import com.houlin.capstone_project.BasePresenter;
import com.houlin.capstone_project.BaseView;
import com.houlin.capstone_project.listener.ComingListener;
import com.houlin.capstone_project.listener.HotListener;
import com.houlin.capstone_project.listener.Top250Listener;
import com.houlin.capstone_project.listener.UsBoxListener;
import com.houlin.capstone_project.data.bean.InTheaters;
import com.houlin.capstone_project.data.bean.Top250;
import com.houlin.capstone_project.data.bean.UsBox;

import java.util.List;

/**
 * @author houlin
 */

public interface HomeContract {

    interface Model {
        void getHot(HotListener listener);

        void getComing(ComingListener listener);

        void getUs(UsBoxListener listener);

        void getTop250(Top250Listener listener);
    }

    interface View extends BaseView {
        void showHot(InTheaters inTheaters);

        void showComing(InTheaters inTheaters);

        void showUsBox(UsBox usBox);

        void showTop250(Top250 top250);

        void showError();

        void showContent();

        void showBanner(List<Integer> resIds);
    }

    interface Presenter extends BasePresenter {
        void getData();
    }

}
