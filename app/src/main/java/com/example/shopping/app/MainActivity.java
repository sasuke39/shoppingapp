package com.example.shopping.app;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.shopping.R;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.community.fragment.CommunityFragment;
import com.example.shopping.home.fragment.HomeFragment;
import com.example.shopping.shoppingcar.fragment.Shoppingcarfragment;
import com.example.shopping.type.fragment.TypeFragment;
import com.example.shopping.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends FragmentActivity {
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    /**
     * 实例化一个RadioGroup
     */


    /**
     * 转入多个fragment的集合
     */
    private ArrayList<BaseFragment> fragments;
    private int position = 0;

    private Fragment tempFragemnt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * ButterKnife 自动和当前activity绑定
         */
        ButterKnife.bind(this);
        /**
         * 通过findViewById 查找到名为rg_main的Group
         * 并且绑定两者关系
         * 通过check方法 表示选中一个名为rb_home的radio
         */
        /**
         * 初始化fragment
         */
        initFragment();
        /**
         * 设置radiogroup的监听
         */
        initListener();

        rgMain.check(R.id.rb_home);
    }

    private void initListener() {

        rgMain.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.rb_home://主页
                    position = 0;
                    break;
                case R.id.rb_type://分类
                    position = 1;
                    break;
                case R.id.rb_community://发现
                    position = 2;
                    break;
                case R.id.rb_cart://购物车
                    position = 3;
                    break;
                case R.id.rb_user://用户中心
                    position = 4;
                    break;
                default:
                    position = 0;
                    break;
            }

            //根据位置取不同的Fragment
            BaseFragment baseFragment = getFragment(position);

            /**
             * 第一参数：上次显示的Fragment
             * 第二参数：当前正要显示的Fragment
             */
            switchFragment(tempFragemnt, baseFragment);

        });


        rgMain.check(R.id.rb_home);
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            /**
             * 从fragments 集合中根据传入的position 取出一个BaseFragment 再返回
             */
             BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;

    }

    /**
     * 切换fragment方法
     * @param fromFragment
     * @param nextFragment
     */
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragemnt != nextFragment) {
            tempFragemnt = nextFragment;
            if (nextFragment != null) {
                /**
                 * 获得fragment 管理器
                 */
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    //添加Fragment
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
    /**
     * 安装顺序添加
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new Shoppingcarfragment());
        fragments.add(new UserFragment());
    }

}
