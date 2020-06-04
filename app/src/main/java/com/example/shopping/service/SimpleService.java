package com.example.shopping.service;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.shopping.app.MainActivity;
import com.example.shopping.utils.Notify;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;

public class SimpleService extends Service {

    public static final String TAG = "SimpleService";
    private boolean Flag=false;
    private Context context;
    
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        context=getApplicationContext();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        new Thread(new Runnable() {
            @SuppressLint("WrongConstant")
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                while (!Flag){
                    try {

                        Log.e("通知发送时间为：",getCurTIme());
                        Intent it = new Intent(context, MainActivity.class);
                        PendingIntent pit = PendingIntent.getActivity(context, 0, it, 0);
                        sleep(5000);
                        Notify notify = new Notify(context,pit);
                        notify.addNotify("test","its just a test!");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 任务逻辑
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Flag=true;
        Log.d(TAG, "onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private String getCurTIme() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date();
        return sdf1.format(d1);
    }



}
