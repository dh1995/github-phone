package com.feicuiedu.gitdroid.hotrepo.repolist;

import android.os.AsyncTask;

import com.feicuiedu.gitdroid.hotrepo.repolist.view.RepoListView;

import java.util.ArrayList;

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
        new RefreshTask().execute();
    }
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
