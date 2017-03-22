package com.wind.huangzhijian.minimalistread.di.component;

import com.wind.huangzhijian.minimalistread.APP.App;
import com.wind.huangzhijian.minimalistread.di.module.AppModule;
import com.wind.huangzhijian.minimalistread.di.module.HttpModule;
import com.wind.huangzhijian.minimalistread.di.module.PageModule;
import com.wind.huangzhijian.minimalistread.module.db.RealmHelper;
import com.wind.huangzhijian.minimalistread.module.http.RetrofitHelper;
import com.wind.huangzhijian.minimalistread.ui.zhihu.fragment.ZhihuFragment;

import java.lang.annotation.Retention;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by huangzhijian on 2017/3/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class, PageModule.class})
public interface AppComponent {
    App getContext();  //提供APP的context

    RetrofitHelper retrofiyHelper();

    RealmHelper realmHelper();

    ZhihuFragment zhihuFragmemt();
}
