package com.example.shopping.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.home.bean.resultBeanData;
import com.example.shopping.utils.Constants;

import java.util.List;

public class ChannelAdapter extends BaseAdapter {
    private final Context my_context;
    private final List<resultBeanData.ResultBean.ChannelInfoBean>  datas;
    public ChannelAdapter(Context my_context, List<resultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        this.my_context = my_context;
        this.datas = channel_info;
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
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = View.inflate(my_context,R.layout.item_channel,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = convertView.findViewById(R.id.iv_channel);
            viewHolder.tv_title = convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        resultBeanData.ResultBean.ChannelInfoBean channelInfoBean = datas.get(position);
        Glide.with(my_context).load(Constants.IMG_URL +channelInfoBean.getImage()).into(viewHolder.iv_icon );
        viewHolder.tv_title.setText(channelInfoBean.getChannel_name());
        return convertView;
    }
    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
    }
}
