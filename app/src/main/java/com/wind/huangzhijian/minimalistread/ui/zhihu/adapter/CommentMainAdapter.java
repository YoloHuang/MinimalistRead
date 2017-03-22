package com.wind.huangzhijian.minimalistread.ui.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wind.huangzhijian.minimalistread.ui.zhihu.fragment.CommentFragment;

import java.util.List;

/**
 * Created by huangzhijian on 2017/3/20.
 */
public class CommentMainAdapter extends FragmentPagerAdapter {

    private List<CommentFragment> fragments;
    public CommentMainAdapter(FragmentManager fm,List<CommentFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
