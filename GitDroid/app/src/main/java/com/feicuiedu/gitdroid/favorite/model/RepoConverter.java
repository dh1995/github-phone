package com.feicuiedu.gitdroid.favorite.model;

import android.support.annotation.NonNull;

import com.feicuiedu.gitdroid.github.hotrepo.repolist.modle.Repo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by dh1 on 2016/8/4.
 */
public class RepoConverter {
    private RepoConverter(){

    }
    /**
     * 将热门仓库Repo转换为LocalRepo本地仓库对象，默认为未分类
     */
    public static @NonNull LocalRepo convert(@NonNull Repo repo){
        LocalRepo localRepo = new LocalRepo();
        localRepo.setAvatar(repo.getOwner().getAvatar());
        localRepo.setDescription(repo.getDescription());
        localRepo.setFullName(repo.getFullName());
        localRepo.setId(repo.getId());
        localRepo.setName(repo.getName());
        localRepo.setStartCount(repo.getStarCount());
        localRepo.setForkCount(repo.getForkCount());
        localRepo.setRepoGroup(null);
        return localRepo;
    }
    public static @NonNull
    List<LocalRepo> converAll(@NonNull List<Repo> repos){
        ArrayList<LocalRepo> localRepos = new ArrayList<LocalRepo>();
        for (Repo repo : repos){
            localRepos.add(convert(repo));
        }
        return localRepos;
    }
}
