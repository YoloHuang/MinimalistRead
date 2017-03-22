package com.wind.huangzhijian.minimalistread.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wind.huangzhijian.minimalistread.APP.App;
import com.wind.huangzhijian.minimalistread.di.component.DaggerFragmentComponent;
import com.wind.huangzhijian.minimalistread.di.component.FragmentComponent;
import com.wind.huangzhijian.minimalistread.di.module.FragmentModule;

import javax.inject.Inject;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by huangzhijian on 2017/3/15.
 */
public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule(){return new FragmentModule(this);}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initInject();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter !=null){
            mPresenter.detachView();
        }
    }

    protected abstract void initInject();
}
