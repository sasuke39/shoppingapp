package com.example.shopping.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.home.bean.MedreslutBeanData;
import com.example.shopping.utils.Constants;

import java.util.List;

/**
 * 该适配器里有上下文 以及 相应的 数据
 */
public class RecommendGridViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<MedreslutBeanData.ResultMedcineBean.HreatMedInfoBean> datas;

    /**
     * 适配器构造方法 初始化数据和上下文
     * @param mContext
     * @param recommend_info
     */
    public RecommendGridViewAdapter(Context mContext, List<MedreslutBeanData.ResultMedcineBean.HreatMedInfoBean> recommend_info) {
        this.mContext = mContext;
        this.datas = recommend_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * 设置convetView 空和不为空时
         * 1.设置上下文和xml文件
         * 2.添加设置好的viewholder
         */
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_recommend = (ImageView) convertView.findViewById(R.id.iv_recommend);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到对应的数据
        MedreslutBeanData.ResultMedcineBean.HreatMedInfoBean recommendInfoBean = datas.get(position);
        Glide.with(mContext).load(Constants.IMG_Med +recommendInfoBean.getImg_url()).into(viewHolder.iv_recommend);
        viewHolder.tv_name.setText(recommendInfoBean.getProduct_name());
        viewHolder.tv_price.setText( "￥"+recommendInfoBean.getCover_price());

        return convertView;

    }

    static class ViewHolder{
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;

    }
}
