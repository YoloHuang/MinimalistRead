package com.wind.huangzhijian.minimalistread.module.http;

import com.wind.huangzhijian.minimalistread.module.bean.CommentBean;
import com.wind.huangzhijian.minimalistread.module.bean.DailyListBean;
import com.wind.huangzhijian.minimalistread.module.bean.DailyListBeforeBean;
import com.wind.huangzhijian.minimalistread.module.bean.DetailExtraBean;
import com.wind.huangzhijian.minimalistread.module.bean.HotListBean;
import com.wind.huangzhijian.minimalistread.module.bean.ThemeChildListBean;
import com.wind.huangzhijian.minimalistread.module.bean.ThemeListBean;
import com.wind.huangzhijian.minimalistread.module.bean.VersionBean;
import com.wind.huangzhijian.minimalistread.module.bean.WelcomeBean;
import com.wind.huangzhijian.minimalistread.module.bean.ZhihuDetailBean;
import com.wind.huangzhijian.minimalistread.module.http.api.ZhihuApis;
import com.wind.huangzhijian.minimalistread.module.http.response.MyHttpResponse;

import rx.Observable;

/**
 * Created by huangzhijian on 2017/3/9.
 */
public class RetrofitHelper {

    private ZhihuApis mZhihuApiService;

    public RetrofitHelper(ZhihuApis mZhihuApiService) {
        this.mZhihuApiService = mZhihuApiService;
    }

    public Observable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mZhihuApiService.getWelcomeInfo(res);
    }

    public Observable<DailyListBean> fetchDailyListInfo(){
        return mZhihuApiService.getDailyList();
    }

    public Observable<DailyListBeforeBean> fetchDailyBeforeListInfo(String date){
        return mZhihuApiService.getDailyBeforeList(date);
    }

    public Observable<ZhihuDetailBean> fetchDetailInfo(int id) {
        return mZhihuApiService.getDetailInfo(id);
    }

    public Observable<DetailExtraBean> fetchDetailExtraInfo(int id) {
        return mZhihuApiService.getDetailExtraInfo(id);
    }

    public Observable<CommentBean> fetchLongCommentInfo(int id) {
        return mZhihuApiService.getLongCommentInfo(id);
    }

    public Observable<CommentBean> fetchShortCommentInfo(int id) {
        return mZhihuApiService.getShortCommentInfo(id);
    }

    public Observable<HotListBean> fetchHotListInfo() {
        return mZhihuApiService.getHotList();
    }

    public Observable<ThemeListBean> fetchDailyThemeListInfo() {
        return mZhihuApiService.getThemeList();
    }

    public Observable<ThemeChildListBean> fetchDailyThemeChildListInfo(int id){
        return mZhihuApiService.getThemeChildList(id);
    }


}
