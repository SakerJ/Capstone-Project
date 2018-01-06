package com.houlin.capstone_project.detail;

import com.houlin.capstone_project.BasePresenter;
import com.houlin.capstone_project.BaseView;
import com.houlin.capstone_project.data.bean.Detail;
import com.houlin.capstone_project.listener.DetailListener;

/**
 * @author houlin
 */

public interface DetailContract {

    interface Model {
        void getDetail(String id, DetailListener listener);
    }

    interface View extends BaseView {
        void showData(Detail detail);

        void showContent();
    }

    interface Presenter extends BasePresenter {
        void initData(String id);
    }

}
