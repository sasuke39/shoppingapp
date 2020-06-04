package com.example.shopping.user.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.app.LoginActivity;
import com.example.shopping.app.MainActivity;
import com.example.shopping.app.MyApplication;
import com.example.shopping.app.OrderActivity;
import com.example.shopping.app.UserInfoActivity;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.service.SimpleService;
import com.example.shopping.user.bean.UserBean;
import com.example.shopping.utils.Constants;
import com.google.gson.Gson;

import java.util.Objects;

import static com.example.shopping.app.MyApplication.setUser;

public class UserFragment extends BaseFragment implements View.OnClickListener {
    private ImageButton userIcon;
    private TextView tvUsername;
    private TextView tvAllOrder;
    private TextView tvUserPay;
    private TextView tvUserReceive;
    private TextView tvUserFinish;
    private TextView tvUserDrawback;
    private TextView tvUserLocation;
    private TextView tvUserCollect;
    private TextView tvUserCoupon;
    private TextView tvUserScore;
    private TextView tvUserPrize;
    private TextView tvUserTicket;
    private TextView tvUserInvitation;
    private TextView tvUserCallcenter;
    private TextView tvUserFeedback;
    private TextView tvUsercenter;
    private TextView userNotify;
    private ImageButton ibUserSetting;
    private ImageButton ibUserMessage;
    private ScrollView scrollView;

    private Button userShutdown;


    private UserBean.MedUserBean medUserBean;


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-10-08 09:07:21 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     *
     * @param view
     */
    private void findViews(View view) {
        userIcon = (ImageButton) view.findViewById(R.id.user_icon);
        tvUsername = (TextView) view.findViewById(R.id.tv_username);
        tvAllOrder = (TextView) view.findViewById(R.id.tv_all_order);
        tvUserPay = (TextView) view.findViewById(R.id.tv_user_pay);
        tvUserReceive = (TextView) view.findViewById(R.id.tv_user_receive);
        tvUserFinish = (TextView) view.findViewById(R.id.tv_user_finish);
        tvUserDrawback = (TextView) view.findViewById(R.id.tv_user_drawback);
        tvUserLocation = (TextView) view.findViewById(R.id.tv_user_location);
        tvUserCollect = (TextView) view.findViewById(R.id.tv_user_collect);
//        tvUserCoupon = (TextView) view.findViewById(R.id.tv_user_coupon);
//        tvUserScore = (TextView) view.findViewById(R.id.tv_user_score);
//        tvUserPrize = (TextView) view.findViewById(R.id.tv_user_prize);
//        tvUserTicket = (TextView) view.findViewById(R.id.tv_user_ticket);
//        tvUserInvitation = (TextView) view.findViewById(R.id.tv_user_invitation);
//        tvUserCallcenter = (TextView) view.findViewById(R.id.tv_user_callcenter);
//        tvUserFeedback = (TextView) view.findViewById(R.id.tv_user_feedback);
        tvUsercenter = (TextView) view.findViewById(R.id.tv_usercenter);
        ibUserSetting = (ImageButton) view.findViewById(R.id.ib_user_setting);
        ibUserMessage = (ImageButton) view.findViewById(R.id.ib_user_message);
        scrollView = (ScrollView) view.findViewById(R.id.scrollview);
        userShutdown = view.findViewById(R.id.user_shutdown);
        view.findViewById(R.id.user_icon);

        userNotify=view.findViewById(R.id.user_notify);


        tvAllOrder.setOnClickListener(this);
        userShutdown.setOnClickListener(this);
        userIcon.setOnClickListener(this);
        ibUserSetting.setOnClickListener(this);
        ibUserMessage.setOnClickListener(this);
        userNotify.setOnClickListener(this);
        tvUsername.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2016-10-08 09:07:21 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == userIcon) {
            if (MyApplication.isIfLogin()) {
                Intent intent = new Intent(my_Context, UserInfoActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(my_Context, "请先登录哦！", Toast.LENGTH_SHORT).show();
            }
        } else if (v == ibUserSetting) {
            Toast.makeText(my_Context, "设置", Toast.LENGTH_SHORT).show();
        } else if (v == ibUserMessage) {
//            Intent intent = new Intent(my_Context, MessageCenterActivity.class);
//            startActivity(intent);
        }else if (v == userShutdown){
            if (MyApplication.isIfLogin()) {
                shutDownUser();
            }else Toast.makeText(my_Context, "请先登录哦！", Toast.LENGTH_SHORT).show();
        }else if (v==tvAllOrder){
            System.out.println(!MyApplication.isIfLogin());
            startOrdersActivity();
        }else if (v==userNotify){
            Intent startIntent = new Intent(getContext(), SimpleService.class);
            startIntent.putExtra("start","1");
            Objects.requireNonNull(getActivity()).startService(startIntent);
        }else if (v==tvUsername){
            if (!MyApplication.isIfLogin()) {
                Intent intent = new Intent(my_Context, LoginActivity.class);
                startActivityForResult(intent, 0);
            }
        }
    }

    private void startOrdersActivity() {
        if (MyApplication.isIfLogin()) {
            System.out.println(medUserBean);
            Intent intent = new Intent(this.getActivity(), OrderActivity.class);
            intent.putExtra("userBeanId",String.valueOf(medUserBean.getId()));
            startActivity(intent);
        }else Toast.makeText(my_Context, "请先登录哦！", Toast.LENGTH_SHORT).show();

    }

    private void shutDownUser() {


        Intent intent10=new Intent(getActivity(), MainActivity.class);
        MyApplication.setIfLogin(false);
        intent10.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent10);

    }



    @Override
    public View initView() {
        View view = View.inflate(my_Context, R.layout.fragment_user, null);
        findViews(view);
        tvUsercenter.setAlpha(0);
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initData() {

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("Range")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int[] location = new int[2];
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE://下滑是正，上滑是负
                        userIcon.getLocationOnScreen(location);//初始状态为125,即最大值是125，全部显示不透明是（40？）
                        float i = (location[1] - 40) / 85f;
                        tvUsercenter.setAlpha(1 - i);
                        break;
                }
                return false;
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==1) {
            Log.i("是否执行", "999");
            String str = data.getExtras().getString("MED_USER");
            medUserBean = new Gson().fromJson(str, UserBean.MedUserBean.class);
            IntoData(medUserBean);
            setUser(medUserBean);
        }else if(resultCode==2) {
            String str = data.getExtras().getString("MSG");
            Toast.makeText(my_Context,str , Toast.LENGTH_SHORT).show();
        }

    }
    public void IntoData(UserBean.MedUserBean medUserBean){
        MyApplication.setUser(medUserBean);
        tvUsername.setText(medUserBean.getUsername());
        Glide.with(my_Context).load(Constants.IMG_User_icon + medUserBean.getUser_icon()).into(userIcon);
        MyApplication.setIfLogin(true);
    }
}

