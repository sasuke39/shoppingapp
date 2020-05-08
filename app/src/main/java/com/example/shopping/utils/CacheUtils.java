package com.example.shopping.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * 作用：缓存工具类
 */
public class CacheUtils {



    /**
     * 得到保持的String类型的数据
     * SharedPreferences
     * 1.利用它存储一些键值对(Key-Value)参数
     * 2.通过getSharedPreference(String,int)方法,获取SharedPreference的对象
     * 3.第一个参数用于指定该存储文件的名称，不用加后缀，第二个参数指定文件的操作模式。
     * 一般用MODE_PRIVATE 私有方式存储，其他应用无法访问。
     * 4..数据存储在Android系统的 /data/data/"app package name"/shared_prefs 目录下的一个.xml文件。
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("shoppingPro", Context.MODE_PRIVATE);
        return sp.getString(key,"");

    }


    /**
     * 保存String类型数据
     * @param context 上下文
     * @param key
     * @param value 保持的值
     */
    public static void saveString(Context context, String key,String value) {
        SharedPreferences sp = context.getSharedPreferences("shoppingPro",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).apply();
    }
}
