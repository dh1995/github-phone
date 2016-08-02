package com.feicuiedu.gitdroid.github.hotrepo.repolist.view;

import com.feicuiedu.gitdroid.github.hotrepo.repolist.modle.Repo;

import java.util.List;

/**
 * 加载更多的视图抽象
 *  Created by dh1 on 2016/7/28.
 */
public interface RepoListLoadMoreView {
    /** 显示上拉加载时的加载中视图*/
    void showLoadMoreLoading();

    /** 隐藏上拉加载时的加载中视图*/
    void hideLoadMore();

    /** 隐藏上拉加载时的错误视图*/
    void showLoadMoreErro(String erroMsg);

    void addMoreData(List<Repo> datas);
}