package com.example.shopping.shoppingcar.utils;


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import com.example.shopping.app.MyApplication;
import com.example.shopping.home.bean.GoodsBean;
import com.example.shopping.utils.CacheUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class CartStorage {

    public static final String JSON_CART = "json_cart";
    private  static  CartStorage instance;
    private final Context mContext;
    //SparseArray的性能优于HashMap
    private SparseArray<GoodsBean> sparseArray;

    /**
     * 购物车构造函数
     * 1.传入上下文
     * 2.实例化一个sparesArray
     * 3.调用listToSparseArray函数。（稀疏数组）
     * @param context
     */
    private CartStorage(Context context){
        this.mContext = context;
        //把之前存储的数据读取
        sparseArray = new SparseArray<>(100);

        listToSparseArray();
    }

    /**
     * 从本地读取的数据加入到SparseArray中
     * 1.通过getAllData函数得到一个goodsbean类型的list
     * 2.遍历list,把里面所有的内容放入sparseArray对象中以key,value形式
     */
    private void listToSparseArray() {
        List<GoodsBean> goodsBeanList = getAllData();
        //把List数据转换成SparseArray
        for (int i= 0; i<goodsBeanList.size();i++){
            GoodsBean goodsBean = goodsBeanList.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);//（key，value）
        }

    }

    /**
     * 获取本地所有的数据
     * 1.实例化一个goodsBeanlist的list
     * 2.
     * @return
     */
    public List<GoodsBean> getAllData() {
        List<GoodsBean> goodsBeanList  = new ArrayList<>();
        //1.从本地获取
        Log.e("TAG", String.valueOf(mContext));
        String json = CacheUtils.getString(mContext,JSON_CART);//json_cart
        //2.使用Gson转换成列表
        //判断不为空的时候执行
        if(!TextUtils.isEmpty(json)){
            //把String转换成List
            goodsBeanList = new Gson().fromJson(json,new TypeToken<List<GoodsBean>>(){}.getType());
        }
        return goodsBeanList;
    }


    /**
     * 得到购车实例
     * @return
     */
    public static CartStorage getInstance(){
        if(instance == null){
            instance = new CartStorage(MyApplication.getContext());
        }
        return instance;
    }

    /**
     * 添加数据
     * @param goodsBean
     */
    public void addData(GoodsBean goodsBean){

        //1.添加到内存中SparseArray
        //如果当前数据已经存在，就修改成number递增
        GoodsBean tempData = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if(tempData != null){
            //内存中有了这条数据
            tempData.setNumber(tempData.getNumber()+1);
        }else{
            tempData = goodsBean;
            tempData.setNumber(1);
        }

        Log.e("TAG",tempData.toString());
        //同步到内存中
        sparseArray.put(Integer.parseInt(tempData.getProduct_id()),tempData);


        //2.同步到本地
        saveLocal();

    }

    /**
     * 删除数据
     * @param goodsBean
     */
    public void deleteData(GoodsBean goodsBean){
        //1.内存中删除
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));

        //2.把内存的保持到本地
        saveLocal();
    }


    /**
     * 更新数据
     * @param goodsBean
     */
    public void updateData(GoodsBean goodsBean){
        //1.内存中更新
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);

        //2.同步到本地
        saveLocal();
    }

    /**
     * 保持数据到本地
     * 1.通过sparseTolist函数返回一个由稀疏数组遍历得到的一个Goodsbeanlist
     * 2.转化为json
     * 3.保存在json_cart中
     */
    private void saveLocal() {
        //1.SparseArray转换成List
        List<GoodsBean>  goodsBeanList = sparseToList();
        //2.使用Gson把列表转换成String类型
        String json = new Gson().toJson(goodsBeanList);
        //3.把String数据保存
        CacheUtils.saveString(mContext,JSON_CART,json);

    }

    private List<GoodsBean> sparseToList() {
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        for (int i=0;i<sparseArray.size();i++){
            GoodsBean goodsBean = sparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }


}

