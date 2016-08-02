package com.feicuiedu.gitdroid.github.hotuser.view;

import com.feicuiedu.gitdroid.github.login.modle.User;

import java.util.List;

/**
 * Created by dh1 on 2016/8/2.
 */
public interface UserListPtrView {
    /** 显示下拉刷新时的内容视图*/
    void showContentView();
    /** 显示下拉刷新时的错误视图*/
    void showErrorView(String errorMsg);
    /** 显示下拉刷新时的空视图*/
    void showEmptyView();
    void showMessage(String msg);
    void stopRefresh();
    void refreshData(List<User> data);
}
