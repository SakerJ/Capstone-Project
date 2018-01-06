package com.houlin.capstone_project.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.houlin.capstone_project.BaseActivity;
import com.houlin.capstone_project.R;
import com.houlin.capstone_project.adapter.DetailAdapter;
import com.houlin.capstone_project.data.bean.Detail;
import com.houlin.capstone_project.ui.custom.LoadingFrameLayout;
import com.houlin.capstone_project.ui.custom.RatingStar;
import com.houlin.capstone_project.utils.GlideUtils;
import com.houlin.capstone_project.utils.RecyclerUtils;
import com.houlin.capstone_project.utils.ScreenUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.houlin.capstone_project.utils.RecyclerUtils.SpaceItemDecoration.RECYCLERVIEW_SPACE;

public class DetailActivity extends BaseActivity implements DetailContract.View {

    private static final String TAG = "DetailActivity";
    private static final String ID = "id";

    @BindView(R.id.loading)
    LoadingFrameLayout mLoadingFrameLayout;
    @BindView(R.id.tv_label)
    TextView mTvLabel;
    @BindView(R.id.iv_image)
    ImageView mIvImage;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.rs_rating)
    RatingStar mRsRating;
    @BindView(R.id.tv_rating)
    TextView mTvRating;
    @BindView(R.id.tv_rating_count)
    TextView mTvRatingCount;
    @BindView(R.id.tv_summary)
    TextView mTvSummary;
    @BindView(R.id.rv_casts)
    RecyclerView mRvCasts;
    @BindView(R.id.rl_bg)
    RelativeLayout mRlBg;
    @BindView(R.id.iv_bg)
    ImageView mIvBg;

    private String id;
    private DetailContract.Presenter mPresenter;
    private DetailAdapter mDetailAdapter;

    public static void startDetail(Context context, String id) {
        Intent intent = new Intent();
        intent.setClass(context, DetailActivity.class);
        intent.putExtra(ID, id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra(ID);
        init();
        mPresenter = new DetailPresenter();
        mPresenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    private void init() {
        mRvCasts.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        mRvCasts.addItemDecoration(
                new RecyclerUtils.SpaceItemDecoration((int) ScreenUtils.dp2px(RECYCLERVIEW_SPACE)));
        mDetailAdapter = new DetailAdapter(this);
        mRvCasts.setAdapter(mDetailAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        mPresenter.initData(id);
        mLoadingFrameLayout.showLoading();
    }

    @OnClick(R.id.iv_back)
    void click(View view) {
        finish();
    }

    @Override
    public void showData(final Detail detail) {
        // centerCrop方法和transform方法冲突。centerCrop也是transform，transform方法参数是可变参数
        Glide.with(this).load(detail.getImages().getLarge())
                .transform(new CenterCrop(this),
                        new GlideUtils.BlurTransformation(this, 50, getResources().getColor(R.color.black_translucent)))
                .into(mIvBg);
        mTvLabel.setText(detail.getTitle());
        Glide.with(this).load(detail.getImages().getSmall())
                .placeholder(R.drawable.place_holder).dontAnimate().into(mIvImage);
        mTvTitle.setText(detail.getTitle());
        // 类型
        List<String> genres = detail.getGenres();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < genres.size(); i++) {
            if (i != 0) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(genres.get(i));
        }
        mTvType.setText(stringBuilder);
        mTvTime.setText(detail.getYear() + "年上映");
        mRsRating.setRating((float) detail.getRating().getAverage());
        mTvRating.setText(String.valueOf(detail.getRating().getAverage()));
        mTvRatingCount.setText(String.valueOf(detail.getRatings_count()) + "人评分");
        mTvSummary.setText(detail.getSummary());
        // RecyclerView更新
        mDetailAdapter.getList().clear();
        mDetailAdapter.getList().addAll(detail.getCasts());
        mDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void showContent() {
        mLoadingFrameLayout.showContent();
    }
}
