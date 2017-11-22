package com.wind.huangzhijian.minimalistread.module.http.api;

import com.wind.huangzhijian.minimalistread.module.bean.CommentBean;
import com.wind.huangzhijian.minimalistread.module.bean.DailyListBean;
import com.wind.huangzhijian.minimalistread.module.bean.DailyListBeforeBean;
import com.wind.huangzhijian.minimalistread.module.bean.DetailExtraBean;
import com.wind.huangzhijian.minimalistread.module.bean.HotListBean;
import com.wind.huangzhijian.minimalistread.module.bean.ThemeChildListBean;
import com.wind.huangzhijian.minimalistread.module.bean.ThemeListBean;
import com.wind.huangzhijian.minimalistread.module.bean.WelcomeBean;
import com.wind.huangzhijian.minimalistread.module.bean.ZhihuDetailBean;
import com.wind.huangzhijian.minimalistread.ui.zhihu.fragment.DailyFragment;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by huangzhijian on 2017/3/9.
 */
public interface ZhihuApis {

    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 启动界面图片
     */
    @GET("start-image/{res}")
    Observable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

    /**
     * get 最新日报
     */
    @GET("news/latest")
    Observable<DailyListBean> getDailyList();

    /**
     * get 往期日报
     */
    @GET("news/before/{date}")
    Observable<DailyListBeforeBean> getDailyBeforeList(@Path("date") String date);


    /**
     * 日报详情
     */
    @GET("news/{id}")
    Observable<ZhihuDetailBean> getDetailInfo(@Path("id") int id);

    /**
     * 日报的额外信息
     */
    @GET("story-extra/{id}")
    Observable<DetailExtraBean> getDetailExtraInfo(@Path("id") int id);

    /**
     * 日报的长评论
     */
    @GET("story/{id}/long-comments")
    Observable<CommentBean> getLongCommentInfo(@Path("id") int id);

    /**
     * 日报的短评论
     */
    @GET("story/{id}/short-comments")
    Observable<CommentBean> getShortCommentInfo(@Path("id") int id);

    /**
     * 主题日报
     */
    @GET("themes")
    Observable<ThemeListBean> getThemeList();

    /**
     * 主题日报详情
     */
    @GET("theme/{id}")
    Observable<ThemeChildListBean> getThemeChildList(@Path("id") int id);

    /**
     * 热门日报
     */
    @GET("news/hot")
    Observable<HotListBean> getHotList();
}
