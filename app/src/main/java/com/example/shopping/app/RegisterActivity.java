package com.example.shopping.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.shopping.R;
import com.example.shopping.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RegisterActivity extends Activity implements View.OnClickListener  {

    private ImageButton ibLoginBack;
    private EditText loginName;
    private EditText loginPwd;
    private ImageButton ibLoginVisible;
    private EditText loginPhone;
    private Button btnRegister;
    private ImageButton ibWeibo;
    private ImageButton ibQq;
    private ImageButton ibWechat;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-05-20 01:04:47 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        ibLoginBack = (ImageButton)findViewById( R.id.ib_login_back );
        loginName = (EditText)findViewById( R.id.login_name );
        loginPwd = (EditText)findViewById( R.id.login_pwd );
        ibLoginVisible = (ImageButton)findViewById( R.id.ib_login_visible );
        loginPhone = (EditText)findViewById( R.id.login_phone );
        btnRegister = (Button)findViewById( R.id.btn_register );
        ibWeibo = (ImageButton)findViewById( R.id.ib_weibo );
        ibQq = (ImageButton)findViewById( R.id.ib_qq );
        ibWechat = (ImageButton)findViewById( R.id.ib_wechat );

        ibLoginBack.setOnClickListener( this );
        ibLoginVisible.setOnClickListener( this );
        btnRegister.setOnClickListener( this );
        ibWeibo.setOnClickListener( this );
        ibQq.setOnClickListener( this );
        ibWechat.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2020-05-20 01:04:47 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ibLoginBack ) {
            finish();
            // Handle clicks for ibLoginBack
        } else if ( v == ibLoginVisible ) {
            // Handle clicks for ibLoginVisible
        } else if ( v == btnRegister ) {
            // Handle clicks for btnLogin
            Register();
        } else if ( v == ibWeibo ) {
            // Handle clicks for ibWeibo
        } else if ( v == ibQq ) {
            // Handle clicks for ibQq
        } else if ( v == ibWechat ) {
            // Handle clicks for ibWechat
        }
    }

    private void Register() {

        final String name = loginName.getText().toString();
        final String pwd = loginPwd.getText().toString();
        final String phone = loginPhone.getText().toString();

       if (checkStyle(name,pwd,phone)){
           System.out.println(name+"==="+pwd+"==="+phone);
           String url = Constants.TEST_URL +"medUser/register";
           OkHttpUtils
                   .post()
                   .url(url)
                   .addParams("username", name)
                   .addParams("password", pwd)
                   .addParams("phone",phone)
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
                           ifRegister(response);

                       }

                   });
       }


    }

    private void ifRegister(String response) {

        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject.get("code")=="200"){
            Toast.makeText(this, Objects.requireNonNull(jsonObject.get("msg")).toString(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, Objects.requireNonNull(jsonObject.get("msg")).toString(), Toast.LENGTH_SHORT).show();
        }
        finish();

    }

    private Boolean checkStyle(String name, String pwd, String phone) {
        if (name.length() > 5) {
            if (!isSpecialChar(name)){
                if (pwd.length()>6&&pwd.length()<20){
                    if (phone.length()==11){
                        return true;
                    }else {
                        Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "密码在6到20之间", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "姓名不能含有特殊字符", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "字数请大于5个", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
    }

    /**
     * 是否含有特殊字符
     * @param str
     * @return
     */
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

}
