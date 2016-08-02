package com.feicuiedu.gitdroid.github.hotuser.model;

import com.feicuiedu.gitdroid.github.login.modle.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *  Created by dh1 on 2016/8/2.
 */
public class UsersResult {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<User> userList;

    public int getTotalCount() {
        return totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public List<User> getRepoList() {
        return userList;
    }
    
//    "total_count": 603,
//            "incomplete_results": false,
//            "items": [
}
