package com.feicuiedu.gitdroid.hotrepo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.feicuiedu.gitdroid.hotrepo.repolist.RepoListFragment;

/**
 * Created by dh1 on 2016/7/27.
 */
public class HotRepoAdapter extends FragmentPagerAdapter{
    public HotRepoAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new RepoListFragment();
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "java"+position;
    }
}
