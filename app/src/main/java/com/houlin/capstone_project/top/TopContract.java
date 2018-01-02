package com.houlin.capstone_project.top;

import com.houlin.capstone_project.BasePresenter;
import com.houlin.capstone_project.BaseView;
import com.houlin.capstone_project.data.bean.Top250;
import com.houlin.capstone_project.listener.Top250Listener;

/**
 * @author houlin
 */

public interface TopContract {

    interface Model {
        void getTop(int start, int count, boolean isRefresh, Top250Listener listener);
    }

    interface View extends BaseView {
        void showTop(Top250 top250, boolean isRefresh);

        void loadFinish();
    }

    interface Presenter extends BasePresenter {
        void getData(int start, boolean isRefresh);
    }

}
