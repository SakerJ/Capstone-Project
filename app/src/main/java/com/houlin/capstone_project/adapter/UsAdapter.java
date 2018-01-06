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
import com.houlin.capstone_project.data.bean.UsBox;
import com.houlin.capstone_project.detail.DetailActivity;
import com.houlin.capstone_project.ui.custom.RatingStar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author houlin
 */

public class UsAdapter extends RecyclerView.Adapter<UsAdapter.ViewHolder> {

    private List<UsBox.SubjectsBean> mList = new ArrayList<>();
    private Fragment mFragment;

    public UsAdapter(Fragment fragment) {
        mFragment = fragment;
    }

    public List<UsBox.SubjectsBean> getList() {
        return mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hot, parent, false);
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
        @BindView(R.id.rs_rating)
        RatingStar mRsRating;
        @BindView(R.id.tv_rating)
        TextView mTvRating;
        @BindView(R.id.tv_director)
        TextView mTvDirector;
        @BindView(R.id.tv_cast)
        TextView mTvCast;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void update(int position) {
            final UsBox.SubjectsBean.SubjectBean subjectsBean = mList.get(position).getSubject();
            Glide.with(mFragment).load(subjectsBean.getImages().getSmall())
                    .placeholder(R.drawable.place_holder).dontAnimate().into(mImageView);
            mTvTitle.setText(subjectsBean.getTitle());
            mRsRating.setRating((float) subjectsBean.getRating().getAverage());
            mTvRating.setText(subjectsBean.getRating().getAverage() + "分");

            StringBuilder director = new StringBuilder("导演：");
            for (int i = 0; i < subjectsBean.getDirectors().size(); i++) {
                UsBox.SubjectsBean.SubjectBean.DirectorsBean bean = subjectsBean.getDirectors().get(i);
                if (i != 0) {
                    director.append(" / ");
                }
                director.append(bean.getName());
            }
            mTvDirector.setText(director);

            StringBuilder cast = new StringBuilder("主演：");
            for (int i = 0; i < subjectsBean.getCasts().size(); i++) {
                UsBox.SubjectsBean.SubjectBean.CastsBean bean = subjectsBean.getCasts().get(i);
                if (i != 0) {
                    cast.append(" / ");
                }
                cast.append(bean.getName());
            }
            mTvCast.setText(cast);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailActivity.startDetail(itemView.getContext(), subjectsBean.getId());
                }
            });
        }
    }
}
