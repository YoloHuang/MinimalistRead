package com.wind.huangzhijian.minimalistread.module.bean;

import java.util.List;

/**
 * Created by huangzhijian on 2017/3/16.
 */
public class DailyListBeforeBean {

    private String date;

    private List<DailyListBean.StoriesBean> storiesBeanList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DailyListBean.StoriesBean> getStoriesBeanList() {
        return storiesBeanList;
    }

    public void setStoriesBeanList(List<DailyListBean.StoriesBean> storiesBeanList) {
        this.storiesBeanList = storiesBeanList;
    }
}
