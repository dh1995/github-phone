package com.feicuiedu.gitdroid;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.feicuiedu.gitdroid.hotrepo.HotRepoFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.navigationView)
    NavigationView navigationView;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    private HotRepoFragment hotRepoFragment;//热门仓库Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置当前视图(也就是说，更改了当前视图内容,将导至onContentChanged方法触发)
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        //ActionBar处理
        setSupportActionBar(toolbar);
        //navigationView监听器
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(//构建抽屉的监听
                this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close );
        //根据drawerlayout同步其当前状态
        toggle.syncState();
        //设置抽屉监听
        drawerLayout.addDrawerListener(toggle);
        //默认显示热门仓库fragment
        hotRepoFragment =new HotRepoFragment();
        replaceFragment(hotRepoFragment);
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.commit();
    }
    //侧滑菜单监听
    @Override public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 热门仓库
            case R.id.github_hot_repo:

                break;
        }
        // 返回true，代表将该菜单项变为checked状态
        return true;
    }
}
