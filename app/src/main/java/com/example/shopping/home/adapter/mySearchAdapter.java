package com.example.shopping.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.app.GoodsInfoActivity;
import com.example.shopping.home.bean.GoodsBean;
import com.example.shopping.home.bean.ResultSearchBean;
import com.example.shopping.utils.Constants;

import java.util.List;

public class mySearchAdapter extends RecyclerView.Adapter<mySearchAdapter.viewHolder> {

    private final Context mContext;
    private final List<ResultSearchBean.MedicinesBean> datas;
    private static final String GOODS_BEAN = "goodsBean";
    //完成状态下的删除CheckBox


    public mySearchAdapter(Context context, List<ResultSearchBean.MedicinesBean> datas) {
        this.datas=datas;
        mContext=context;
        setListener();
    }




    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_search, null);
        return new mySearchAdapter.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        final ResultSearchBean.MedicinesBean medicinesBean = datas.get(position);

        Glide.with(mContext).load(Constants.IMG_Med_TEST + medicinesBean.getImg_url()).into(holder.ivGood);
        holder.tvGoodname.setText(medicinesBean.getProduct_name());
        holder.tvPriceGood.setText("￥"+medicinesBean.getCover_price());
        holder.goodClassify.setText(medicinesBean.getType());
        holder.goodSalesVolume.setText("销量：1W+");
        holder.goodSendAddress.setText("发货地址:"+"上海");

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        private ImageView ivGood;
        private TextView tvGoodname;
        private TextView goodClassify;
        private TextView tvPriceGood;
        private TextView goodSalesVolume;
        private TextView goodSendAddress;

        /**
         * Find the Views in the layout<br />
         * <br />
         * Auto-created on 2020-06-13 21:14:13 by Android Layout Finder
         * (http://www.buzzingandroid.com/tools/android-layout-finder)
         */


        public viewHolder(View itemView) {
            super(itemView);
            ivGood = (ImageView)itemView.findViewById( R.id.iv_good );
            tvGoodname = (TextView)itemView.findViewById( R.id.tv_goodname );
            goodClassify = (TextView)itemView.findViewById( R.id.good_classify );
            tvPriceGood = (TextView)itemView.findViewById( R.id.tv_price_good );
            goodSalesVolume = (TextView)itemView.findViewById( R.id.good_sales_volume );
            goodSendAddress = (TextView)itemView.findViewById( R.id.good_send_address );

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });

        }
    }

    public interface  OnItemClickListener{
        /**
         * 当点击某条的时候被回调
         * @param position
         */
        public void  onItemClick(int position);
    }


    private OnItemClickListener onItemClickListener;


    /**
     * 设置item的监听
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    private void setListener() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //1.根据位置找到对应的Bean对象
                ResultSearchBean.MedicinesBean medicinesBean = datas.get(position);

                GoodsBean goodsBean = new GoodsBean();

                goodsBean.setDetails(medicinesBean.getDetails());
                goodsBean.setCover_price(String.valueOf(medicinesBean.getCover_price()));
                goodsBean.setFigure(medicinesBean.getImg_url());
                goodsBean.setName(medicinesBean.getProduct_name());
                goodsBean.setProduct_id(String.valueOf(medicinesBean.getProduct_id()));

                startGoodsInfoActivity(goodsBean);

            }
        });
    }

    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goodsBean);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);

    }
}
