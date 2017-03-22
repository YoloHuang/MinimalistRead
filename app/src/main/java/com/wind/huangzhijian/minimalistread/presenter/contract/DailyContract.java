package com.wind.huangzhijian.minimalistread.presenter.contract;

import com.wind.huangzhijian.minimalistread.base.BasePresenter;
import com.wind.huangzhijian.minimalistread.base.BaseView;
import com.wind.huangzhijian.minimalistread.module.bean.DailyListBean;
import com.wind.huangzhijian.minimalistread.module.bean.DailyListBeforeBean;

/**
 * Created by huangzhijian on 2017/3/16.
 */
public interface DailyContract {

    interface View extends BaseView{
        //显示当日日报
        void showContent(DailyListBean info);
        //显示之前的日报
        void showMoreContent(String date,DailyListBeforeBean info);
        void doInterval(int currentCount);
    }

    interface Presenter extends BasePresenter<View>{
        void getDailyData();
        void getBeforeData(String date);
        void startInterval();
        void stopInterval();
        void insertReadtoDB(int id);
    }

}
