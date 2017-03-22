package com.wind.huangzhijian.minimalistread.ui.zhihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wind.huangzhijian.minimalistread.Component.ImageLoader;
import com.wind.huangzhijian.minimalistread.R;
import com.wind.huangzhijian.minimalistread.module.bean.DailyListBean;
import com.wind.huangzhijian.minimalistread.ui.zhihu.activity.ZhihuDetailActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by huangzhijian on 2017/3/18.
 */
public class TopPageAdapter extends PagerAdapter {

    private List<DailyListBean.TopStoriesBean> list = new ArrayList<>();

    private Context context;

    public TopPageAdapter(Context context,List<DailyListBean.TopStoriesBean> list){
        this.context= context;
        this.list =list;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top_page,container,false);
        ImageView ivImage = (ImageView) view.findViewById(R.id.iv_top_image);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_top_title);
        ImageLoader.load(context,list.get(position).getImage(),ivImage);
        tvTitle.setText(list.get(position).getTitle());
        final int id = list.get(position).getId();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ZhihuDetailActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("isNotTransition",true);
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }
}
