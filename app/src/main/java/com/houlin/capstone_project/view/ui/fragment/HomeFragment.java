package com.houlin.capstone_project.view.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.houlin.capstone_project.BaseFragment;
import com.houlin.capstone_project.R;
import com.houlin.capstone_project.adapter.HomeComingAdapter;
import com.houlin.capstone_project.adapter.HomeHotAdapter;
import com.houlin.capstone_project.adapter.HomeTopAdapter;
import com.houlin.capstone_project.adapter.HomeUsAdapter;
import com.houlin.capstone_project.model.bean.InTheaters;
import com.houlin.capstone_project.model.bean.Top250;
import com.houlin.capstone_project.model.bean.UsBox;
import com.houlin.capstone_project.presenter.contract.HomePresenter;
import com.houlin.capstone_project.presenter.implement.HomePresenterImpl;
import com.houlin.capstone_project.utils.RecyclerUtils;
import com.houlin.capstone_project.utils.ScreenUtils;
import com.houlin.capstone_project.view.contract.HomeView;
import com.houlin.capstone_project.view.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements HomeView {

    private static final String TAG = "HomeFragment";
    private final int RECYCLERVIEW_SPACE = 10;// RecyclerView间距

    @BindView(R.id.rv_hot)
    RecyclerView mRvHot;
    @BindView(R.id.rv_coming)
    RecyclerView mRvComing;
    @BindView(R.id.rv_us)
    RecyclerView mRvUs;
    @BindView(R.id.rv_top)
    RecyclerView mRvTop;

    private HomePresenter mPresenter;
    private HomeHotAdapter mHomeHotAdapter;
    private HomeComingAdapter mHomeComingAdapter;
    private HomeUsAdapter mHomeUsAdapter;
    private HomeTopAdapter mHomeTopAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        // 正在热映RecyclerView
        mRvHot.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        // RecyclerView的item间距
        mRvHot.addItemDecoration(
                new RecyclerUtils.SpaceItemDecoration((int) ScreenUtils.dp2px(RECYCLERVIEW_SPACE)));
        mHomeHotAdapter = new HomeHotAdapter(this);
        mRvHot.setAdapter(mHomeHotAdapter);

        // 即将上映RecyclerView
        mRvComing.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        mRvComing.addItemDecoration(
                new RecyclerUtils.SpaceItemDecoration((int) ScreenUtils.dp2px(RECYCLERVIEW_SPACE)));
        mHomeComingAdapter = new HomeComingAdapter(this);
        mRvComing.setAdapter(mHomeComingAdapter);

        // 北美票房榜RecyclerView
        mRvUs.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        mRvUs.addItemDecoration(
                new RecyclerUtils.SpaceItemDecoration((int) ScreenUtils.dp2px(RECYCLERVIEW_SPACE)));
        mHomeUsAdapter = new HomeUsAdapter(this);
        mRvUs.setAdapter(mHomeUsAdapter);

        // Top250RecyclerView
        mRvTop.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        mRvTop.addItemDecoration(
                new RecyclerUtils.SpaceItemDecoration((int) ScreenUtils.dp2px(RECYCLERVIEW_SPACE)));
        mHomeTopAdapter = new HomeTopAdapter(this);
        mRvTop.setAdapter(mHomeTopAdapter);

        mPresenter = new HomePresenterImpl();
        mPresenter.attach(this);
        mPresenter.getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detach();
    }

    @OnClick({R.id.rl_hot, R.id.rl_coming, R.id.rl_us, R.id.rl_top})
    void click(View view) {
        MainActivity activity = (MainActivity) getActivity();
        switch (view.getId()) {
            case R.id.rl_hot:
                // TODO: 2017/12/13 传参
                activity.checkRadioButton(MainActivity.HOT);
                break;
            case R.id.rl_coming:
                // TODO: 2017/12/13 传参
                activity.checkRadioButton(MainActivity.HOT);
                break;
            case R.id.rl_us:
                activity.checkRadioButton(MainActivity.US);
                break;
            case R.id.rl_top:
                activity.checkRadioButton(MainActivity.TOP);
                break;
        }
    }

    @Override
    public void showHot(InTheaters inTheaters) {
        mHomeHotAdapter.getList().clear();
        mHomeHotAdapter.getList().addAll(inTheaters.getSubjects());
        mHomeHotAdapter.notifyDataSetChanged();
    }

    @Override
    public void showComing(InTheaters inTheaters) {
        mHomeComingAdapter.getSubjectsBeans().clear();
        mHomeComingAdapter.getSubjectsBeans().addAll(inTheaters.getSubjects());
        mHomeComingAdapter.notifyDataSetChanged();
    }

    @Override
    public void showUsBox(UsBox usBox) {
        mHomeUsAdapter.getList().clear();
        mHomeUsAdapter.getList().addAll(usBox.getSubjects());
        mHomeUsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTop250(Top250 top250) {
        mHomeTopAdapter.getList().clear();
        mHomeTopAdapter.getList().addAll(top250.getSubjects());
        mHomeTopAdapter.notifyDataSetChanged();
    }

}
