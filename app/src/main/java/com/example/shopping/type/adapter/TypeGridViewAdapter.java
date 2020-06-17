package com.example.shopping.type.adapter;

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

public class TypeGridViewAdapter extends BaseAdapter {

    private final List<MedreslutBeanData.ResultMedcineBean.SkinMedInfoBean> datas;
    private final Context my_context;

    /**
     * 构造方法
     * @param my_context
     * @param hot_info
     */
    public TypeGridViewAdapter(Context my_context, List<MedreslutBeanData.ResultMedcineBean.SkinMedInfoBean> hot_info) {
        this.my_context =  my_context;
        this.datas = hot_info;
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

    /**
     * 创建View
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TypeGridViewAdapter.ViewHolder viewHolder;
        if (convertView==null){
            convertView = View.inflate(my_context, R.layout.item_hot_grid_view,null);
            viewHolder = new TypeGridViewAdapter.ViewHolder();
            viewHolder.iv_hot = (ImageView) convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (TypeGridViewAdapter.ViewHolder) convertView.getTag();
        }

        /**
         * 解析数据
         * 根据position
         * 一个一个解析数据
         */
        MedreslutBeanData.ResultMedcineBean.SkinMedInfoBean hotInfoBean = datas.get(position);
        Glide.with(my_context).load(Constants.IMG_Med +hotInfoBean.getImg_url()).into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(hotInfoBean.getProduct_name());
        viewHolder.tv_price.setText("￥"+hotInfoBean.getCover_price());
        return convertView;

    }

    /**
     * 创建类中的viewHolder
     */
    static class ViewHolder{
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
