package com.example.shopping.shoppingcar.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.Constraints;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.example.shopping.app.MyApplication;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.home.bean.GoodsBean;
import com.example.shopping.shoppingcar.Bean.Goods;
import com.example.shopping.shoppingcar.Bean.SimOrderBean;
import com.example.shopping.shoppingcar.adapter.ShoppingCartAdapter;
import com.example.shopping.shoppingcar.utils.CartStorage;
import com.example.shopping.utils.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

import static android.content.ContentValues.TAG;


/**
 * 作用：购物车的Fragment
 */
public class Shoppingcarfragment extends BaseFragment implements View.OnClickListener {

    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;

    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private LinearLayout ll_empty_shopcart;
    private ShoppingCartAdapter adapter;

    //编辑状态
    private static final int ACTION_EDIT = 1;
    //完成状态
    private static final int ACTION_COMPLETE = 2;

    private List<GoodsBean> goodsBeanList;


    /**
     * 初始化视图类似于findviews
     *
     * @return
     */
    @Override
    public View initView() {
        Log.e(TAG, "购物车的Fragment的UI被初始化了");
        View view = View.inflate(my_Context, R.layout.fragment_shopping_cart, null);
        tvShopcartEdit = (TextView) view.findViewById(R.id.tv_shopcart_edit);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) view.findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) view.findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) view.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) view.findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) view.findViewById(R.id.ll_delete);
        cbAll = (CheckBox) view.findViewById(R.id.cb_all);
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
        btnCollection = (Button) view.findViewById(R.id.btn_collection);

        ll_empty_shopcart = (LinearLayout) view.findViewById(R.id.ll_empty_shopcart);
        ivEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = (TextView) view.findViewById(R.id.tv_empty_cart_tobuy);

        btnCheckOut.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCollection.setOnClickListener(this);

        initListener();


        return view;
    }

    /**
     * 监听编辑按钮的点击
     */
    private void initListener() {
        //设置默认的编辑状态
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setOnClickListener(new View.OnClickListener() {
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
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");
        //2.全部变成勾选
        //3.checkAll 下面全选按钮设置全选状态
        if (adapter != null) {
            adapter.checkAll_none(true);
            adapter.checkAll();
            adapter.showTotalPrice();
        }
        //3.删除视图显示
        llDelete.setVisibility(View.GONE);
        //4.结算视图隐藏
        llCheckAll.setVisibility(View.VISIBLE);

    }

    private void showDelete() {
        //1.设置状态和文本-完成
        tvShopcartEdit.setTag(ACTION_COMPLETE);
        tvShopcartEdit.setText("完成");
        //2.全部变成变成非勾选
        if (adapter != null) {
            adapter.checkAll_none(false);
            adapter.checkAll();
        }
        //3.删除视图显示
        llDelete.setVisibility(View.VISIBLE);
        //4.结算视图隐藏
        llCheckAll.setVisibility(View.GONE);

    }


    @Override
    public void onClick(View v) {
        if (v == btnCheckOut) {
            // Handle clicks for btnCheckOut
            //结算
//            pay(v);
            if (MyApplication.isIfLogin()) {
                AddToMyOrder();
            }else Toast.makeText(my_Context, "请先登录！", Toast.LENGTH_SHORT).show();
        } else if (v == btnDelete) {
            // Handle clicks for btnDelete
            //删除选中的
            adapter.deleteData();
            //校验状态
            adapter.checkAll();
            //数据大小为0
            if (adapter.getItemCount() == 0) {
                emptyShoppingCart();
            }

        } else if (v == btnCollection) {
            // Handle clicks for btnCollection
        }
    }

    private void AddToMyOrder() {
            SimOrderBean simOrderBean = new SimOrderBean();
            List<Goods> goodsList = new ArrayList<Goods>();
            double Total = 0.0;
            goodsBeanList= null;
            goodsBeanList = CartStorage.getInstance().getAllData();

        int count = 0;
        for (GoodsBean goodsBean : goodsBeanList) {
            if (goodsBean.isSelected()){
                count++;
            Goods goods = new Goods();
            goods.setGoodName(goodsBean.getName());
            goods.setId(Integer.parseInt(goodsBean.getProduct_id()));
            goods.setNumber(goodsBean.getNumber());
            goodsList.add(goods);
            Total = Total + Double.parseDouble(goodsBean.getCover_price()) * (double) goodsBean.getNumber();
            }
        }

        if (count!=0) {
            simOrderBean.setUserId(MyApplication.getUSer().getId());
            simOrderBean.setAllGoods(goodsList.toString());
            simOrderBean.setTotal(Total);
            simOrderBean.setCreateTime(getCurTIme());
            System.out.println(simOrderBean);
            String json = new Gson().toJson(simOrderBean);
            String url = Constants.TEST_URL + "medUser/updateUsersOrder";
            //发送订单到服务器
            OkHttpUtils.postString()
                    .url(url)
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .content(json)
                    .build()
                    .execute(new StringCallback() {
                        /**
                         * 请求失败 回调
                         *
                         * @param call
                         * @param e
                         * @param id
                         */
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e(Constraints.TAG, "购买失败！" + e.getMessage());

                        }

                        /**
                         * 联网成功时
                         *
                         * @param response 请求成功数据
                         * @param id
                         */

                        @Override
                        public void onResponse(String response, int id) {
                            Log.e(Constraints.TAG, "订单设置成功!");
                            Toast.makeText(my_Context, "购买成功！前往订单查看状态", Toast.LENGTH_SHORT).show();
                        }

                    });
        }else Toast.makeText(my_Context, "请选中想要的商品后在选择！", Toast.LENGTH_SHORT).show();

    }

    private String getCurTIme() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = new Date();
        String str1 = sdf1.format(d1);
        System.out.println("订单创建时间为： " + str1);
        return str1;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "购物车的Fragment的数据被初始化了");


    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    /**
     * 显示数据
     */
    private void showData() {
        goodsBeanList = CartStorage.getInstance().getAllData();

        /**
         * 流程
         * 1.查看购物车是否有数据
         * 2.有数据显示编辑和结算界面
         * 3.隐藏空界面
         * 4.设置购物车适配器 放入物品数据 总价 全选 编辑界面的全选
         */
        if (goodsBeanList != null && goodsBeanList.size() > 0) {
            tvShopcartEdit.setVisibility(View.VISIBLE);
            llCheckAll.setVisibility(View.VISIBLE);
            //有数据
            //把当没有数据显示的布局-隐藏
            ll_empty_shopcart.setVisibility(View.GONE);

            //设置适配器
            adapter = new ShoppingCartAdapter(my_Context, goodsBeanList, tvShopcartTotal, checkboxAll, cbAll);
            recyclerview.setAdapter(adapter);

            //设置布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(my_Context, LinearLayoutManager.VERTICAL, false));


        } else {
            //没有数据
            //显示数据为空的布局
            emptyShoppingCart();
        }
    }

    private void emptyShoppingCart() {

        ll_empty_shopcart.setVisibility(View.VISIBLE);
        tvShopcartEdit.setVisibility(View.GONE);
        llDelete.setVisibility(View.GONE);
    }


}

