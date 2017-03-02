package com.wind.huangzhijian.minimalistread.Util;

import com.orhanobut.logger.Logger;
import com.wind.huangzhijian.minimalistread.BuildConfig;

/**
 * Created by huangzhijian on 2017/2/23.
 */
public class LogUtil {

    public static boolean isDebug = BuildConfig.DEBUG;

    private static final String TAG = "com.wind.huangzhijian.minimalistread";

    public static void e(String tag,Object o){
        if(isDebug){
            Logger.e(tag,o);
        }
    }

    public static void e(Object o){Logger.e(TAG,o);}

    public static void w(String tag,Object o){
        if(isDebug){
            Logger.w(tag,o);
        }
    }

    public static void w(Object o){Logger.w(TAG,o);}

    public static void d(String tag,Object o){
        if(isDebug){
            Logger.d(tag,o);
        }
    }

    public static void i(String tag,Object o){
        if(isDebug){
            Logger.i(tag,o);
        }
    }
}
