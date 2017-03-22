package com.wind.huangzhijian.minimalistread.di.component;

import android.app.Activity;

import com.wind.huangzhijian.minimalistread.di.module.ActivityModule;
import com.wind.huangzhijian.minimalistread.di.scope.ActivityScope;
import com.wind.huangzhijian.minimalistread.ui.main.WelcomeActivity;
import com.wind.huangzhijian.minimalistread.ui.main.activity.MainActivity;
import com.wind.huangzhijian.minimalistread.ui.zhihu.activity.ThemeActivity;
import com.wind.huangzhijian.minimalistread.ui.zhihu.activity.ZhihuDetailActivity;

import dagger.Component;

/**
 * Created by huangzhijian on 2017/3/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class ,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(WelcomeActivity welcomeActivity);

    void inject(ZhihuDetailActivity zhihuDetailActivity);

    void inject(ThemeActivity themeActivity);

    void inject(MainActivity mainActivity);

}
