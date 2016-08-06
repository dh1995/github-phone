package com.feicuiedu.gitdroid.gank.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dh1 on 2016/8/5.
 */
@SuppressWarnings("unused")
public class GankResult {

    private boolean error;

    private List<String> category;

    private Results results;

    public boolean isError() {
        return error;
    }

    public List<String> getCategory() {
        return category;
    }

    public Results getResults() {
        return results;
    }

    public static class Results{
        @SerializedName("Android")
        private List<GankItem> androidItems;

        public List<GankItem> getAndroidItems(){
            return  androidItems;
        }
    }
}
