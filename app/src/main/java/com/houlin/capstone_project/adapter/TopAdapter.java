package com.houlin.capstone_project.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.houlin.capstone_project.R;
import com.houlin.capstone_project.model.bean.Top250;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by houlin on 2017/12/11.
 */

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {

    private android.support.v4.app.Fragment mFragment;
    private List<Top250.SubjectsBean> mList = new ArrayList<>();

    public List<Top250.SubjectsBean> getList() {
        return mList;
    }

    public TopAdapter(Fragment fragment) {
        mFragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_top, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image)
        ImageView mImageView;
        @BindView(R.id.tv_title)
        TextView mTvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void update(int position) {
            Top250.SubjectsBean bean = mList.get(position);
            Glide.with(mFragment).load(bean.getImages().getSmall())
                    .placeholder(R.drawable.place_holder).dontAnimate().into(mImageView);
            mTvTitle.setText(bean.getTitle());
            // TODO: 2017/12/11 设置星级
        }
    }
}
