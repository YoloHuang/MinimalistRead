package com.wind.huangzhijian.minimalistread.presenter;

import com.wind.huangzhijian.minimalistread.Util.RxUtil;
import com.wind.huangzhijian.minimalistread.base.RxPresenter;
import com.wind.huangzhijian.minimalistread.module.bean.CommentBean;
import com.wind.huangzhijian.minimalistread.module.http.RetrofitHelper;
import com.wind.huangzhijian.minimalistread.presenter.contract.CommentContract;
import com.wind.huangzhijian.minimalistread.widget.CommonSubscriber;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by huangzhijian on 2017/3/20.
 */
public class CommentPresenter extends RxPresenter<CommentContract.View> implements CommentContract.Presenter {

    public static final int SHORT_COMMENT = 0;

    public static final int LONG_COMMENT = 1;

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public CommentPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }


    @Override
    public void getCommentData(int id, int commentKind) {
        if(commentKind == SHORT_COMMENT) {
            Subscription rxSubscription = mRetrofitHelper.fetchShortCommentInfo(id)
                    .compose(RxUtil.<CommentBean>rxSchedulerHelper())
                    .subscribe(new CommonSubscriber<CommentBean>(mView) {
                        @Override
                        public void onNext(CommentBean commentBean) {
                            mView.showContent(commentBean);
                        }
                    });
            addSubscribe(rxSubscription);
        } else {
            Subscription rxSubscription = mRetrofitHelper.fetchLongCommentInfo(id)
                    .compose(RxUtil.<CommentBean>rxSchedulerHelper())
                    .subscribe(new CommonSubscriber<CommentBean>(mView) {
                        @Override
                        public void onNext(CommentBean commentBean) {
                            mView.showContent(commentBean);
                        }
                    });
            addSubscribe(rxSubscription);
        }
    }
}
