package com.example.shopping.type.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.shopping.R;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.type.fragment.childfragment.type1ChildFg;
import com.example.shopping.type.fragment.childfragment.type2ChildFg;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MyTypeFragment extends BaseFragment implements View.OnClickListener{

    private Context context;
    private ArrayList<BaseFragment> fragments;
    private Fragment tempFragemnt;
    private int position=0;

    private TextView v1;
    private TextView v2;
    private TextView v3;
    private TextView v4;
    private FrameLayout fm;
//    private TextView v1;

    private TextView lastCheck;
    private TextView nextCheck;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
        initFragment();
        initListener();
    }

    private void initListener() {

        BaseFragment baseFragment = getFragment(position);
        switchFragment(tempFragemnt, baseFragment);

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new type1ChildFg());
        fragments.add(new type2ChildFg());

    }


    @Override
    public View initView() {
        View view = View.inflate(my_Context, R.layout.testfragment, null);
        v1 = view.findViewById(R.id.text1);
        v1.setOnClickListener(this);
        lastCheck=v1;
        v1.setSelected(true);
        v2= view.findViewById(R.id.text2);
        v2.setOnClickListener(this);

        v3 = view.findViewById(R.id.text3);
        v3.setOnClickListener(this);

        v4 = view.findViewById(R.id.text4);
        v4.setOnClickListener(this);

        fm = view.findViewById(R.id.frameLayout);
//        TextView view5 = view.findViewById(R.id.text5);
//        view5.setOnClickListener(this);
//        TextView view6 = view.findViewById(R.id.text6);
//        view6.setOnClickListener(this);
//        TextView view7 = view.findViewById(R.id.text7);
//        view7.setOnClickListener(this);
//        TextView view8 = view.findViewById(R.id.text8);
//        view8.setOnClickListener(this);
//        TextView view9 = view.findViewById(R.id.text9);
//        view9.setOnClickListener(this);
//        TextView view10 = view.findViewById(R.id.text10);
//        view10.setOnClickListener(this);

        return view;
    }






    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragemnt != nextFragment) {
            tempFragemnt = nextFragment;
            if (nextFragment != null) {
                /**
                 * 获得fragment 管理器
                 */
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    //添加Fragment
                    FragmentTransaction add = transaction.add(R.id.frameLayout, nextFragment);
                    add.commit();
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


    @Override
    public void onClick(View v) {

        if (v==v1&&lastCheck!=v1){
            lastCheck.setSelected(false);
            v1.setSelected(true);
            lastCheck=v1;
            Log.e(TAG,"切换到第一个fragment");
            position=0;
        }else if (v==v2&&lastCheck!=v2){
            lastCheck.setSelected(false);
            v2.setSelected(true);
            lastCheck=v2;
            Log.e(TAG,"切换到第二个fragment");
            position=1;
        }

        BaseFragment baseFragment = getFragment(position);
        switchFragment(tempFragemnt, baseFragment);

    }

}

