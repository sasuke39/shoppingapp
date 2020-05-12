package com.example.shopping.type.fragment;


import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.shopping.R;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.type.adapter.TagGridViewAdapter;
import com.example.shopping.type.bean.TagBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class TagFragment extends BaseFragment {

    private GridView gv_tag;
    private TagGridViewAdapter adapter;
    private List<TagBean.ResultBean> result;

    @Override
    public View initView() {
        View view = View.inflate(my_Context, R.layout.fragment_tag, null);
        gv_tag = (GridView) view.findViewById(R.id.gv_tag);

        return view;
    }

    @Override
    public void initData() {
        getDataFromNet();

    }


    public void getDataFromNet() {
        OkHttpUtils
                .get()
//                .url(Constants.TAG_URL)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {


        @Override
        public void onBefore(Request request, int id) {
        }

        @Override
        public void onAfter(int id) {
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("TAG", "联网失败" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {

            switch (id) {
                case 100:
//                    Toast.makeText(mContext, "http", Toast.LENGTH_SHORT).show();
                    if (response != null) {
                        processData(response);
                        adapter = new TagGridViewAdapter(my_Context, result);
                        gv_tag.setAdapter(adapter);
                    }
                    break;
                case 101:
                    Toast.makeText(my_Context, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }

    private void processData(String json) {
        Gson gson = new Gson();
        TagBean tagBean = gson.fromJson(json, TagBean.class);
        result = tagBean.getResult();
    }

}
