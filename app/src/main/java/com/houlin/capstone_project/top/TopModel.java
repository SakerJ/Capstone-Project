package com.houlin.capstone_project.top;

import com.houlin.capstone_project.data.api.ApiClient;
import com.houlin.capstone_project.data.bean.Top250;
import com.houlin.capstone_project.listener.Top250Listener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author houlin
 */

public class TopModel implements TopContract.Model {

    @Override
    public void getTop(int start, int count, final Top250Listener listener) {
        ApiClient.service.top250(start, count).enqueue(new Callback<Top250>() {
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
