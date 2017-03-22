package com.wind.huangzhijian.minimalistread.di.module;

import com.wind.huangzhijian.minimalistread.ui.zhihu.fragment.ZhihuFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huangzhijian on 2017/3/7.
 */

@Module
public class PageModule {

    @Singleton
    @Provides
    ZhihuFragment provideZhihu(){return new ZhihuFragment();}
}
