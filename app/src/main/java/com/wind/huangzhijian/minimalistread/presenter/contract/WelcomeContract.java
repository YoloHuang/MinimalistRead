package com.wind.huangzhijian.minimalistread.presenter.contract;

import com.wind.huangzhijian.minimalistread.base.BasePresenter;
import com.wind.huangzhijian.minimalistread.base.BaseView;
import com.wind.huangzhijian.minimalistread.module.bean.WelcomeBean;

/**
 * Created by huangzhijian on 2017/3/7.
 */
public interface WelcomeContract  {

    interface View extends BaseView{
        void showContent(WelcomeBean welcomeBean);

        void JumpToMain();
    }

    interface Presenter extends BasePresenter<View>{
        void getWelcomeData();
    }
}
