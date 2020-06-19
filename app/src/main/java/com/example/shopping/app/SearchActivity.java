package com.example.shopping.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.shopping.R;
import com.example.shopping.home.adapter.mySearchAdapter;
import com.example.shopping.home.bean.ResultSearchBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.shopping.app.MyApplication.getContext;
import static com.example.shopping.utils.CommonUtils.hideSoftInput;
import static com.example.shopping.utils.Constants.TEST_URL;

public class SearchActivity extends Activity implements View.OnClickListener {
    private ConstraintLayout clToolbar;
    private ImageView ivBack;
    private EditText editSearch;
    private ImageView ivClearSearch;
    private TextView ivSearch;
    private FrameLayout frameLayout;
    private RecyclerView recyclerviewSearchItem;

    private Context context;

    private String searchWord ;

    private  ResultSearchBean resultSearchBean;

    private mySearchAdapter adapter;


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-06-13 19:33:26 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        clToolbar = (ConstraintLayout)findViewById( R.id.cl_toolbar );
        ivBack = (ImageView)findViewById( R.id.iv_back );
        editSearch = (EditText)findViewById( R.id.edit_searchPage );
        ivClearSearch = (ImageView)findViewById( R.id.iv_clear_search );
        ivSearch = (TextView)findViewById( R.id.iv_search );
        frameLayout = (FrameLayout)findViewById( R.id.frameLayout );
        recyclerviewSearchItem = (RecyclerView)findViewById( R.id.recyclerview_search_item );

        ivSearch.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        setContentView(R.layout.activity_search);
        findViews();
        searchWord = getIntent().getStringExtra("searchWord");
        if (searchWord!=null) {
            editSearch.setText(searchWord);
            startSearch(searchWord);
            Toast.makeText(context, searchWord, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        if (v==ivSearch){
            String record = editSearch.getText().toString();
            hideSoftInput(context,v);
            startSearch(record);
        }else if (v==ivBack){
            finish();
        }
    }


    private void startSearch(String searchWord) {
        String url = TEST_URL+"Medicine/searchMedicine";
        OkHttpUtils
                .post()
                .url(url)
                .addParams("searchWord", searchWord)
//                .addParams("password", "123")
                .build()
                .execute(new StringCallback() {
                    /**
                     * 请求失败 回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "搜索请求失败" + e.getMessage());

                    }

                    /**
                     * 联网成功时
                     * @param response 请求成功数据
                     * @param id
                     */

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "搜索请求成功!");

                        //解析数据
                        processSearchData(response);
                    }

                });
    }



    private void processSearchData(String response) {
        resultSearchBean = JSON.parseObject(response, ResultSearchBean.class);
        if (resultSearchBean.getCode()==200){
            final List<ResultSearchBean.MedicinesBean> medicines = resultSearchBean.getMedicines();
            if (medicines!=null&&medicines.size()>0){
                adapter = new mySearchAdapter(context,medicines);
                recyclerviewSearchItem.setAdapter(adapter);
                LinearLayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                recyclerviewSearchItem.setLayoutManager(layout);

            }

        }else {
            Toast.makeText(context, "未找到相关的搜索信息！请重试！", Toast.LENGTH_SHORT).show();
        }
    }



}

