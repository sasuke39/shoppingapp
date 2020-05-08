package com.example.shopping.app;

import android.app.Application;
import android.content.Context;


//import com.zhy.http.okhttp.OkHttpUtils;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {
    private static Context my_context;

    @Override
    public void onCreate() {
        super.onCreate();
        my_context =this;
        /**
         * 初始化OkhttpUtils
         */
        initOkhttpClient();
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
//        Response response = okHttpClient.newCall(request).execute(); //获取服务器返回的数据
//        String responseData = response.body().string();


        OkHttpUtils.initClient(okHttpClient);
    }

    public static Context getContext() {
        return my_context;
    }
}
