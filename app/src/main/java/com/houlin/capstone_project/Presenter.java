package com.houlin.capstone_project;

/**
 * @author houlin
 */

public abstract class Presenter<T extends BaseView> implements BasePresenter<T> {

    private T mView;


    @Override
    public void attach(T view) {
        mView = view;
    }

    @Override
    public void detach() {
        mView = null;
    }
}
