package com.wind.huangzhijian.minimalistread.module.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by huangzhijian on 2017/3/16.
 */
public class ReadStateBean extends RealmObject {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey
    private int id ;

    public  ReadStateBean(){}


}
