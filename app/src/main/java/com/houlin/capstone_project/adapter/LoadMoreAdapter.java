package com.houlin.capstone_project.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.houlin.capstone_project.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 带footer的Adapter，在正常列表最后加一项footer。
 * 该类封装了所有关于footer的操作
 *
 * @author houlin
 */

public abstract class LoadMoreAdapter<E> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "LoadMoreAdapter";

    private final int FOOTER = 1;
    private final int NORMAL = 0;
    private boolean isLoading = true;// false-已全部加载

    protected List<E> mList = new ArrayList<>();

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        return position == mList.size() ? FOOTER : NORMAL;
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;// footer加1
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOTER) {
            View inflate = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.footer_recycler, parent, false);
            return new FooterViewHolder(inflate);
        } else {
            return onCreateNormalHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == getItemCount() - 1) {// footer
            ((FooterViewHolder) holder).update();
        } else {
            onBindNormalHolder(holder, position);
        }
    }

    public abstract RecyclerView.ViewHolder onCreateNormalHolder(ViewGroup parent, int viewType);

    public abstract void onBindNormalHolder(RecyclerView.ViewHolder holder, int position);

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mLlLoading;
        TextView mTvLoaded;

        public FooterViewHolder(View itemView) {
            super(itemView);
            mLlLoading = itemView.findViewById(R.id.ll_loading);
            mTvLoaded = itemView.findViewById(R.id.tv_loaded);
        }

        void update() {
            mLlLoading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            mTvLoaded.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        }
    }
}
