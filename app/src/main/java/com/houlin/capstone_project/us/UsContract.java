package com.houlin.capstone_project.us;

import com.houlin.capstone_project.BasePresenter;
import com.houlin.capstone_project.BaseView;
import com.houlin.capstone_project.data.bean.UsBox;
import com.houlin.capstone_project.listener.UsBoxListener;

/**
 * @author houlin
 */

public interface UsContract {

    interface Model {
        void getUs(UsBoxListener listener);
    }

    interface View extends BaseView {
        void showUs(UsBox usBox);
    }

    interface Presenter extends BasePresenter {
        void getData();
    }

}
