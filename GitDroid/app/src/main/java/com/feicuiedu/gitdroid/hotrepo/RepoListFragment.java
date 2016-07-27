package com.feicuiedu.gitdroid.hotrepo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.feicuiedu.gitdroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by dh1 on 2016/7/27.
 */
public class RepoListFragment extends Fragment {
    @Bind(R.id.lvRepos)
    ListView listView;
    @Bind(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @Bind(R.id.emptyView)
    TextView emptyView;
    @Bind(R.id.errorView)
    TextView errorView;
    private ArrayAdapter<String> adapter;
    // 用来做当前页面业务逻辑及视图更新的
    private RepoListPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter=new RepoListPresenter(this);
        adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                new ArrayList<String>()
        );
        listView.setAdapter(adapter);

        initPullToRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void initPullToRefresh() {
        //使用当前对象为key来记录上一次的刷新时间，如果来此下拉间隔时间太短，不会执行刷新
        ptrClassicFrameLayout.setLastUpdateTimeRelateObject(this);
        //关闭header所用时长
        ptrClassicFrameLayout.setDurationToCloseHeader(1500);
        //下拉刷新监听处理
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            //下拉时触发此方法
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                // 去做数据的加载，做具体的业务
                // 要抛开视图，到后台线程去做你的业务处理(数据刷新加载)
                presenter.refresh();
            }
        });
        //以下代码只修改header的样式，可选
        StoreHouseHeader header=new StoreHouseHeader(getContext());
        header.initWithString("I LIKE"+"JAVA");
        header.setPadding(0,60,0,60);
        //修改Ptr的HeaderView效果
        ptrClassicFrameLayout.setHeaderView(header);
        ptrClassicFrameLayout.addPtrUIHandler(header);
        ptrClassicFrameLayout.setBackgroundResource(R.color.colorRefresh);
    }
    //刷新方法
    // 视图上:
    // 显示内容 or 错误 or 空白 , 三选一
    public void showContentView(){
        ptrClassicFrameLayout.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }
    public void showErrorView(String errorMsg){
        ptrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }
    public void showEmptyView(){
        ptrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }
    //
    // 显示提示信息
    // 如：Toast， 直接在当前页面上页面
    public void showMessage(String msg){

    }
    //停止刷新
    public void stopRefresh(){
        ptrClassicFrameLayout.refreshComplete();
    }
    //
    // 刷新数据
    // 将后台线程更新加载到的数据，刷新显示到视图(listview)上来显示给用户看
    public void refreshData(List<String> data){
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
