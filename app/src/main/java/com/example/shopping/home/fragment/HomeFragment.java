package com.example.shopping.home.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.shopping.Dao.RecordsDao;
import com.example.shopping.R;
import com.example.shopping.app.SearchActivity;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.home.adapter.HomeFragmentAdapter;
import com.example.shopping.home.bean.MedreslutBeanData;
import com.example.shopping.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;

import static com.example.shopping.utils.CommonUtils.hideSoftInput;


public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();
    /**
     * 初始化各个组件
     * 且绑定
     */
    private RecordsDao mRecordsDao;
    //默然展示词条个数
    private final int DEFAULT_RECORD_NUMBER = 10;
    private TagAdapter<String> mRecordsAdapter;
    private List<String> recordList = new ArrayList<>();
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.edit_query)
    EditText editText;
    @BindView(R.id.iv_clear_search)
    ImageView clearSearch;
    @BindView(R.id.iv_search)
    TextView ivSearch;
    @BindView(R.id.cl_toolbar)
    ConstraintLayout clToolbar;
    @BindView(R.id.tv_history_hint)
    TextView tvHistoryHint;
    @BindView(R.id.clear_all_records)
    ImageView clearAllRecords;
    @BindView(R.id.fl_search_records)
    TagFlowLayout tagFlowLayout;
    @BindView(R.id.iv_arrow)
    ImageView moreArrow;
    @BindView(R.id.ll_history_content)
    LinearLayout mHistoryContent;
    @BindView(R.id.search_flag)
    LinearLayout searchFlag;
    private RecyclerView rvHome;
    private ImageView ib_top;
    private EditText ed_search;
    private TextView tv_search;

    private RecyclerView rvSearchItm;

    private HomeFragmentAdapter adapter;

    private boolean if_show = false;

    /**
     * 返回的数据
     */
    private MedreslutBeanData.ResultBean resultBean;
    private MedreslutBeanData.ResultMedcineBean resultMedcineBean;

    private Context my_Context;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        my_Context = getActivity();
        String username = "a";
        //初始化数据库
        mRecordsDao = new RecordsDao(my_Context, username);
        System.out.println("onCreate!");
    }


    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        /**
         * 获取fragment_home的view
         */
        View view = View.inflate(my_Context, R.layout.fragment_home, null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        rvSearchItm =view.findViewById(R.id.recyclerview_search_item);
        ButterKnife.bind(this, view);


        System.out.println("onCreateView!");
        init_Data();
        initlis();
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "主页数据被初始化了");
        getDataFromNet();
        //联网请求主页的数据
//        getDataFromNet();
    }

    /**
     * 获取数据
     * 成功回调解析函数
     */
    private void getDataFromNet() {
//        String url = Constants.HOME_URL;
        String url = Constants.home1;
        OkHttpUtils
                .get()
                .url(url)
//                .addParams("username", "hyman")
//                .addParams("password", "123")
                .build()
                .execute(new StringCallback() {
                    /**
                     * 请求失败 回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "首页请求失败" + e.getMessage());

                    }

                    /**
                     * 联网成功时
                     * @param response 请求成功数据
                     * @param id
                     */

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "首页请求成功!");

                        //解析数据
                        processData(response);
                    }

                });
    }


    private void processData(String json) {
        /**
         * 把json转化成一个类对象
         * 传来的json里的样式 不是数据 给转化成一个类
         */
        MedreslutBeanData medreslutBeanData = JSON.parseObject(json, MedreslutBeanData.class);
        /**
         * 获取类resultBeanData对象中resultBean对象
         */
        resultBean = medreslutBeanData.getResult();
        resultMedcineBean = medreslutBeanData.getResult_medcine();

        if (resultBean != null) {
            //有数据
            //设置适配器
            adapter = new HomeFragmentAdapter(my_Context, resultBean, resultMedcineBean);
            rvHome.setAdapter(adapter);

            /**
             * 设置浮标
             */
            GridLayoutManager manager = new GridLayoutManager(my_Context, 1);
            //设置跨度大小监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position <= 2) {
                        //隐藏
                        ib_top.setVisibility(View.GONE);
                    } else {
                        //显示
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    //只能返回1
                    return 1;
                }
            });
            //设置布局管理者
            rvHome.setLayoutManager(manager);

        } else {
            //没有数据
        }
