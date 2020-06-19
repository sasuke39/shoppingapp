package com.example.shopping.type.fragment.childfragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.shopping.R;
import com.example.shopping.app.GoodsInfoActivity;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.home.bean.GoodsBean;
import com.example.shopping.type.adapter.MyTypeChildAdapter;
import com.example.shopping.type.bean.MyTypeBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.shopping.utils.Constants.TEST_URL;


public class type2ChildFg extends BaseFragment {
    private String FragmentName="分类二";
    private GridView typeList;
    private MyTypeChildAdapter adapter;
    private TextView type_text;
    @Override
    public View initView() {
        View view =View.inflate(my_Context, R.layout.type_fl1,null);
        typeList= view.findViewById(R.id.gv_type);
        type_text=view.findViewById(R.id.type_text);
        return view;
    }


    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }


    private void getDataFromNet() {
        String url = TEST_URL+"Medicine/getMedicineByType";
        OkHttpUtils
                .get()
                .addParams("type","皮肤科药")
                .url(url)
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
                        Log.e(TAG,FragmentName+"请求失败"+e.getMessage());

                    }

                    /**
                     * 联网成功时
                     * @param response 请求成功数据
                     * @param id
                     */

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,FragmentName+"请求成功!");

                        //解析数据
                        processData(response);
                    }

                });
    }


    private void processData(String response) {
        MyTypeBean myTypeBean = JSON.parseObject(response, MyTypeBean.class);
        if (myTypeBean.getCode()==200) {
            final List<MyTypeBean.MedicineBeanListBean> medicineBeanList = myTypeBean.getMedicineBeanList();
            if (medicineBeanList!=null||!medicineBeanList.toString().equals("[]")){
                adapter = new MyTypeChildAdapter(my_Context, medicineBeanList);
                typeList.setAdapter(adapter);
                type_text.setText("皮肤科药");

                typeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MyTypeBean.MedicineBeanListBean listBean = medicineBeanList.get(position);
//                    //商品信息类
                        GoodsBean goodsBean = new GoodsBean();
                        goodsBean.setCover_price(String.valueOf(listBean.getCover_price()));
                        goodsBean.setFigure(listBean.getImg_url());
                        goodsBean.setName(listBean.getProduct_name());
                        goodsBean.setProduct_id(String.valueOf(listBean.getProduct_id()));
                        goodsBean.setDetails(listBean.getDetails());
                        startGoodsInfoActivity(goodsBean);


                    }
                });
            }

        }

        else {

        }
    }

    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(my_Context, GoodsInfoActivity.class);
        intent.putExtra("goodsBean",goodsBean);
        my_Context.startActivity(intent);
    }
}
