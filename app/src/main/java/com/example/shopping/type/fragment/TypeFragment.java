package com.example.shopping.type.fragment;


import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.shopping.R;
import com.example.shopping.base.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TypeFragment extends BaseFragment {
    private SegmentTabLayout segmentTabLayout;
    private ImageView iv_type_search;
    private FrameLayout fl_type;
    private List<BaseFragment> fragmentList;
    private Fragment tempFragment;
    public ListFragment listFragment;
    public TagFragment tagFragment;

    /**
     * 初始化视图 实例化视图中的id
     * @return
     */
    @Override
    public View initView() {
        View view = View.inflate(my_Context, R.layout.fragment_type, null);
        segmentTabLayout = (SegmentTabLayout) view.findViewById(R.id.tl_1);
        iv_type_search = (ImageView) view.findViewById(R.id.iv_type_search);
        fl_type = (FrameLayout) view.findViewById(R.id.fl_type);

        return view;

    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();

        initFragment();
//"标签"
        String[] titles = {"分类","标签"};

        segmentTabLayout.setTabData(titles);

        segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                switchFragment(tempFragment, fragmentList.get(position));
                Toast.makeText(my_Context, "had clicked", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTabReselect(int position) {
                Toast.makeText(my_Context, "had clicked", Toast.LENGTH_SHORT).show();

            }
        });

    }


    /**
     * protected void onResume() 在 Activity 从 Pause 状态转换到 Active 状态时被调用
     */
    @Override
    public void onResume() {
        super.onResume();
        switchFragment(tempFragment, fragmentList.get(0));
    }

    /**
     * 点击后一个隐藏前面一个
     * 检查是否添加到transaction
     * 没有 就 隐藏再添加
     * 有 直接隐藏
     * 最后show 下一个fragment
     * @param fromFragment
     * @param nextFragment
     */
    public void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }

                    transaction.add(R.id.fl_type, nextFragment, "tagFragment").commit();
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
     * 初始化fragment
     * 实例化一个arraylist 一个list fragment
     * 一个 tahfragment
     * 在fragment 中添加listfragment tagment
     *
     */
    private void initFragment() {
        fragmentList = new ArrayList<>();
        listFragment = new ListFragment();
        tagFragment = new TagFragment();

        fragmentList.add(listFragment);
        fragmentList.add(tagFragment);

        switchFragment(tempFragment, fragmentList.get(0));
    }


}
