package com.houlin.capstone_project.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.houlin.capstone_project.R;
import com.houlin.capstone_project.data.bean.Detail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author houlin
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private List<Detail.CastsBean> mList = new ArrayList<>();
    private Activity mActivity;

    public DetailAdapter(Activity activity) {
        mActivity = activity;
    }

    public List<Detail.CastsBean> getList() {
        return mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_casts, parent, false);
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

        @BindView(R.id.iv_cast)
        ImageView mIvCast;
        @BindView(R.id.tv_cast)
        TextView mTvCast;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void update(int position) {
            Detail.CastsBean castsBean = mList.get(position);
            Glide.with(mActivity).load(castsBean.getAvatars().getSmall())
                    .placeholder(R.drawable.place_holder).dontAnimate().into(mIvCast);
            mTvCast.setText(castsBean.getName());
        }
    }

}
