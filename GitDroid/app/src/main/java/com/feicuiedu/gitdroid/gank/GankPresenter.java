package com.feicuiedu.gitdroid.gank;

import android.support.annotation.NonNull;

import com.feicuiedu.gitdroid.gank.model.GankItem;
import com.feicuiedu.gitdroid.gank.model.GankResult;
import com.feicuiedu.gitdroid.gank.network.GankClient;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;

/**
 * Created by dh1 on 2016/8/5.
 */
public class GankPresenter {
    private Call<GankResult> call;
    private GankView gankView;

    public GankPresenter(@NonNull GankView gankView){
        this.gankView = gankView;
    }

    /**
     * 获取每日干货数据（通过日期）
     */
    public void getGanks(Date date){
        //得到year、monty、day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int monty = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        call = GankClient.getInstance().getDailyData(year,monty,day);
    }
    private Callback<GankResult> callback = new Callback<GankResult>() {
        @Override public void onResponse(Call<GankResult> call, Response<GankResult> response) {
            GankResult gankResult = response.body();
            if (gankResult == null) {
                gankView.showMessage("未知错误!");
                return;
            }
            // 没有数据的情况
            if (gankResult.isError()
                    || gankResult.getResults() == null
                    || gankResult.getResults().getAndroidItems() == null
                    || gankResult.getResults().getAndroidItems().isEmpty()) {
                gankView.showEmptyView();
                return;
            }
            List<GankItem> gankItems = gankResult.getResults().getAndroidItems();
            // 将获取到的今日敢货数据交付给视图
            gankView.hideEmptyView();
            gankView.setData(gankItems);
        }

        @Override public void onFailure(Call<GankResult> call, Throwable t) {
            gankView.showMessage("Error:" + t.getMessage());
        }
    };
    /**
     * 每日干货业务，视图接口
     */
    public interface GankView {
        void showEmptyView();

        void hideEmptyView();

        void showMessage(String msg);

        void setData(List<GankItem> gankItems);
    }
}
