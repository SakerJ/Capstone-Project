package com.houlin.capstone_project.model.implement;

import com.houlin.capstone_project.listener.ComingListener;
import com.houlin.capstone_project.listener.HotListener;
import com.houlin.capstone_project.listener.Top250Listener;
import com.houlin.capstone_project.listener.UsBoxListener;
import com.houlin.capstone_project.model.api.ApiClient;
import com.houlin.capstone_project.model.bean.InTheaters;
import com.houlin.capstone_project.model.bean.Top250;
import com.houlin.capstone_project.model.bean.UsBox;
import com.houlin.capstone_project.model.contract.HomeModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by houlin on 2017/12/7.
 */

public class HomeModelImpl implements HomeModel {

    @Override
    public void getHot(final HotListener listener) {
        Call<InTheaters> call = ApiClient.service.inTheaters();
        call.enqueue(new Callback<InTheaters>() {
            @Override
            public void onResponse(Call<InTheaters> call, Response<InTheaters> response) {
                InTheaters body = response.body();
                listener.onResponse(body);
            }

            @Override
            public void onFailure(Call<InTheaters> call, Throwable t) {
                listener.onFailure();
            }
        });

    }

    @Override
    public void getComing(final ComingListener listener) {
        ApiClient.service.comingSoon(0, 10).enqueue(new Callback<InTheaters>() {
            @Override
            public void onResponse(Call<InTheaters> call, Response<InTheaters> response) {
                InTheaters body = response.body();
                listener.onComingResponse(body);
            }

            @Override
            public void onFailure(Call<InTheaters> call, Throwable t) {
                listener.onComingFailure();
            }
        });
    }

    @Override
    public void getUs(final UsBoxListener listener) {
        ApiClient.service.usBox().enqueue(new Callback<UsBox>() {
            @Override
            public void onResponse(Call<UsBox> call, Response<UsBox> response) {
                UsBox body = response.body();
                listener.onUsResponse(body);
            }

            @Override
            public void onFailure(Call<UsBox> call, Throwable t) {
                listener.onUsFailure();
            }
        });
    }

    @Override
    public void getTop250(final Top250Listener listener) {
        ApiClient.service.top250(0, 10).enqueue(new Callback<Top250>() {
            @Override
            public void onResponse(Call<Top250> call, Response<Top250> response) {
                Top250 body = response.body();
                listener.onTopResponse(body);
            }

            @Override
            public void onFailure(Call<Top250> call, Throwable t) {
                listener.onTopFailure();
            }
        });
    }
}
