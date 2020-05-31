package com.example.shopping.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.shopping.R;
import com.example.shopping.user.bean.UserBean;
import com.example.shopping.utils.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class LoginActivity extends Activity implements View.OnClickListener {
    private ImageButton ibLoginBack;
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private ImageButton ibLoginVisible;
    private Button btnLogin;
    private TextView tvLoginRegister;
    private TextView tvLoginForgetPwd;
    private ImageButton ib_weibo;
    private ImageButton ib_qq;
    private ImageButton ib_wechat;

    private Button btnRegister;

    private int isVisble;
    private String screen_name;
    private String profile_image_url;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-10-12 21:26:53 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        ibLoginBack = (ImageButton) findViewById(R.id.ib_login_back);
        etLoginPhone = (EditText) findViewById(R.id.et_login_phone);
        etLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        ibLoginVisible = (ImageButton) findViewById(R.id.ib_login_visible);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvLoginRegister = (TextView) findViewById(R.id.tv_login_register);
        tvLoginForgetPwd = (TextView) findViewById(R.id.tv_login_forget_pwd);
        ib_weibo = (ImageButton) findViewById(R.id.ib_weibo);
        ib_qq = (ImageButton) findViewById(R.id.ib_qq);
        ib_wechat = (ImageButton) findViewById(R.id.ib_wechat);
        btnRegister=findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(this);
        ibLoginBack.setOnClickListener(this);
        ibLoginVisible.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        ib_weibo.setOnClickListener(this);
        ib_qq.setOnClickListener(this);
        ib_wechat.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == ibLoginBack) {
            finish();
        } else if (v == ibLoginVisible) {
            /**
             * 隐藏密码
             * isVisble为双数 隐藏密码 改变右边的表情
             * 为1 显示密码
             */
            isVisble++;
            if (isVisble % 2 == 0) {
                ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_invisible);
                etLoginPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_visible);
                etLoginPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

            }
        }else if (v==btnRegister)
        {
            startRegisterActivity();
        }else if (v == btnLogin) {
            ifLogin();
//            UserBean.MedUserBean medUserBean = new UserBean.MedUserBean();
//            medUserBean.setId(1);
//            medUserBean.setPassword("123123..");
//            medUserBean.setPhoneNumber("165165");
//            medUserBean.setUsername("zzz");
//            backToHome(medUserBean);
        } else if (v == ib_weibo) {
//            mShareAPI = UMShareAPI.get(this);
//            mShareAPI.doOauthVerify(this, SHARE_MEDIA.SINA, umAuthListener);
        } else if (v == ib_qq) {
//            mShareAPI = UMShareAPI.get(this);
//            mShareAPI.doOauthVerify(this, SHARE_MEDIA.QQ, umAuthListener);
        } else if (v == ib_wechat) {
//            mShareAPI = UMShareAPI.get(this);
//            mShareAPI.doOauthVerify(this, SHARE_MEDIA.WEIXIN, umAuthListener);
        }
    }

    private void startRegisterActivity() {

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, 3);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void ifLogin() {

        final String username = String.valueOf(etLoginPhone.getText());
        final String password = String.valueOf(etLoginPwd.getText());
        Log.e("USERNAME", String.valueOf(username));
        Log.e("PASSWORD", String.valueOf(password));
//        String url = Constants.LOGIN_URL;
        String url = Constants.TEST_URL+"medUser/login";

        OkHttpUtils
                .post()
                .url(url)
                .addParams("phone", username)
                .addParams("password", password)
                .build()
                .execute(new StringCallback()
                {
                    /**
                     * 请求失败 回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"登陆请求失败"+e.getMessage());

                    }

                    /**
                     * 联网成功时
                     * @param response 请求成功数据
                     * @param id
                     */

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"登陆请求成功!");

                        login(response);

                    }

                });
    }


    private void login(String response) {
        UserBean userBean = JSON.parseObject(response, UserBean.class);
            UserBean.MedUserBean medUserBean = userBean.getMed_user();
            /**
             * 登录逻辑处理
             */
        System.out.println(userBean.getCode());
        System.out.println(medUserBean);

        backToHome(userBean);

    }


    private void backToHome(UserBean userBean){
        Intent intent = new Intent();
        if (userBean.getCode().equals("200")) {
            MyApplication.setIfLogin(true);
            intent.putExtra("MED_USER", new Gson().toJson(userBean.getMed_user()));
            setResult(1, intent);
            System.out.println("ITs 200");
        }else {
            System.out.println("ITS 500");
            intent.putExtra("MSG", "failed!");
            setResult(2,intent);
        }
        finish();
    }
}
