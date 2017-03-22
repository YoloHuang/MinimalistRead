package com.wind.huangzhijian.minimalistread.ui.zhihu.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wind.huangzhijian.minimalistread.R;
import com.wind.huangzhijian.minimalistread.base.SimpleFragment;
import com.wind.huangzhijian.minimalistread.module.http.api.ZhihuApis;
import com.wind.huangzhijian.minimalistread.ui.zhihu.adapter.ZhihuAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by huangzhijian on 2017/3/15.
 */
public class ZhihuFragment extends SimpleFragment {

    @BindView(R.id.tab_comment)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager_zhihu)
    ViewPager mViewPager;

    ZhihuAdapter zhihuAdapter;

    String[] tabTitle = new String[]{"日报","主题","热门"};
    List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void initEventAndData() {
        fragments.add(new DailyFragment());
        fragments.add(new ThemeFragment());
        fragments.add(new HotFragment());
        zhihuAdapter = new ZhihuAdapter(getChildFragmentManager(),fragments);
        mViewPager.setAdapter(zhihuAdapter);

        //TabLayout配合ViewPager有时会出现不显示Tab文字的Bug,需要按如下顺序
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[2]));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(tabTitle[0]);
        mTabLayout.getTabAt(1).setText(tabTitle[1]);
        mTabLayout.getTabAt(2).setText(tabTitle[2]);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu;
    }
}
