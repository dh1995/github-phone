package com.feicuiedu.gitdroid.hotrepo.repolist;

import android.os.AsyncTask;

import com.feicuiedu.gitdroid.hotrepo.repolist.view.RepoListView;
import com.feicuiedu.gitdroid.network.GitHubApi;
import com.feicuiedu.gitdroid.network.GitHubClient;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dh1 on 2016/7/27.
 */
public class RepoListPresenter {
    private RepoListView  repoListView;
//    private RepoListPtrView repoListPtrView;
    private int count;

    public RepoListPresenter(RepoListView repoListView) {
        this.repoListView = repoListView;
    }
    //下拉刷新处理
    public void refresh(){
//        new RefreshTask().execute();
        GitHubClient gitHubClient=new GitHubClient();
        GitHubApi gitHubApi=gitHubClient.gitHubApi();
        Call<ResponseBody> call=gitHubApi.getRetrofitContributors();
        // #1 直接在当前线程执行
        // #2 异步执行
        call.enqueue(refreshCallback);
    }
    private final Callback<ResponseBody> refreshCallback=new Callback<ResponseBody>() {
       //响应
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            repoListView.stopRefresh();
            //获取成功200-299
            if(response.isSuccessful()){
                ResponseBody body=response.body();
                if (body==null){
                    repoListView.showMessage("未知错误");
                    return;
                }
                try {
                    String content=body.string();
                    repoListView.showContentView();
                } catch (IOException e) {
                    onFailure(call,e);
                }
            }else {
                //401:一般是指未授权
                repoListView.showMessage("code:"+response.code());
            }
        }

        //失败：比如网络连接异常
        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            repoListView.stopRefresh();
            repoListView.showMessage(t.getMessage());
            repoListView.showContentView();
        }
    };

    //加载更多处理
    public void LoadMore(){
        repoListView.showLoadMoreLoading();
        new LoadMoreTask().execute();
    }
    final class LoadMoreTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayList<String> datas = new ArrayList<String>();
            for (int i = 0;i < 20 ;i++ ){
                datas.add("测试数据" + (count++));
            }
            repoListView.addMoreData(datas);
            repoListView.hideLoadMore();
        }
    }
    final class RefreshTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayList<String> datas = new ArrayList<String>();
            for (int i = 0; i < 20; i++) {
                datas.add("测试数据" + (count++));
            }
            repoListView.stopRefresh();
            repoListView.refreshData(datas);
            repoListView.showContentView();
        }
    }
}
