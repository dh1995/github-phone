package com.feicuiedu.gitdroid.favorite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.commons.ActivityUtils;
import com.feicuiedu.gitdroid.favorite.dao.DBHelp;
import com.feicuiedu.gitdroid.favorite.dao.RepoGroupDao;
import com.feicuiedu.gitdroid.favorite.model.RepoGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 本地收藏页面
 * Created by dh1 on 2016/8/3.
 */
public class FavoriteFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    @Bind(R.id.tvGroupType)
    TextView tvGroupType;// 显示当前类别的文本
    @Bind(R.id.btnFilter)
    ImageButton btnFilter;// 切换类别的按钮
    @Bind(R.id.listView)
    ListView listView;
    private ActivityUtils activityUtils;
    private RepoGroupDao repoGroupDao;//仓库类别Dao

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        repoGroupDao = new RepoGroupDao(DBHelp.getInstance(getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    //按下按钮弹出类别菜单
    @OnClick(R.id.btnFilter)
    public void showPopupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(getContext(),view);
        popupMenu.setOnMenuItemClickListener(this);
        //menu项，只有全部和未分类
        popupMenu.inflate(R.menu.menu_popup_repo_groups);
        //向menu上添加类别
        //1.拿到MENU
        Menu menu = popupMenu.getMenu();
        //2.拿到数据
        List<RepoGroup> repoGroups = repoGroupDao.queryForAll();
        for (RepoGroup repoGroup:repoGroups){
            menu.add(Menu.NONE,repoGroup.getId(),Menu.NONE,repoGroup.getName());
        }
        popupMenu.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        //设置对应类别标题
        String title = item.getTitle().toString();
        tvGroupType.setText(title);
        setData();
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private void setData(){

    }
}
