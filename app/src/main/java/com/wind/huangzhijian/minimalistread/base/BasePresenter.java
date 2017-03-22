package com.wind.huangzhijian.minimalistread.base;

/**
 * Created by huangzhijian on 2017/3/7.
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(T view);
    void detachView();
}
