package com.example.shopping.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.user.bean.UserBean;
import com.example.shopping.utils.Constants;

public class UserInfoActivity extends Activity implements View.OnClickListener {

    private ScrollView scrollview;
    private ImageButton userIcon;
    private TextView tvImageTip;
    private TextView tvUsername;
    private TextView tvUserPhone;
    private Switch swUserNotify;
    private TextView tvUserCheckUpdate;
    private TextView tvUserSet;
    private ImageButton btnBackToUserFragment;
    private Context context;
    private UserBean.MedUserBean medUserBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        this.medUserBean=MyApplication.getUSer();
        this.context = getApplicationContext();
        findViews();
        setData();
    }

    private void setData() {
        Glide.with(context).load(Constants.IMG_User_icon + medUserBean.getUser_icon()).into(userIcon);
        tvUsername.setText("用户名："+medUserBean.getUsername());
        tvUserPhone.setText("手机号："+medUserBean.getPhoneNumber());
    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-06-03 20:03:18 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        scrollview = (ScrollView)findViewById( R.id.scrollview );
        userIcon = (ImageButton)findViewById( R.id.user_icon );
        tvImageTip = (TextView)findViewById( R.id.tv_image_tip );
        tvUsername = (TextView)findViewById( R.id.tv_username );
        tvUserPhone = (TextView)findViewById( R.id.tv_user_phone );
        swUserNotify = (Switch)findViewById( R.id.sw_user_notify );
        tvUserCheckUpdate = (TextView)findViewById( R.id.tv_user_check_update );
        tvUserSet = (TextView)findViewById( R.id.tv_user_set );
        btnBackToUserFragment = (ImageButton)findViewById( R.id.btn_back_to_userFragment );

        userIcon.setOnClickListener( this );
        btnBackToUserFragment.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2020-06-03 20:03:18 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == userIcon ) {
            if (MyApplication.isIfLogin()) {
                Intent intent = new Intent(context, PhotoActivity.class);
                startActivity(intent);
            }
        } else if ( v == btnBackToUserFragment ) {
            // Handle clicks for btnBackToUserFragment
            finish();
        }
    }


}
