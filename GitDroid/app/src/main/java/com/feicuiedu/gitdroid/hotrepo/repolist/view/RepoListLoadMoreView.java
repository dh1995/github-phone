package com.feicuiedu.gitdroid.hotrepo.repolist.view;

import java.util.List;

/**
 * 加载更多视图抽象接口
 * Created by dh1 on 2016/7/28.
 */
public interface RepoListLoadMoreView {
    void showLoadMoreLoading();
    void hideLoadMore();
    void showLoadMoreErro(String erroMsg);
    void addMoreData(List<String> datas);
}
