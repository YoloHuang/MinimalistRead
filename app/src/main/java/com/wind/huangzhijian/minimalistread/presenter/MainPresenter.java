package com.wind.huangzhijian.minimalistread.presenter;

import com.google.common.primitives.Booleans;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.wind.huangzhijian.minimalistread.Component.RxBus;
import com.wind.huangzhijian.minimalistread.Util.RxUtil;
import com.wind.huangzhijian.minimalistread.base.RxPresenter;
import com.wind.huangzhijian.minimalistread.module.bean.WelcomeBean;
import com.wind.huangzhijian.minimalistread.module.event.NightModeEvent;
import com.wind.huangzhijian.minimalistread.module.http.RetrofitHelper;
import com.wind.huangzhijian.minimalistread.module.http.response.MyHttpResponse;
import com.wind.huangzhijian.minimalistread.presenter.contract.MainContract;
import com.wind.huangzhijian.minimalistread.widget.CommonSubscriber;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by huangzhijian on 2017/3/14.
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private RetrofitHelper retrofitHelper;



    @Inject
    public MainPresenter(RetrofitHelper retrofitHelper){
        this.retrofitHelper = retrofitHelper;
        registerEvent();
    }

    private void registerEvent() {
        Subscription rxSubscription = RxBus.getDefault().toObservable(NightModeEvent.class)
                .compose(RxUtil.<NightModeEvent>rxSchedulerHelper())
                .map(new Func1<NightModeEvent,Boolean>() {
                    @Override
                    public Boolean call(NightModeEvent nightModeEvent) {
                        return nightModeEvent.isNightMode();
                    }
                })
                .subscribe(new CommonSubscriber<Boolean>(mView ,"切换模式失败" ) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mView.useNightMode(aBoolean);
                    }
                });
        addSubscribe(rxSubscription);

    }

    @Override
    public void checkVersion(String currentVersion) {

    }

    @Override
    public void checkPermission(RxPermissions rxPermissions) {

    }
}
