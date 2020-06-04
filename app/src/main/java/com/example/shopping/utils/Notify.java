package com.example.shopping.utils;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.shopping.R;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.constraintlayout.widget.Constraints.TAG;


public class Notify {

    private NotificationManager manager;
    private Notification.Builder builder;
    private Context context;
    private PendingIntent pit;

    public Notify(Context context, PendingIntent pit){
        this.context=context;
        this.pit =pit;
    }


    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addNotify(String title, String detail) {
        manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        // set channel  chanenel的创建
        String CHANNEL_ONE_ID = "com.zhangshiling.app";
        String CHANNEL_ONE_NAME = "Channel One";
        NotificationChannel notificationChannel;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
                    CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
            /*notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);*/
            NotificationManager nm = context.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(notificationChannel);
        }

        builder = new Notification.Builder(context).setContentTitle(title)
                .setChannelId(CHANNEL_ONE_ID)
                .setContentText(detail)
                .setContentIntent(pit)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setVibrate(new long[]{0, 1000, 1000, 1000}) //通知栏消息震动
                .setLights(Color.GREEN, 1000, 2000) //通知栏消息闪灯(亮一秒间隔两秒再亮)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_launcher_foreground);
        Log.i(TAG, "after build a builder");
        //manager.notify(1, builder.getNotification());
        NotificationManagerCompat new_nm = NotificationManagerCompat.from(context);
        new_nm.notify(1, builder.build());  // 第一个参数1具体实现时 需要修改 用于显示不同消息。
        //return builder.getNotification();
    }

}
