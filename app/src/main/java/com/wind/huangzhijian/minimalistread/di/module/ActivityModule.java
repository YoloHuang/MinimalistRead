package com.wind.huangzhijian.minimalistread.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huangzhijian on 2017/3/7.
 */

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    public Activity provideActivity(){
        return activity;
    }

}
