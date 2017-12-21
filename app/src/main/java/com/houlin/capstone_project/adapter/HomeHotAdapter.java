package com.houlin.capstone_project.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.houlin.capstone_project.R;
import com.houlin.capstone_project.model.bean.InTheaters;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by houlin on 2017/12/7.
 */

public class HomeHotAdapter extends RecyclerView.Adapter<HomeHotAdapter.ViewHolder> {

    private final List<InTheaters.SubjectsBean> mList = new ArrayList<>();
    private final Fragment mFragment;

    public List<InTheaters.SubjectsBean> getList() {
        return mList;
    }

    public HomeHotAdapter(Fragment fragment) {
        mFragment = fragment;
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

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_image)
        ImageView mImageView;
        @Nullable
        @BindView(R.id.tv_text)
        TextView mTextView;
        @BindView(R.id.tv_title)
        TextView mTvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void update(int position) {
            InTheaters.SubjectsBean bean = mList.get(position);
            Glide.with(mFragment).load(bean.getImages().getSmall())
                    .placeholder(R.drawable.place_holder).dontAnimate().into(mImageView);

            if (mTextView == null) {
                ViewStub viewStub = itemView.findViewById(R.id.vs_text);
                mTextView = (TextView) viewStub.inflate();
            }
            mTextView.setText(bean.getCollect_count() + "人看过");
            mTvTitle.setText(bean.getTitle());
        }
    }
}
