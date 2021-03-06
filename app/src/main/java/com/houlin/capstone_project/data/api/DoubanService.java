package com.houlin.capstone_project.data.api;

import com.houlin.capstone_project.data.bean.Detail;
import com.houlin.capstone_project.data.bean.InTheaters;
import com.houlin.capstone_project.data.bean.Top250;
import com.houlin.capstone_project.data.bean.UsBox;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by houlin on 2017/12/6.
 */

public interface DoubanService {

    String BASE_URL = "http://api.douban.com/";

    // 正在热映
    @GET("/v2/movie/in_theaters")
    Call<InTheaters> inTheaters();

    // 即将上映
    @GET("/v2/movie/coming_soon")
    Call<InTheaters> comingSoon(@Query("start") int start, @Query("count") int count);

    // 北美票房榜
    @GET("/v2/movie/us_box")
    Call<UsBox> usBox();

    // Top250
    @GET("/v2/movie/top250")
    Call<Top250> top250(@Query("start") int start, @Query("count") int count);

    // 电影条目信息
    @GET("/v2/movie/subject/{id}")
    Call<Detail> subject(@Path("id") String id);
}
