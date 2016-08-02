package com.feicuiedu.gitdroid.github.hotrepo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.feicuiedu.gitdroid.github.hotrepo.repolist.RepoListFragment;

import java.util.List;

/**
 *  Created by dh1 on 2016/7/27.
 */
public class HotRepoAdapter extends FragmentPagerAdapter {
    private List<Language> languages;

    public HotRepoAdapter(FragmentManager fm, Context context) {
        super(fm);
        languages = Language.getDefaultLanguages(context);
    }

    @Override public Fragment getItem(int position) {
        // RepoListFragment
        return RepoListFragment.getInstance(languages.get(position));
    }

    @Override public int getCount() {
        return languages.size();
    }

    @Override public CharSequence getPageTitle(int position) {
        return languages.get(position).getName();
    }
}
