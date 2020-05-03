package com.example.shopping.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.app.GoodsInfoActivity;
import com.example.shopping.home.bean.GoodsBean;
import com.example.shopping.home.bean.resultBeanData;
import com.example.shopping.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentAdapter extends RecyclerView.Adapter {
    /**
     * 根据jsoN数据的类型
     * 排序
     */
    public static final  int BANNER = 0;
    public static final  int CHANNEL = 1;
    public static final  int ACT = 2;
//    public static final  int SECKILL = 3;
    public static final  int RECOMMEND = 3;
    public static final  int HOT = 4;
    /**
     * 初始化布局
     */
    private  LayoutInflater inflater;
    private  Context my_context;
    private  resultBeanData.ResultBean resultBean;

    /**
     * 当前类型
     */
    private int currentType = 0 ;
    private static final String GOODS_BEAN = "goodsBean";

    /**
     * HomeFragmentAdapter 构造方法 初始化 iflater context resultbean
     * @param my_context
     * @param resultBean
     */
    public HomeFragmentAdapter(Context my_context, resultBeanData.ResultBean resultBean) {

        this.my_context  = my_context;
        this.resultBean = resultBean;
        inflater = LayoutInflater.from(my_context);

    }

    /**
     * 等同Getview();
     * * 创建ViewHolder
     * 根据viewtype的不同创建不同的 bannerholder
     * @param parent
     * @param viewType 当前类型
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER){
            return new BannerViewHolder(my_context,inflater.inflate(R.layout.banner_viewpager,null));
        }else if (viewType == CHANNEL) {
            return new ChannelViewHolder(my_context, inflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
                return new ActViewHolder(my_context, inflater.inflate(R.layout.act_item, null));
        }
        else if(viewType == RECOMMEND ){
            return new RecommendViewHolder(my_context, inflater.inflate(R.layout.recommend_item, null));
        }else if(viewType == HOT){
            return new HotViewHolder(my_context, inflater.inflate(R.layout.hot_item,null));
        }
        return null;
    }

    /**
     * getView();中的绑定数据模块
     * 什么position得到什么viewtype
     * 再把holder强制转为BannerHolder
     * 绑定相应的数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (getItemViewType(position) ==BANNER){
        BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
        bannerViewHolder.setData(resultBean.getBanner_info());
    }else if(getItemViewType(position)==CHANNEL){
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        }
    else if (getItemViewType(position) == ACT) {
        ActViewHolder actViewHolder = (ActViewHolder) holder;
        actViewHolder.setData(resultBean.getAct_info());
    } else if(getItemViewType(position) == RECOMMEND){
        RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
        recommendViewHolder.setData(resultBean.getRecommend_info());
    } else if(getItemViewType(position)==HOT){
        HotViewHolder hotViewHolder = (HotViewHolder) holder;
        hotViewHolder.setData(resultBean.getHot_info());
    }
        /**
         * 里面的方法
         * 1.实例化一个holder
         * 2.设置相应的来自resultBean的数据
         */

    }
    /**
     * 总共的item
     * @return
     */
    @Override
    public int getItemCount() {
        //开发过程中从一到6
        return 5;
    }

    /**
     * 得到类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
//            case SECKILL:
//                currentType = SECKILL;
//                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }


    /**
     * 启动商品详情页面
     * 商品信息列表页面
     * @param goodsBean
     */

    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(my_context, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goodsBean);
        my_context.startActivity(intent);

    }

    /**
     * 创建所需的holder类
     * 里面设置数据
     * 构造方法
     * 联网请求
     * 点击事件
     */
    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Context my_context;
        private Banner banner;

        public BannerViewHolder(Context my_context, View itemView) {
                super(itemView);
                this.my_context = my_context;
                this.banner = itemView.findViewById(R.id.banner);
        }

        public void setData(List<resultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //设置banner的数据

            //得到图片list地址
            List<String> imagesUrl = new ArrayList<>();
            for (int i= 0;i<banner_info.size();i++){
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }
            /**
             * 设置循环指示点
             * 图片下的小圆标
             */
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            /**
             * 设置动画 设置图片
             */
            banner.setBannerAnimation(Transformer.ZoomOutSlide);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    //联网请求图片
                    Glide.with(my_context).load(Constants.IMG_URL+url).into(view);
                }
            });
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    /**
                     * 弹出提示框position
                     */
