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
import com.houlin.capstone_project.model.bean.UsBox;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by houlin on 2017/12/10.
 */

public class UsAdapter extends RecyclerView.Adapter<UsAdapter.ViewHolder> {

    private Fragment mFragment;
    private List<UsBox.SubjectsBean> mList = new ArrayList<>();

    public UsAdapter(Fragment fragment) {
        mFragment = fragment;
    }

    public List<UsBox.SubjectsBean> getList() {
        return mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_hot, parent, false);
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
        @BindView(R.id.tv_text)
        TextView mTextView;
        @BindView(R.id.tv_title)
        TextView mTvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void update(int position) {
            UsBox.SubjectsBean bean = mList.get(position);
            UsBox.SubjectsBean.SubjectBean subject = bean.getSubject();
            Glide.with(mFragment).load(subject.getImages().getSmall())
                    .placeholder(R.drawable.place_holder).dontAnimate().into(mImageView);
            mTvTitle.setText(subject.getTitle());
            mTextView.setText(bean.getBox() / 10000 + "万票房");
        }
    }
}
