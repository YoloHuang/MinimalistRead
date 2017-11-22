package com.wind.huangzhijian.minimalistread.ui.zhihu.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.wind.huangzhijian.minimalistread.Component.RxBus;
import com.wind.huangzhijian.minimalistread.R;
import com.wind.huangzhijian.minimalistread.Util.CircularAnimUtil;
import com.wind.huangzhijian.minimalistread.Util.DateUtil;
import com.wind.huangzhijian.minimalistread.Util.SnackbarUtil;
import com.wind.huangzhijian.minimalistread.base.BaseFragment;
import com.wind.huangzhijian.minimalistread.base.SimpleFragment;
import com.wind.huangzhijian.minimalistread.module.bean.DailyListBean;
import com.wind.huangzhijian.minimalistread.module.bean.DailyListBeforeBean;
import com.wind.huangzhijian.minimalistread.presenter.DailyPresenter;
import com.wind.huangzhijian.minimalistread.presenter.contract.DailyContract;
import com.wind.huangzhijian.minimalistread.ui.zhihu.activity.CalendarActivity;
import com.wind.huangzhijian.minimalistread.ui.zhihu.activity.ZhihuDetailActivity;
import com.wind.huangzhijian.minimalistread.ui.zhihu.adapter.DailyAdapter;
import com.wind.huangzhijian.minimalistread.widget.ProgressImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huangzhijian on 2017/3/15.
 */
public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View{

    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.rv_daily_list)
    RecyclerView rvDailyList;
    @BindView(R.id.view_progress)
    ProgressImageView ivProgress;

    String currentDate;
    DailyAdapter mAdapter;
    List<DailyListBean.StoriesBean> mList = new ArrayList<>();

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showContent(DailyListBean info) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            ivProgress.stop();
        }

        mList = info.getStories();
        currentDate = String.valueOf(Integer.valueOf(info.getDate()) + 1);
        mAdapter.addDailyDate(info);
        mPresenter.stopInterval();
        mPresenter.startInterval();
    }

    @Override
    public void showMoreContent(String date, DailyListBeforeBean info) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            ivProgress.stop();
        }
        mPresenter.stopInterval();
        mList = info.getStoriesBeanList();
        currentDate = String.valueOf(Integer.valueOf(info.getDate()));
        mAdapter.addDailyBeforeDate(info);
    }


    @Override
    public void doInterval(int currentCount) {
        mAdapter.changeTopPager(currentCount);
    }

    @Override
    public void showError(String msg) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            ivProgress.stop();
        }
        SnackbarUtil.showShort(rvDailyList,msg);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    protected void initEventAndData() {
        currentDate = DateUtil.getTomorrowDate();
        mAdapter = new DailyAdapter(mContext,mList);
        mAdapter.setOnItemClickListener(new DailyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position,View shareView) {
                mPresenter.insertReadtoDB(mList.get(position).getId());
                mAdapter.setReadState(position,true);
                if(mAdapter.getIsBefore()) {
                    mAdapter.notifyItemChanged(position + 1);
                } else {
                    mAdapter.notifyItemChanged(position + 2);
                }
                Intent intent = new Intent();
                intent.setClass(mContext, ZhihuDetailActivity.class);
                intent.putExtra("id",mList.get(position).getId());
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity, shareView, "shareView");
                mContext.startActivity(intent,options.toBundle());
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(currentDate.equals(DateUtil.getTomorrowDate())) {
                    mPresenter.getDailyData();
                } else {
                    int year = Integer.valueOf(currentDate.substring(0,4));
                    int month = Integer.valueOf(currentDate.substring(4,6));
                    int day = Integer.valueOf(currentDate.substring(6,8));
                    CalendarDay date = CalendarDay.from(year, month - 1, day);
                    RxBus.getDefault().post(date);
                }
            }
        });
        rvDailyList.setLayoutManager(new LinearLayoutManager(mContext));
        rvDailyList.setAdapter(mAdapter);
        ivProgress.start();
        mPresenter.getDailyData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @OnClick(R.id.fab_calender)
    void startCalender() {
        Intent it = new Intent();
        it.setClass(mContext,CalendarActivity.class);
        CircularAnimUtil.startActivity(mActivity,it,fabCalender,R.color.fab_bg);
    }
}
