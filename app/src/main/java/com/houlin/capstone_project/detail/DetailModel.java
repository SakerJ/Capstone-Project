package com.houlin.capstone_project.detail;

import com.houlin.capstone_project.data.api.ApiClient;
import com.houlin.capstone_project.data.bean.Detail;
import com.houlin.capstone_project.listener.DetailListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author houlin
 */

public class DetailModel implements DetailContract.Model {
    @Override
    public void getDetail(String id, final DetailListener listener) {
        ApiClient.service.subject(id).enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                Detail body = response.body();
                listener.onResponse(body);
            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {

            }
        });
    }
}