//        Log.e(TAG,"解析成功=="+resultBean.getHot_info().get(0).getName());
    }

    private void initlis() {
        //创建历史标签适配器
        //为标签设置对应的内容
        mRecordsAdapter = new TagAdapter<String>(recordList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(my_Context).inflate(R.layout.tv_history, tagFlowLayout, false);
                //为标签设置对应的内容
                tv.setText(s);
                return tv;
            }
        };
        System.out.println(tagFlowLayout);
        tagFlowLayout.setAdapter(mRecordsAdapter);
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public void onTagClick(View view, int position, FlowLayout parent) {
                //清空editText之前的数据
                editText.setText("");
                //将获取到的字符串传到搜索结果界面,点击后搜索对应条目内容
                editText.setText(recordList.get(position));
                editText.setSelection(editText.length());
            }
        });
        //删除某个条目
        tagFlowLayout.setOnLongClickListener(new TagFlowLayout.OnLongClickListener() {
            @Override
            public void onLongClick(View view, final int position) {
                showDialog("确定要删除该条历史记录？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //删除某一条记录
                        mRecordsDao.deleteRecord(recordList.get(position));
                    }
                });
            }
        });

        //view加载完成时回调
        tagFlowLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

            }
        });

        moreArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagFlowLayout.setLimit(false);
                mRecordsAdapter.notifyDataChanged();
            }
        });

        //清除所有记录
        clearAllRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("确定要删除全部历史记录？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tagFlowLayout.setLimit(true);
                        //清除所有数据
                        mRecordsDao.deleteUsernameAllRecords();
                    }
                });
            }
        });

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSearch.setVisibility(View.GONE);
                clearSearch.setVisibility(View.GONE);
                mHistoryContent.setVisibility(View.GONE);
                editText.setText("");
                hideSoftInput(my_Context,v);
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSearch.setVisibility(View.VISIBLE);
                clearSearch.setVisibility(View.VISIBLE);
                mHistoryContent.setVisibility(View.VISIBLE);
                if (null == recordList || recordList.size() == 0) {
                    boolean isOverFlow = tagFlowLayout.isOverFlow();
                    boolean isLimit = tagFlowLayout.isLimit();
                    if (isLimit && isOverFlow) {
                        moreArrow.setVisibility(View.VISIBLE);
                        if_show = true;
                    } else {
                        moreArrow.setVisibility(View.GONE);
                        if_show = false;
                    }

                }
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    Toast.makeText(my_Context, "点击回车！", Toast.LENGTH_SHORT).show();
                    String record = editText.getText().toString();
                    if (!TextUtils.isEmpty(record)) {
                        //添加数据
                        mRecordsDao.addRecords(record);
                        Intent intent = new Intent(my_Context, SearchActivity.class);
                        intent.putExtra("searchWord",record);
                        my_Context.startActivity(intent);
                    }else {
                        Toast.makeText(my_Context, "请输入搜索信息！", Toast.LENGTH_SHORT).show();
                    }//隐藏软键盘
                    hideSoftInput(my_Context,v);
                }
                return false;
            }
        });
        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除搜索历史
                editText.setText("");
            }
        });

//        searchFlag.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ivSearch.setVisibility(View.VISIBLE);
//                clearSearch.setVisibility(View.VISIBLE);
//                mHistoryContent.setVisibility(View.VISIBLE);
//                if (null == recordList || recordList.size() == 0) {
//                    boolean isOverFlow = tagFlowLayout.isOverFlow();
//                    boolean isLimit = tagFlowLayout.isLimit();
//                    if (isLimit && isOverFlow) {
//                        moreArrow.setVisibility(View.VISIBLE);
//                        if_show = true;
//                    } else {
//                        moreArrow.setVisibility(View.GONE);
//                        if_show = false;
//                    }
//
//                }
//
//            }
//        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHistoryContent.setVisibility(View.GONE);
            }
        });

        ib_top.setOnClickListener(v -> {
            //回到顶部
            rvHome.scrollToPosition(0);
        });


        mRecordsDao.setNotifyDataChanged(new RecordsDao.NotifyDataChanged() {
            @Override
            public void notifyDataChanged() {
                init_Data();
            }
        });
    }


    private void showDialog(String dialogTitle, @NonNull DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(dialogTitle);
        builder.setPositiveButton("确定", onClickListener);
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }


    private void init_Data() {
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                emitter.onNext(mRecordsDao.getRecordsByNumber(DEFAULT_RECORD_NUMBER));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> s) throws Exception {
                        recordList.clear();
                        recordList = s;
                        if (mRecordsAdapter != null) {
                            mRecordsAdapter.setData(recordList);
                            mRecordsAdapter.notifyDataChanged();
                        }
                    }
                });
        System.out.println("init_Data!!!!!!!!!");
    }

    @Override
    public void onDestroy() {
        mRecordsDao.closeDatabase();
        mRecordsDao.removeNotifyDataChanged();
        super.onDestroy();
    }



}
