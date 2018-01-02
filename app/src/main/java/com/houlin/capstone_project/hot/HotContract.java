package com.houlin.capstone_project.hot;

import com.houlin.capstone_project.BasePresenter;
import com.houlin.capstone_project.BaseView;
import com.houlin.capstone_project.listener.ComingListener;
import com.houlin.capstone_project.listener.HotListener;
import com.houlin.capstone_project.data.bean.InTheaters;

/**
 * @author houlin
 */

public interface HotContract {

    interface Model {
        void getHot(HotListener listener);

        void getComing(ComingListener listener);
    }

    interface View extends BaseView {
        void showHot(InTheaters inTheaters);

        void showContent();
    }

    interface Presenter extends BasePresenter {
        void getData();
    }
}
