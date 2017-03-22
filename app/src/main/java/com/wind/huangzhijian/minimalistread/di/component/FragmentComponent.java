package com.wind.huangzhijian.minimalistread.di.component;

import android.app.Activity;

import com.wind.huangzhijian.minimalistread.di.module.FragmentModule;
import com.wind.huangzhijian.minimalistread.di.scope.FragmentScope;
import com.wind.huangzhijian.minimalistread.ui.zhihu.fragment.CommentFragment;
import com.wind.huangzhijian.minimalistread.ui.zhihu.fragment.DailyFragment;
import com.wind.huangzhijian.minimalistread.ui.zhihu.fragment.HotFragment;
import com.wind.huangzhijian.minimalistread.ui.zhihu.fragment.SectionFragment;
import com.wind.huangzhijian.minimalistread.ui.zhihu.fragment.ThemeFragment;
import com.wind.huangzhijian.minimalistread.ui.zhihu.fragment.ZhihuFragment;

import javax.inject.Inject;

import dagger.Component;

/**
 * Created by huangzhijian on 2017/3/15.
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(DailyFragment dailyFragment);

    void inject(ThemeFragment themeFragment);

    void inject(HotFragment hotFragment);

    void inject(CommentFragment longCommentFragment);

    //void inject(ZhihuFragment zhihuFragment);

}
