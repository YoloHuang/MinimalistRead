package com.wind.huangzhijian.minimalistread.module.db;

import android.content.Context;

import com.wind.huangzhijian.minimalistread.module.bean.ReadStateBean;
import com.wind.huangzhijian.minimalistread.module.bean.RealmLikeBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


/**
 * Created by huangzhijian on 2017/3/16.
 */
public class RealmHelper {

    private static final String DB_NAME = "myRealm.realmhzj";

    private Realm mRealm;

    public  RealmHelper(Context context){
        mRealm = Realm.getInstance(new RealmConfiguration.Builder(context)
            .deleteRealmIfMigrationNeeded()
            .name(DB_NAME)
            .build());
    }

    /**
     * 增加阅读记录
     */
    public void insertNewsId(int id){
        ReadStateBean bean = new ReadStateBean();
        bean.setId(id);
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(bean);
        mRealm.commitTransaction();
    }

    /**
     * 查询阅读记录
     */

    public boolean queryNewsId(int id){
        RealmResults<ReadStateBean> results = mRealm.where(ReadStateBean.class).findAll();
        for(ReadStateBean item : results){
            if(item.getId() == id){
                return true;
            }
        }
        return false;
    }

    /**
     * 增加收藏记录
     */
    public void insertLikeBean(RealmLikeBean bean){
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(bean);
        mRealm.commitTransaction();
    }

    /**
     * 删除收藏记录
     */
    public void deleteLikeBean(String id){
        RealmLikeBean bean = mRealm.where(RealmLikeBean.class).equalTo("id",id).findFirst();
        mRealm.beginTransaction();
        if(bean !=null){
            bean.deleteFromRealm();
        }
        mRealm.commitTransaction();
    }

    /**
     * 查询收藏记录
     */
    public boolean queryLikeBean(String id){
        RealmResults<RealmLikeBean> results = mRealm.where(RealmLikeBean.class).findAll();
        for(RealmLikeBean bean : results){
            if(bean.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取收藏列表
     */
    public List<RealmLikeBean> getLikeBeans(){
        RealmResults<RealmLikeBean> results = mRealm.where(RealmLikeBean.class).findAllSorted("time");
        return mRealm.copyFromRealm(results);
    }

    /**
     * 修改收藏记录，以时间戳重新排序
     */
    public void checkLikeTime(String id ,long time,boolean isPlus){
        RealmLikeBean bean = mRealm.where(RealmLikeBean.class).equalTo("id",id).findFirst();
        mRealm.beginTransaction();
        if(isPlus){
            bean.setTime(++time);
        }else {
            bean.setTime(--time);
        }
        mRealm.commitTransaction();
    }



}
