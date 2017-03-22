package com.wind.huangzhijian.minimalistread.presenter.contract;

import com.wind.huangzhijian.minimalistread.base.BasePresenter;
import com.wind.huangzhijian.minimalistread.base.BaseView;
import com.wind.huangzhijian.minimalistread.module.bean.CommentBean;

/**
 * Created by huangzhijian on 2017/3/20.
 */
public interface CommentContract {
    interface View extends BaseView {

        void showContent(CommentBean commentBean);

    }

    interface Presenter extends BasePresenter<View> {

        void getCommentData(int id,int commentKind);

    }
}
