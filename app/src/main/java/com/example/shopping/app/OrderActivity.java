package com.example.shopping.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.shopping.Myorders.adapter.myOrderAdapter;
import com.example.shopping.Myorders.bean.Orderbean;
import com.example.shopping.R;
import com.example.shopping.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.shopping.app.MyApplication.getContext;

public class OrderActivity extends Activity implements View.OnClickListener {

    private TextView tvOrderEdit;
    private RecyclerView recyclerview;
    private LinearLayout llDelete;
    private Button btnDelete;
    private ImageButton ibMyOrderBack;
    private myOrderAdapter adapter;
    private Context context;

    private String  userId;

    //编辑状态
    private static final int ACTION_EDIT = 1;
    //完成状态
    private static final int ACTION_COMPLETE = 2;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        context=getContext();
        getUser();
        findViews();
        initListener();
        getOrderJson();
    }

    private void getUser() {
        Intent intent = getIntent();
        String userBeanId = intent.getStringExtra("userBeanId");
        if (userBeanId==null){
            finish();
        }
        userId = userBeanId;
        System.out.println(userId);

    }


    private void getOrderJson() {

        String url = Constants.TEST_URL+"order/MedOrderByUid";
        OkHttpUtils
                .post()
                .url(url)
                .addParams("uid", userId)
                .build()
                .execute(new StringCallback()
                {
                    /**
                     * 请求失败 回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"订单请求服务器失败"+e.getMessage());

                    }

                    /**
                     * 联网成功时
                     * @param response 请求成功数据
                     * @param id
                     */

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"订单请求服务器成功!");
                        showdata(response);

                    }

                });
    }

    /**
         * 流程
         * 1.查看订单是否有数据
         * 2.有数据显示管理界面
         * 3.设置订单适配器 放入订单数据  编辑界面的全选
         */
    private void showdata(String orderJson) {

       final Orderbean orderbean = JSON.parseObject(orderJson, Orderbean.class);
        List<Orderbean.MedOrderBean> med_order = orderbean.getMedOrder();

        if (med_order!=null&&med_order.size()>0){
            tvOrderEdit.setVisibility(View.VISIBLE);
            adapter = new myOrderAdapter(context,med_order);
            recyclerview.setAdapter(adapter);
            LinearLayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            recyclerview.setLayoutManager(layout);

        }else {
            tvOrderEdit.setVisibility(View.GONE);
        }
    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-05-21 12:24:57 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        /**
         * 管理按钮
         * 订单视图
         * 删除试图布局LinerLayout
         * 编辑界面全选按钮
         * 删除按钮
         */
        tvOrderEdit = (TextView)findViewById( R.id.tv_order_edit );
        recyclerview = (RecyclerView)findViewById( R.id.recyclerview );
        llDelete = (LinearLayout)findViewById( R.id.ll_delete );
        btnDelete = (Button)findViewById( R.id.btn_delete );
        ibMyOrderBack = findViewById(R.id.ib_myOrder_back);

        tvOrderEdit.setOnClickListener(this);
        ibMyOrderBack.setOnClickListener(this);
        btnDelete.setOnClickListener( this );

    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2020-05-21 12:24:57 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == btnDelete ) {
            // Handle clicks for btnDelete
            recyclerview.invalidate();
        } else if (v==ibMyOrderBack){
            finish();
        }else if (v==tvOrderEdit){

        }
    }

    private void initListener() {
        //设置默认的编辑状态
        tvOrderEdit.setTag(ACTION_EDIT);
        tvOrderEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = (int) v.getTag();
                if (action == ACTION_EDIT) {
                    //切换为完成状态
                    showDelete();
                } else {
                    //切换成编辑状态
                    hideDelete();
                }
            }
        });


    }

    private void hideDelete() {
        //1.设置状态和文本-编辑
        tvOrderEdit.setTag(ACTION_EDIT);
        tvOrderEdit.setText("管理");
        //2.全部变成勾选
        if (adapter != null) {

        }
        //3.删除视图显示
        llDelete.setVisibility(View.GONE);

    }

    private void showDelete() {
        //1.设置状态和文本-完成
        tvOrderEdit.setTag(ACTION_COMPLETE);
        tvOrderEdit.setText("完成");
        //2.变成非勾选
        //3.删除视图显示
        if (adapter != null) {

        }
        llDelete.setVisibility(View.VISIBLE);


    }




}
