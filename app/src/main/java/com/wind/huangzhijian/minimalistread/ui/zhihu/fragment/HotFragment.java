package com.wind.huangzhijian.minimalistread.ui.zhihu.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wind.huangzhijian.minimalistread.R;
import com.wind.huangzhijian.minimalistread.Util.SnackbarUtil;
import com.wind.huangzhijian.minimalistread.base.BaseFragment;
import com.wind.huangzhijian.minimalistread.module.bean.HotListBean;
import com.wind.huangzhijian.minimalistread.presenter.HotPresenter;
import com.wind.huangzhijian.minimalistread.presenter.contract.HotContract;
import com.wind.huangzhijian.minimalistread.ui.zhihu.activity.ZhihuDetailActivity;
import com.wind.huangzhijian.minimalistread.ui.zhihu.adapter.HotAdapter;
import com.wind.huangzhijian.minimalistread.widget.ProgressImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Created by huangzhijian on 2017/3/15.
 */
public class HotFragment extends BaseFragment<HotPresenter> implements HotContract.View{

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.view_progress)
    ProgressImageView ivProgress;
    @BindView(R.id.rv_content)
    RecyclerView rvHotContent;

    HotAdapter mHotAdapter;
    List<HotListBean.RecentBean> mList;
    HotAdapter mAdapter;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showContent(HotListBean hotListBean) {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            ivProgress.stop();
        }
        rvHotContent.setVisibility(View.VISIBLE);
        mList.clear();
        mList.addAll(hotListBean.getRecent());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            ivProgress.stop();
        }
        SnackbarUtil.showShort(rvHotContent,msg);
    }

    @Override
    public void useNightMode(boolean isNight) {
        mList = new ArrayList<>();
        ivProgress.start();
        mAdapter = new HotAdapter(mContext,mList);
        rvHotContent.setVisibility(View.INVISIBLE);
        rvHotContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvHotContent.setAdapter(mAdapter);
        mPresenter.getHotData();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getHotData();
            }
        });
        mAdapter.setOnItemClickListener(new HotAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View shareView) {
                mPresenter.insertReadToDB(mList.get(position).getNews_id());
                mAdapter.setReadState(position,true);
                mAdapter.notifyItemChanged(position);
                Intent intent = new Intent();
                intent.setClass(mContext, ZhihuDetailActivity.class);
                intent.putExtra("id",mList.get(position).getNews_id());
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity, shareView, "shareView");
                mContext.startActivity(intent,options.toBundle());
            }
        });
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_common_list;
    }
}
