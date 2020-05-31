package com.example.shopping.Myorders.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping.Myorders.bean.Orderbean;
import com.example.shopping.R;
import com.example.shopping.utils.Constants;

import java.util.List;

public class myOrderAdapter extends RecyclerView.Adapter<myOrderAdapter.viewHolder> {


    private final Context mContext;
    private final List<Orderbean.MedOrderBean> datas;
    //完成状态下的删除CheckBox
    private Boolean ifshowselected =Boolean.FALSE;


    public myOrderAdapter(Context context, List<Orderbean.MedOrderBean> med_order) {
        datas=med_order;
        mContext=context;
        setListener();
    }




    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_order, null);
        return new myOrderAdapter.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        final Orderbean.MedOrderBean orderBean = datas.get(position);
        Orderbean.MedOrderBean.MedicineBean medicine = orderBean.getMedicine();
        System.out.println(orderBean);
        holder.cbGov.setChecked(orderBean.getSelectEd());
        Glide.with(mContext).load(Constants.IMG_Med_TEST + medicine.getImg_url()).into(holder.ivGov);
        holder.tvDescGov.setText(medicine.getProduct_name());
        holder.tvPriceGov.setText("￥"+medicine.getCover_price());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }



    class viewHolder extends RecyclerView.ViewHolder {
        //是否选中
        private CheckBox cbGov;
        //货物图片
        private ImageView ivGov;
        //货物名称
        private TextView tvDescGov;
        //货物价格
        private TextView tvPriceGov;


        /**
         * Find the Views in the layout<br />
         * <br />
         * Auto-created on 2020-05-22 00:30:09 by Android Layout Finder
         * (http://www.buzzingandroid.com/tools/android-layout-finder)
         */



        public viewHolder(View itemView) {
            super(itemView);
            cbGov = (CheckBox)itemView.findViewById( R.id.cb_gov );
            ivGov = (ImageView)itemView.findViewById( R.id.iv_gov );
            tvDescGov = (TextView)itemView.findViewById( R.id.tv_desc_gov );
            tvPriceGov = (TextView)itemView.findViewById( R.id.tv_price_gov );
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
    /**
     * 点击item的监听者
     */
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


    /**
     * 根据位置找所点击的item
     * 1.获取货物数据
     * 2.商品选择状态取反
     * 3.传入一个pos位置，通知RecyclerView该pos位置已经失效，需要重新绘制和UI更新
     * 4.是否全选
     * 5.根据选择的计算总价
     */
    private void setListener() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //1.根据位置找到对应的Bean对象
                Orderbean.MedOrderBean orderbean = datas.get(position);
                //2.设置取反状态
                orderbean.setSelectEd(!orderbean.getSelectEd());
                //3.刷新状态

                notifyItemChanged(position);
                //4.校验是否全选
            }
        });
    }



}
