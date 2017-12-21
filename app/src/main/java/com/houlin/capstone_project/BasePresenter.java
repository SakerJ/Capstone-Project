package com.houlin.capstone_project;

/**
 * @author houlin
 */

public interface BasePresenter<T extends BaseView> {

    void attach(T view);

    void detach();
}
