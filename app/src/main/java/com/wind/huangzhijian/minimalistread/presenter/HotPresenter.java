package com.wind.huangzhijian.minimalistread.presenter;

import com.wind.huangzhijian.minimalistread.Util.RxUtil;
import com.wind.huangzhijian.minimalistread.base.RxPresenter;
import com.wind.huangzhijian.minimalistread.module.bean.HotListBean;
import com.wind.huangzhijian.minimalistread.module.db.RealmHelper;
import com.wind.huangzhijian.minimalistread.module.http.RetrofitHelper;
import com.wind.huangzhijian.minimalistread.presenter.contract.HotContract;
import com.wind.huangzhijian.minimalistread.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by huangzhijian on 2017/3/20.
 */
public class HotPresenter extends RxPresenter<HotContract.View> implements HotContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper mRealmHelper;

    @Inject
    public HotPresenter(RetrofitHelper retrofitHelper,RealmHelper realmHelper){
        this.mRealmHelper =realmHelper;
        this.mRetrofitHelper=retrofitHelper;
    }

    @Override
    public void getHotData() {
        Subscription rxSubscription = mRetrofitHelper.fetchHotListInfo()
                .compose(RxUtil.<HotListBean>rxSchedulerHelper())
                .map(new Func1<HotListBean, HotListBean>() {
                    @Override
                    public HotListBean call(HotListBean hotListBean) {
                        List<HotListBean.RecentBean> list = hotListBean.getRecent();
                        for(HotListBean.RecentBean item : list) {
                            item.setReadState(mRealmHelper.queryNewsId(item.getNews_id()));
                        }
                        return hotListBean;
                    }
                })
                .subscribe(new CommonSubscriber<HotListBean>(mView) {
                    @Override
                    public void onNext(HotListBean hotListBean) {
                        mView.showContent(hotListBean);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void insertReadToDB(int id) {
        mRealmHelper.insertNewsId(id);
    }
}
