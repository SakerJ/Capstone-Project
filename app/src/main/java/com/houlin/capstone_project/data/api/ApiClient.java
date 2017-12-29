package com.houlin.capstone_project.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by houlin on 2017/12/6.
 */

public final class ApiClient {

    private ApiClient() {
    }

    public static final DoubanService service = new Retrofit.Builder()
            .baseUrl(DoubanService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DoubanService.class);

}
