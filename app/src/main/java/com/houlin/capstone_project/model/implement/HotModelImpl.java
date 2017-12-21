package com.houlin.capstone_project.model.implement;

import com.houlin.capstone_project.listener.ComingListener;
import com.houlin.capstone_project.listener.HotListener;
import com.houlin.capstone_project.model.api.ApiClient;
import com.houlin.capstone_project.model.bean.InTheaters;
import com.houlin.capstone_project.model.contract.HotModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author houlin
 */

public class HotModelImpl implements HotModel {
    @Override
    public void getHot(final HotListener listener) {
        ApiClient.service.inTheaters().enqueue(new Callback<InTheaters>() {
            @Override
            public void onResponse(Call<InTheaters> call, Response<InTheaters> response) {
                InTheaters inTheaters = response.body();
                listener.onResponse(inTheaters);
            }

            @Override
            public void onFailure(Call<InTheaters> call, Throwable t) {

            }
        });
    }

    @Override
    public void getComing(final ComingListener listener) {
        ApiClient.service.comingSoon(0, 10).enqueue(new Callback<InTheaters>() {
            @Override
            public void onResponse(Call<InTheaters> call, Response<InTheaters> response) {
                InTheaters inTheaters = response.body();
                listener.onComingResponse(inTheaters);
            }

            @Override
            public void onFailure(Call<InTheaters> call, Throwable t) {

            }
        });
    }
}
