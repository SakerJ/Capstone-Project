package com.houlin.capstone_project.view.ui.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.houlin.capstone_project.R;

/**
 * 用于显示数据加载状态的FrameLayout，如loading、error、empty等状态。
 * 其中error状态包括一个retry动作
 *
 * @author houlin
 */

public class LoadingFrameLayout extends FrameLayout {

    private LayoutInflater mInflater;
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;

    public LoadingFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public LoadingFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
    }

    public void showLoading() {
        if (mLoadingView == null) {
            mLoadingView = mInflater.inflate(R.layout.layout_loading, this, false);
            addView(mLoadingView);
        }
        showChildren(false);
        mLoadingView.setVisibility(VISIBLE);
    }

    public void showError() {
        if (mErrorView == null) {
            mErrorView = mInflater.inflate(R.layout.layout_error, this, false);
            addView(mErrorView);
        }
        showChildren(false);
        mErrorView.setVisibility(VISIBLE);
    }

    public void showEmpty() {
        if (mEmptyView == null) {
            mEmptyView = mInflater.inflate(R.layout.layout_empty, this, false);
            addView(mEmptyView);
        }
        showChildren(false);
        mEmptyView.setVisibility(VISIBLE);
    }

    public void showContent() {
        showChildren(true);
        if (mLoadingView != null) {
            mLoadingView.setVisibility(GONE);
        }
        if (mErrorView != null) {
            mErrorView.setVisibility(GONE);
        }
        if (mEmptyView != null) {
            mEmptyView.setVisibility(GONE);
        }
    }

    private void showChildren(boolean isVisible) {
        int visible = isVisible ? VISIBLE : GONE;
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(visible);
        }
    }
}
