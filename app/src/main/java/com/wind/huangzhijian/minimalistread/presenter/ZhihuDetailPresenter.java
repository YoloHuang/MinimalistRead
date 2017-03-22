package com.wind.huangzhijian.minimalistread.presenter;

import com.wind.huangzhijian.minimalistread.APP.Constants;
import com.wind.huangzhijian.minimalistread.Util.RxUtil;
import com.wind.huangzhijian.minimalistread.base.RxPresenter;
import com.wind.huangzhijian.minimalistread.module.bean.DetailExtraBean;
import com.wind.huangzhijian.minimalistread.module.bean.RealmLikeBean;
import com.wind.huangzhijian.minimalistread.module.bean.ZhihuDetailBean;
import com.wind.huangzhijian.minimalistread.module.db.RealmHelper;
import com.wind.huangzhijian.minimalistread.module.http.RetrofitHelper;
import com.wind.huangzhijian.minimalistread.presenter.contract.ZhihuDetailContract;
import com.wind.huangzhijian.minimalistread.widget.CommonSubscriber;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by huangzhijian on 2017/3/20.
 */
public class ZhihuDetailPresenter extends RxPresenter<ZhihuDetailContract.View> implements ZhihuDetailContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper mRealmHelper;
    private ZhihuDetailBean mData;

    @Inject
    public ZhihuDetailPresenter(RetrofitHelper mRetrofitHelper,RealmHelper mRealmHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.mRealmHelper = mRealmHelper;
    }

    @Override
    public void getDetailData(int id) {
        Subscription rxSubscription =  mRetrofitHelper.fetchDetailInfo(id)
                .compose(RxUtil.<ZhihuDetailBean>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<ZhihuDetailBean>(mView) {
                    @Override
                    public void onNext(ZhihuDetailBean zhihuDetailBean) {
                        mData = zhihuDetailBean;
                        mView.showContent(zhihuDetailBean);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void getExtraData(int id) {
        Subscription rxSubscription =  mRetrofitHelper.fetchDetailExtraInfo(id)
                .compose(RxUtil.<DetailExtraBean>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<DetailExtraBean>(mView, "加载额外信息失败ヽ(≧Д≦)ノ") {
                    @Override
                    public void onNext(DetailExtraBean detailExtraBean) {
                        mView.showExtraInfo(detailExtraBean);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void insertLikeData() {
        if (mData != null) {
            RealmLikeBean bean = new RealmLikeBean();
            bean.setId(String.valueOf(mData.getId()));
            bean.setImage(mData.getImage());
            bean.setTitle(mData.getTitle());
            bean.setType(Constants.TYPE_ZHIHU);
            bean.setTime(System.currentTimeMillis());
            mRealmHelper.insertLikeBean(bean);
        } else {
            mView.showError("操作失败");
        }
    }

    @Override
    public void deleteLikeData() {
        if (mData != null) {
            mRealmHelper.deleteLikeBean(String.valueOf(mData.getId()));
        } else {
            mView.showError("操作失败");
        }
    }

    @Override
    public void queryLikeData(int id) {
        mView.setLikeButtonState(mRealmHelper.queryLikeBean(String.valueOf(id)));
    }
}
