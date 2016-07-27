package com.feicuiedu.gitdroid.splash;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.splash.pager.Pager2;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by dh1 on 2016/7/26.
 */
public class SplashPagerFragment extends Fragment {


    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.indicator)
    CircleIndicator indicator;// 指示器
    @Bind(R.id.content)
    FrameLayout content;//当前页面Layout(主要为了更新其背景颜色)
    @Bind(R.id.ivPhoneBlank)
    ImageView ivPhoneBlank;
    @Bind(R.id.ivPhoneFont)
    ImageView ivPhoneFont;
    @Bind(R.id.layoutPhone)
    FrameLayout layoutPhone;//屏幕中间的手机
    private SplashPagerAdapter adapter;
    @BindColor(R.color.colorGreen) int colorGreen;
    @BindColor(R.color.colorRed) int colorRed;
    @BindColor(R.color.colorYellow) int colorYellow;
//    private int colorGreen;
//    private int colorRed;
//    private int colorYellow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_pager, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new SplashPagerAdapter(getContext());
//        colorGreen=getResources().getColor(R.color.colorGreen);
//        colorRed=getResources().getColor(R.color.colorRed);
//        colorYellow=getResources().getColor(R.color.colorYellow);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(pageChangeListener);
        viewPager.addOnPageChangeListener(phoneChangeListener);
    }

    //为了做背景颜色渐变处理
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        final ArgbEvaluator argbEvaluator=new ArgbEvaluator();
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //第一、二个页面之间
            if (position == 0 ){
                int color= (int) argbEvaluator.evaluate(positionOffset,colorGreen,colorRed);
                content.setBackgroundColor(color);
                return;
            }
            //第二、第三页面之间
            if (position == 1 ){
                int color= (int) argbEvaluator.evaluate(positionOffset,colorRed,colorYellow);
                content.setBackgroundColor(color);
            }

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    //为了做"手机"的动画效果处理(平移、缩放、透明度变化)
    private ViewPager.OnPageChangeListener phoneChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //第一、二个页面之间
            if (position == 0){
                //手机的缩放
                float scale = 0.3f + positionOffset * 0.7f;
                layoutPhone.setScaleX(scale);
                layoutPhone.setScaleY(scale);
                //手机的平移
                int scroll= (int) ((positionOffset-1) * 360);
                layoutPhone.setTranslationX(scroll);
                //手机字体的渐变
                ivPhoneFont.setAlpha(positionOffset);
                return;
            }
            //第二、第三个页面之间
            if (position == 1){
                layoutPhone.setTranslationX(-positionOffsetPixels);
            }

        }

        @Override
        public void onPageSelected(int position) {
        //滑到最后一个页面时播放动画
            if (position == 2){
                Pager2 pager2View= (Pager2) adapter.getView(position);
                pager2View.showAnimation();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
