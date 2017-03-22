package com.wind.huangzhijian.minimalistread.presenter.contract;

import com.wind.huangzhijian.minimalistread.base.BasePresenter;
import com.wind.huangzhijian.minimalistread.base.BaseView;
import com.wind.huangzhijian.minimalistread.module.bean.HotListBean;

/**
 * Created by huangzhijian on 2017/3/20.
 */
public interface HotContract {

    interface View extends BaseView {

        void showContent(HotListBean hotListBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getHotData();

        void insertReadToDB(int id);

    }

}
