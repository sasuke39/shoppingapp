package com.example.shopping.type.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.type.bean.MyTypeBean;
import com.example.shopping.utils.Constants;

import java.util.List;

public class MyTypeChildAdapter extends BaseAdapter implements View.OnClickListener {

    private List<MyTypeBean.MedicineBeanListBean> datas;
    private Context my_context;

    public MyTypeChildAdapter(Context my_context, List<MyTypeBean.MedicineBeanListBean> type_info) {
        this.my_context =  my_context;
        this.datas = type_info;

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
        if (convertView==null){
            convertView = View.inflate(my_context, R.layout.type_item,null);
            viewHolder = new MyTypeChildAdapter.ViewHolder();
            viewHolder.iv_hot = (ImageView) convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyTypeChildAdapter.ViewHolder) convertView.getTag();
        }

        /**
         * 解析数据
         * 根据position
         * 一个一个解析数据
         */
        final MyTypeBean.MedicineBeanListBean medicineBeanListBean = datas.get(position);
        Glide.with(my_context).load(Constants.IMG_Med +medicineBeanListBean.getImg_url()).into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(medicineBeanListBean.getProduct_name());
        viewHolder.tv_price.setText("￥"+medicineBeanListBean.getCover_price());

        return convertView;

    }


    @Override
    public void onClick(View v) {

    }


//    private class viewHolder extends RecyclerView.ViewHolder {
//
//        private Context my_context;
//
//        private GridView gv_type;
//        private TypeGridViewAdapter adapter;
//
//        public viewHolder(Context my_context, View itemView) {
//            super(itemView);
//            this.my_context = my_context;
//            gv_type = (GridView) itemView.findViewById(R.id.gv_type);
//        }
//
//        public viewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//
//        public void setData(final List<MedreslutBeanData.ResultMedcineBean.SkinMedInfoBean> hot_info) {
//            //1.有数据
//            //2.设置GridView的适配器
//            adapter = new TypeGridViewAdapter(my_context,hot_info);
//            gv_type.setAdapter(adapter);
//
//
//            //设置item的监听
//            /**
//             * 主页中热卖的点击事件
//             */
//
//            gv_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                    Toast.makeText(my_context, "position=="+position, Toast.LENGTH_SHORT).show();
////                    startGoodsInfoActivity();
////                    //热卖商品信息类
//                    /**
//                     * 根据点击的position获得相应的hotinfo 信息
//                     * 再用这些信息实例化goodsbean
//                     */
//
//                    MedreslutBeanData.ResultMedcineBean.SkinMedInfoBean hotInfoBean =  hot_info.get(position);
////                    //商品信息类
//                    GoodsBean goodsBean = new GoodsBean();
//                    goodsBean.setCover_price(String.valueOf(hotInfoBean.getCover_price()));
//                    goodsBean.setFigure(hotInfoBean.getImg_url());
//                    goodsBean.setName(hotInfoBean.getProduct_name());
//                    goodsBean.setProduct_id(String.valueOf(hotInfoBean.getProduct_id()));
//                    goodsBean.setDetails(hotInfoBean.getDetails());
//                    startGoodsInfoActivity(goodsBean);
//                }
//            });
//        }
//
//    }


    static class ViewHolder{
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
