package com.wind.huangzhijian.minimalistread.presenter.contract;

import com.wind.huangzhijian.minimalistread.base.BasePresenter;
import com.wind.huangzhijian.minimalistread.base.BaseView;
import com.wind.huangzhijian.minimalistread.module.bean.ThemeListBean;

/**
 * Created by huangzhijian on 2017/3/20.
 */
public interface ThemeContract {
    interface View extends BaseView {

        void showContent(ThemeListBean themeListBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getThemeData();
    }
}
