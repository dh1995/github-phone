package com.feicuiedu.gitdroid.gank.network;

import com.feicuiedu.gitdroid.commons.LoggingInterceptor;
import com.feicuiedu.gitdroid.gank.model.GankResult;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

/**
 * Created by dh1 on 2016/8/5.
 */
public class GankClient implements GankApi{

    private static GankClient sClient;

    public static GankClient getInstance(){
        if (sClient == null){
            sClient = new GankClient();
        }
        return sClient;
    }

    private final GankApi gankApi;

    private GankClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor()).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build();
        gankApi = retrofit.create(GankApi.class);
    }
    @Override
    public Call<GankResult> getDailyData(@Path("year") int year, @Path("month") int month, @Path("day") int day) {
        return gankApi.getDailyData(year,month,day);
    }
}
