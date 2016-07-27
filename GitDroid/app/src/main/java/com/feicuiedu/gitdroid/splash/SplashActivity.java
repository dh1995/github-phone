package com.feicuiedu.gitdroid.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.feicuiedu.gitdroid.MainActivity;
import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.commons.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dh1 on 2016/7/26.
 */
public class SplashActivity extends AppCompatActivity {
//    @Bind(R.id.btnLogin)
//    Button btnLogin;
//    @Bind(R.id.btnEnter)
//    Button btnEnter;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        activityUtils = new ActivityUtils(this);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btnLogin)
    public void login(){

    }

    @OnClick(R.id.btnEnter)
    public void enter(){
        activityUtils.startActivity(MainActivity.class);
        finish();
    }
}
