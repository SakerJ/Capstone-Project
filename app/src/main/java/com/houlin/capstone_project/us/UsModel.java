package com.houlin.capstone_project.us;

import com.houlin.capstone_project.data.api.ApiClient;
import com.houlin.capstone_project.data.bean.UsBox;
import com.houlin.capstone_project.listener.UsBoxListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author houlin
 */

public class UsModel implements UsContract.Model {

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

            }
        });
    }
}