//                    Toast.makeText(my_context,"position=="+position,Toast.LENGTH_SHORT).show();
//                    startGoodsInfoActivity(goodsBean);
                }
            });
        }

    }


    private class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context my_context;
        private GridView gv_channel;
        private ChannelAdapter channelAdapter;

        public ChannelViewHolder(Context my_context, View itemView) {
            super(itemView);
            this.my_context = my_context;
            gv_channel =  itemView.findViewById(R.id.gv_channel);

            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    Toast.makeText(my_context, "position" + position, Toast.LENGTH_SHORT).show();
//                    startGoodsInfoActivity(goodsBean);
                }
            });
        }

        public void setData(List<resultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            //得到数据后设置GridView的适配器
            channelAdapter = new ChannelAdapter(my_context,channel_info);
            gv_channel.setAdapter(channelAdapter);


        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager act_viewpager;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<resultBeanData.ResultBean.ActInfoBean> act_info) {
            act_viewpager.setPageMargin(20);
            act_viewpager.setOffscreenPageLimit(3);//>=3

            //setPageTransformer 决定动画效果 第三方库
//            act_viewpager.setPageTransformer(true, new
//                    ScaleInTransformer());
            //1.有数据了
            //2.设置适配器
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                /**
                 *
                 * @param view 页面
                 * @param object instantiateItem方法返回的值
                 * @return
                 */
                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                /**
                 *
                 * @param container ViewPager
                 * @param position 对应页面的位置
                 * @return
                 */
                @Override
                public Object instantiateItem(ViewGroup container, final int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    Glide.with(mContext).load(Constants.IMG_URL + act_info.get(position).getIcon_url()).into(imageView);
                    //添加到容器中
                    container.addView(imageView);

                    //设置点击事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
//                            startGoodsInfoActivity(goodsBean);

                        }
                    });


                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder{

        private  Context my_context;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private RecommendGridViewAdapter adapter;

        public RecommendViewHolder(Context my_context, View itemView) {

            super(itemView);
            this.my_context = my_context;
            /**
             * 把xml中view 标签实例化 为了往里面传入数据
             */
            tv_more_recommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);


        }

        public void setData(final List<resultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            //1.有数据了
            //2.设置适配器
            /**
             * 适配器中设置在主页的显示和数据
             */
            adapter = new RecommendGridViewAdapter(my_context,recommend_info);
            gv_recommend.setAdapter(adapter);
            /**
             * 点击事件后创建 goodsbean
             * 调用goodsactivty函数跳转页面
             */
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(my_context, "position=="+position, Toast.LENGTH_SHORT).show();
                    resultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(position);

                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setFigure(recommendInfoBean.getFigure());
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }

    private class HotViewHolder extends RecyclerView.ViewHolder {

        private  Context my_context;

        private TextView tv_more_hot;
        private GridView gv_hot;
        private HotGridViewAdapter adapter;

        public HotViewHolder(Context my_context, View itemView) {
            super(itemView);
            this.my_context = my_context;
            tv_more_hot = (TextView) itemView.findViewById(R.id.tv_more_hot);
            gv_hot = (GridView) itemView.findViewById(R.id.gv_hot);
        }

        public void setData(final List<resultBeanData.ResultBean.HotInfoBean> hot_info) {
            //1.有数据
            //2.设置GridView的适配器
            adapter = new HotGridViewAdapter(my_context,hot_info);
            gv_hot.setAdapter(adapter);


            //设置item的监听
            /**
             * 主页中热卖的点击事件
             */
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(my_context, "position=="+position, Toast.LENGTH_SHORT).show();
//                    startGoodsInfoActivity();
//                    //热卖商品信息类
                    /**
                     * 根据点击的position获得相应的hotinfo 信息
                     * 再用这些信息实例化goodsbean
                     */

                    resultBeanData.ResultBean.HotInfoBean hotInfoBean =  hot_info.get(position);
//                    //商品信息类
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(hotInfoBean.getCover_price());
                    goodsBean.setFigure(hotInfoBean.getFigure());
                    goodsBean.setName(hotInfoBean.getName());
                    goodsBean.setProduct_id(hotInfoBean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }

    }



}
