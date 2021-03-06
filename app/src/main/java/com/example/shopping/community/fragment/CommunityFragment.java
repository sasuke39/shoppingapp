package com.example.shopping.community.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


import com.example.shopping.base.BaseFragment;

import static android.content.ContentValues.TAG;

/**
 * 作用：发现的Fragment
 */
public class CommunityFragment extends BaseFragment {
    private TextView textView;

    @Override
    public View initView() {
        Log.e(TAG,"发现的Fragment的UI被初始化了");
        textView = new TextView(my_Context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"发现的Fragment的数据被初始化了");
        textView.setText("发现内容");
    }
}

