package com.example.shopping.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class CommonUtils {
    public static void showSoftInput(Context context ) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm!=null) imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static void hideSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm!=null)   imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }


}
