package com.houlin.capstone_project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author houlin
 */

public abstract class BaseFragment extends Fragment {

    private String TAG = getClass().getSimpleName();
    private boolean isInitData;
    private boolean isVisible;

    // 与ViewPager配合时的懒加载(part1
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint: " + isVisibleToUser);
        isVisible = isVisibleToUser;
    }

    // 与ViewPager配合时的懒加载(part2
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        if (!isInitData && isVisible) {
            isInitData = true;
            initData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // 与FragmentTransaction配合时的懒加载
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG, "onHiddenChanged: " + hidden);
        if (!isInitData && !hidden) {
            isInitData = true;
            initData();
        }
    }

    protected abstract void initData();
}
