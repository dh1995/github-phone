package com.feicuiedu.gitdroid.gank.network;

import com.feicuiedu.gitdroid.gank.model.GankResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dh1 on 2016/8/5.
 */
interface GankApi {

    String ENDPOINT = "http://gank.io/api/";

    @GET("day/{year}/{month}/{day}")
    Call<GankResult> getDailyData(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);
}
