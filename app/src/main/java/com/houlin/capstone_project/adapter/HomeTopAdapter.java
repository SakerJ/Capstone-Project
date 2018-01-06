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
import com.houlin.capstone_project.data.bean.Top250;
import com.houlin.capstone_project.detail.DetailActivity;
import com.houlin.capstone_project.ui.custom.RatingStar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by houlin on 2017/12/11.
 */

public class HomeTopAdapter extends RecyclerView.Adapter<HomeTopAdapter.ViewHolder> {

    private android.support.v4.app.Fragment mFragment;
    private List<Top250.SubjectsBean> mList = new ArrayList<>();

    public List<Top250.SubjectsBean> getList() {
        return mList;
    }

    public HomeTopAdapter(Fragment fragment) {
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image)
        ImageView mImageView;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @Nullable
        @BindView(R.id.rs_rating)
        RatingStar mRatingStar;
        @Nullable
        @BindView(R.id.tv_rating)
        TextView mTvRating;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void update(int position) {
            final Top250.SubjectsBean bean = mList.get(position);
            Glide.with(mFragment).load(bean.getImages().getSmall())
                    .placeholder(R.drawable.place_holder).dontAnimate().into(mImageView);
            mTvTitle.setText(bean.getTitle());
            if (mRatingStar == null || mTvRating == null) {
                ViewStub viewStub = itemView.findViewById(R.id.vs_text);
                viewStub.setLayoutResource(R.layout.inflate_home_top);
                viewStub.inflate();
                mRatingStar = itemView.findViewById(R.id.rs_rating);
                mTvRating = itemView.findViewById(R.id.tv_rating);
            }
            mRatingStar.setRating((float) bean.getRating().getAverage());
            mTvRating.setText(bean.getRating().getAverage() + "分");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailActivity.startDetail(itemView.getContext(), bean.getId());
                }
            });
        }
    }
}
