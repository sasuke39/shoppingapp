package com.example.shopping.home.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.shopping.R;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.home.adapter.HomeFragmentAdapter;
import com.example.shopping.home.bean.resultBeanData;
import com.example.shopping.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();
    /**
     * 初始化各个组件
     * 且绑定
     */


    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;

    private HomeFragmentAdapter adapter;

    /**
     * 返回的数据
     */
    private resultBeanData.ResultBean resultBean;

    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        /**
         * 获取fragment_home的view
         */
        View view = View.inflate(my_Context, R.layout.fragment_home, null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);

        //设置点击事件
        initListener();
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "主页数据被初始化了");
        getDataFromNet();
        //联网请求主页的数据
//        getDataFromNet();
    }

    /**
     * 获取数据
     * 成功回调解析函数
     */
    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
//                .addParams("username", "hyman")
//                .addParams("password", "123")
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
                        Log.e(TAG,"首页请求失败"+e.getMessage());

                    }

                    /**
                     * 联网成功时
                     * @param response 请求成功数据
                     * @param id
                     */

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"首页请求成功!"+response);

                        //解析数据
                        processData(response);
                    }

                });
    }



    private void processData(String json) {
        /**
         * 把json转化成一个类对象
         * 传来的json里的样式 不是数据 给转化成一个类
         */
        resultBeanData resultBeanData = JSON.parseObject(json, resultBeanData.class);
        /**
         * 获取类resultBeanData对象中resultBean对象
         */
        resultBean = resultBeanData.getResult();

        if(resultBean != null){
            //有数据
            //设置适配器
            adapter = new HomeFragmentAdapter(my_Context,resultBean);
            rvHome.setAdapter(adapter);

            /**
             * 设置浮标
             */
            GridLayoutManager manager =  new GridLayoutManager(my_Context,1);
            //设置跨度大小监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position <= 3){
                        //隐藏
                        ib_top.setVisibility(View.GONE);
                    }else{
                        //显示
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    //只能返回1
                    return 1;
                }
            });
            //设置布局管理者
            rvHome.setLayoutManager(manager);

        }else{
            //没有数据
        }
//        Log.e(TAG,"解析成功=="+resultBean.getHot_info().get(0).getName());
    }

    private void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ib_top.setOnClickListener(v -> {
            //回到顶部
            rvHome.scrollToPosition(0);
        });

        //搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(my_Context, "搜索", Toast.LENGTH_SHORT).show();
            }
        });

        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(my_Context, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
