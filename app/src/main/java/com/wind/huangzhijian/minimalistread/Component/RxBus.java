package com.wind.huangzhijian.minimalistread.Component;

import com.wind.huangzhijian.minimalistread.Util.RxUtil;

import java.util.Objects;
import java.util.Observable;

import rx.subjects.Subject;

import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

/**
 * Created by huangzhijian on 2017/3/9.
 */
public class RxBus {

    private final Subject<Object, Object> bus;

    private RxBus(){bus = new SerializedSubject<>(PublishSubject.create());}

    public static RxBus getDefault(){return RxBusHolder.sInstance;}

    private static class RxBusHolder {
        private static final RxBus sInstance = new RxBus();
    }

    public void post(Object o){bus.onNext(o);}

    public <T> rx.Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }

    // 封装默认订阅
    public <T> Subscription toDefaultObservable(Class<T> eventType, Action1<T> act) {
        return bus.ofType(eventType).compose(RxUtil.<T>rxSchedulerHelper()).subscribe(act);
    }
}
