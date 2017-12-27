package com.houlin.capstone_project.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author houlin
 */

public class HomePagerAdapter extends PagerAdapter {

    private List<Integer> mList = new ArrayList<>();

    public List<Integer> getList() {
        return mList;
    }

    @Override
    public int getCount() {
        return mList.size() == 0 ? 0 : Integer.MAX_VALUE;// 无限轮播
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
//        return false;
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
        position %= mList.size();// 防止无限轮播导致list越界
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mList.get(position));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
