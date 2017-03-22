package com.wind.huangzhijian.minimalistread.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by huangzhijian on 2017/3/15.
 */
public abstract class SimpleFragment extends SupportFragment {

    protected View view;
    protected Activity mActivity;
    protected Context mContext;

    private Unbinder mUnbinder;
    private boolean isInited = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(),null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this ,view);
        if(savedInstanceState == null){
            if(!isHidden()){
                isInited = true;
                initEventAndData();
            }
        }else {
            if(!isSupportVisible()){
                isInited = true ;
                initEventAndData();
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!isInited && !hidden){
            isInited =true;
            initEventAndData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    protected abstract void initEventAndData();

    protected abstract int getLayoutId();
}
