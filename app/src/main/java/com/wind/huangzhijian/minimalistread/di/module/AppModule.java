package com.wind.huangzhijian.minimalistread.di.module;

import com.wind.huangzhijian.minimalistread.APP.App;
import com.wind.huangzhijian.minimalistread.module.db.RealmHelper;
import com.wind.huangzhijian.minimalistread.module.http.RetrofitHelper;
import com.wind.huangzhijian.minimalistread.module.http.api.ZhihuApis;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huangzhijian on 2017/3/7.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule (App application){this.application = application;}

    @Provides
    @Singleton
    App provideApplicationContext(){return application;}

    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper(ZhihuApis zhihuApisService){
        return new RetrofitHelper(zhihuApisService);
    }

    @Provides
    @Singleton
    RealmHelper provideRealmHelper() {
        return new RealmHelper(application);
    }
}
