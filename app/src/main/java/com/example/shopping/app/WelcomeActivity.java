package com.example.shopping.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.shopping.R;

public class WelcomeActivity extends Activity { //去掉App就没有导航条

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //进入主页面两秒钟
        new Handler().postDelayed(() -> {
            //执行在主线程
            //启动主页面
            startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
            //关闭当前页面
            finish();
        },500);
    }
}
