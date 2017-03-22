package com.wind.huangzhijian.minimalistread.ui.zhihu.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wind.huangzhijian.minimalistread.R;
import com.wind.huangzhijian.minimalistread.Util.SnackbarUtil;
import com.wind.huangzhijian.minimalistread.base.BaseFragment;
import com.wind.huangzhijian.minimalistread.module.bean.ThemeListBean;
import com.wind.huangzhijian.minimalistread.presenter.ThemePresenter;
import com.wind.huangzhijian.minimalistread.presenter.contract.ThemeContract;
import com.wind.huangzhijian.minimalistread.ui.zhihu.activity.ThemeActivity;
import com.wind.huangzhijian.minimalistread.ui.zhihu.adapter.ThemeAdapter;
import com.wind.huangzhijian.minimalistread.widget.ProgressImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by huangzhijian on 2017/3/15.
 */
public class ThemeFragment extends BaseFragment<ThemePresenter> implements ThemeContract.View{

    @BindView(R.id.rv_content)
    RecyclerView rvThemeList;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.view_progress)
    ProgressImageView ivProgress;

    ThemeAdapter mAdapter;
    List<ThemeListBean.OthersBean> mList = new ArrayList<>();

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showContent(ThemeListBean themeListBean) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            ivProgress.stop();
        }
        mList.clear();
        mList.addAll(themeListBean.getOthers());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            ivProgress.stop();
        }
        SnackbarUtil.showShort(rvThemeList,msg);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    protected void initEventAndData() {
        mAdapter = new ThemeAdapter(mContext, mList);
        rvThemeList.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvThemeList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ThemeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent();
                intent.setClass(mContext, ThemeActivity.class);
                intent.putExtra("id", id);
                mContext.startActivity(intent);
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getThemeData();
            }
        });
        mPresenter.getThemeData();
        ivProgress.start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_common_list;
    }
}
