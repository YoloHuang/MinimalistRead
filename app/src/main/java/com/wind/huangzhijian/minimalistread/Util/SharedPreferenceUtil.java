package com.wind.huangzhijian.minimalistread.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.wind.huangzhijian.minimalistread.APP.App;
import com.wind.huangzhijian.minimalistread.APP.Constants;

/**
 * Created by huangzhijian on 2017/3/10.
 */
public class SharedPreferenceUtil {

    private static final String SHAREDPREFERENCE_NAME = "my_sp";
    private static final boolean DEFAULT_NO_IMAGE = false;
    private static final boolean DEFAULT_AUTO_SAVE = true;

    public static SharedPreferences getAppSp(){
        return App.getInstance().getSharedPreferences(SHAREDPREFERENCE_NAME , Context.MODE_PRIVATE);
    }

    public static boolean getNoImageState(){
        return getAppSp().getBoolean(Constants.SP_NO_IMAGE ,DEFAULT_NO_IMAGE);
    }

    public static boolean getAutoCacheState() {
        return getAppSp().getBoolean(Constants.SP_AUTO_CACHE, DEFAULT_AUTO_SAVE);
    }
}
