package com.example.shopping.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
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
    public static final  int SECKILL = 3;
    public static final  int RECOMMEND = 4;
    public static final  int HOT = 5;
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

    public HomeFragmentAdapter(Context my_context, resultBeanData.ResultBean resultBean) {

        this.my_context  = my_context;
        this.resultBean = resultBean;
        inflater = LayoutInflater.from(my_context);

    }


    /**
     * 等同Getview();
     * * 创建ViewHolder
     * @param parent
     * @param viewType 当前类型
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER){
            return new BannerViewHolder(my_context,inflater.inflate(R.layout.banner_viewpager,null));
        }
        return null;
    }

    /**
     * getView();中的绑定数据模块
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (getItemViewType(position) ==BANNER){
        BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
        bannerViewHolder.setData(resultBean.getBanner_info());
    }
    }

    /**
     * 创建所需的holder类
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
                    Toast.makeText(my_context,"position=="+position,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



    /**
     * 总共的item
     * @return
     */
    @Override
    public int getItemCount() {
        //开发过程中从一到6
        return 1;
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
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }
}
