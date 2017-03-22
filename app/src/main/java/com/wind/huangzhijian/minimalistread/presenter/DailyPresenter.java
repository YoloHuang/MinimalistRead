package com.wind.huangzhijian.minimalistread.presenter;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.wind.huangzhijian.minimalistread.Component.RxBus;
import com.wind.huangzhijian.minimalistread.Util.DateUtil;
import com.wind.huangzhijian.minimalistread.Util.RxUtil;
import com.wind.huangzhijian.minimalistread.base.RxPresenter;
import com.wind.huangzhijian.minimalistread.module.bean.DailyListBean;
import com.wind.huangzhijian.minimalistread.module.bean.DailyListBeforeBean;
import com.wind.huangzhijian.minimalistread.module.db.RealmHelper;
import com.wind.huangzhijian.minimalistread.module.http.RetrofitHelper;
import com.wind.huangzhijian.minimalistread.presenter.contract.DailyContract;
import com.wind.huangzhijian.minimalistread.widget.CommonSubscriber;


import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;


import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by huangzhijian on 2017/3/16.
 */
public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter {


    private RetrofitHelper mRetrofitHelper;
    private RealmHelper mRealmHelper;
    private Subscription mSubscription;

    private static final int INTERVAL_INSTANCE=6;

    private int topCount = 0;
    private int currentTopCount= 0;

    @Inject
    public DailyPresenter(RetrofitHelper retrofitHelper,RealmHelper realmHelper){
        this.mRealmHelper =realmHelper;
        this.mRetrofitHelper= retrofitHelper;
        registerEvent();
    }

    private void registerEvent() {
        Subscription rxSubscription = RxBus.getDefault().toObservable(CalendarDay.class)
                .subscribeOn(Schedulers.io())
                .map(new Func1<CalendarDay, String>() {
                    @Override
                    public String call(CalendarDay calendarDay) {
                        StringBuilder date = new StringBuilder();
                        String year = String.valueOf(calendarDay.getYear());
                        String month = String.valueOf(calendarDay.getMonth());
                        String day = String.valueOf(calendarDay.getDay());
                        if(month.length()<2){
                            month="0"+month;
                        }
                        if(day.length()<2){
                            day="0"+day;
                        }
                        return date.append(year).append(month).append(day).toString();
                    }
                })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        if(s.equals(DateUtil.getTomorrowDate())) {
                            getDailyData();
                            return false;
                        }
                        return true;
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Func1<String, Observable<DailyListBeforeBean>>() {
                    @Override
                    public Observable<DailyListBeforeBean> call(String s) {
                        return mRetrofitHelper.fetchDailyBeforeListInfo(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<DailyListBeforeBean, DailyListBeforeBean>() {
                    @Override
                    public DailyListBeforeBean call(DailyListBeforeBean dailyListBeforeBean) {
                        List<DailyListBean.StoriesBean> list = dailyListBeforeBean.getStoriesBeanList();
                        for(DailyListBean.StoriesBean story : list){
                            story.setReadstate(mRealmHelper.queryNewsId(story.getId()));
                        }
                        return dailyListBeforeBean;
                    }
                })
                .subscribe(new CommonSubscriber<DailyListBeforeBean>(mView) {
                    @Override
                    public void onNext(DailyListBeforeBean dailyListBeforeBean) {
                        int year = Integer.valueOf(dailyListBeforeBean.getDate().substring(0,4));
                        int month = Integer.valueOf(dailyListBeforeBean.getDate().substring(4,6));
                        int day = Integer.valueOf(dailyListBeforeBean.getDate().substring(6,8));
                        mView.showMoreContent(String.format("%d年%d月%d日",year,month,day),dailyListBeforeBean);
                    }
                });
        addSubscribe(rxSubscription);
    }


    @Override
    public void getDailyData() {
        Subscription rxSubscription = mRetrofitHelper.fetchDailyListInfo()
                .compose(RxUtil.<DailyListBean>rxSchedulerHelper())
                .map(new Func1<DailyListBean, DailyListBean>() {
                    @Override
                    public DailyListBean call(DailyListBean dailyListBean) {
                        List<DailyListBean.StoriesBean> list = dailyListBean.getStories();
                        for(DailyListBean.StoriesBean item : list){
                            item.setReadstate(mRealmHelper.queryNewsId(item.getId()));
                        }
                        return dailyListBean;
                    }
                })
                .subscribe(new CommonSubscriber<DailyListBean>(mView) {
                    @Override
                    public void onNext(DailyListBean dailyListBean) {
                        topCount = dailyListBean.getTopStories().size();
                        mView.showContent(dailyListBean);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void getBeforeData(String date) {
        Subscription rxSubscription = mRetrofitHelper.fetchDailyBeforeListInfo(date)
                .compose(RxUtil.<DailyListBeforeBean>rxSchedulerHelper())
                .map(new Func1<DailyListBeforeBean, DailyListBeforeBean>() {
                    @Override
                    public DailyListBeforeBean call(DailyListBeforeBean dailyListBeforeBean) {
                        List<DailyListBean.StoriesBean> list = dailyListBeforeBean.getStoriesBeanList();
                        for(DailyListBean.StoriesBean item : list){
                            item.setReadstate(mRealmHelper.queryNewsId(item.getId()));
                        }
                        return dailyListBeforeBean;
                    }
                })
                .subscribe(new CommonSubscriber<DailyListBeforeBean>(mView) {
                    @Override
                    public void onNext(DailyListBeforeBean dailyListBeforeBean) {
                        int year = Integer.valueOf(dailyListBeforeBean.getDate().substring(0,4));
                        int month = Integer.valueOf(dailyListBeforeBean.getDate().substring(4,6));
                        int day = Integer.valueOf(dailyListBeforeBean.getDate().substring(6,8));
                        mView.showMoreContent(String.format("%d年%d月%d日",year,month,day),dailyListBeforeBean);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void startInterval() {
        mSubscription =Observable.interval(INTERVAL_INSTANCE, TimeUnit.SECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if(currentTopCount == topCount){
                            currentTopCount =0;
                        }
                        mView.doInterval(currentTopCount++);
                    }
                });
        addSubscribe(mSubscription);
    }

    @Override
    public void stopInterval() {
        if(mSubscription != null){
            mSubscription.unsubscribe();
        }

    }

    @Override
    public void insertReadtoDB(int id) {
        mRealmHelper.insertNewsId(id);
    }


}
