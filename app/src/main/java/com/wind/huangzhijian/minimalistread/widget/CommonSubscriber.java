package com.wind.huangzhijian.minimalistread.widget;


import android.text.TextUtils;

import com.wind.huangzhijian.minimalistread.base.BaseView;
import com.wind.huangzhijian.minimalistread.module.http.exception.ApiException;
import com.wind.huangzhijian.minimalistread.module.http.exception.HttpException;

import rx.Subscriber;

/**
 * Created by huangzhijian on 2017/3/14.
 */
public abstract class CommonSubscriber<T> extends Subscriber<T> {

    private BaseView mView;
    private String mErrorMsg;

    protected CommonSubscriber(BaseView view){this.mView=view;}

    protected CommonSubscriber(BaseView view ,String string){
        this.mView = view;
        this.mErrorMsg = string;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if(mView == null){
            return;
        }
        if(mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)){
            mView.showError(mErrorMsg);
        }else if (e instanceof ApiException){
            mView.showError(e.toString());
        }else if (e instanceof HttpException){
            mView.showError("数据加载失败");
        }else {
            mView.showError("未知错误~");
        }
    }
}
