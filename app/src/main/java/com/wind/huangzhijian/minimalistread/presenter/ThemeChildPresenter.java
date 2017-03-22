package com.wind.huangzhijian.minimalistread.presenter;

import com.wind.huangzhijian.minimalistread.Util.RxUtil;
import com.wind.huangzhijian.minimalistread.base.RxPresenter;
import com.wind.huangzhijian.minimalistread.module.bean.ThemeChildListBean;
import com.wind.huangzhijian.minimalistread.module.db.RealmHelper;
import com.wind.huangzhijian.minimalistread.module.http.RetrofitHelper;
import com.wind.huangzhijian.minimalistread.presenter.contract.ThemeChildContract;
import com.wind.huangzhijian.minimalistread.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by huangzhijian on 2017/3/22.
 */
public class ThemeChildPresenter extends RxPresenter<ThemeChildContract.View> implements ThemeChildContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    private RealmHelper mRealmHelper;

    @Inject
    public ThemeChildPresenter(RetrofitHelper retrofitHelper,RealmHelper realmHelper){
        this.mRealmHelper= realmHelper;
        this.mRetrofitHelper=retrofitHelper;
    }

    @Override
    public void getThemeChildData(int id) {
        Subscription rxSubscription = mRetrofitHelper.fetchDailyThemeChildListInfo(id)
                .compose(RxUtil.<ThemeChildListBean>rxSchedulerHelper())
                .map(new Func1<ThemeChildListBean, ThemeChildListBean>() {
                    @Override
                    public ThemeChildListBean call(ThemeChildListBean themeChildListBean) {
                        List<ThemeChildListBean.StoriesBean> list =themeChildListBean.getStories();
                        for(ThemeChildListBean.StoriesBean item : list){
                            item.setReadState(mRealmHelper.queryNewsId(item.getId()));
                        }
                        return themeChildListBean;
                    }
                })
                .subscribe(new CommonSubscriber<ThemeChildListBean>(mView) {
                    @Override
                    public void onNext(ThemeChildListBean themeChildListBean) {
                        mView.showContent(themeChildListBean);
                    }
                });
        addSubscribe(rxSubscription);

    }

    @Override
    public void insertReadToDB(int id) {
        mRealmHelper.insertNewsId(id);
    }
}
