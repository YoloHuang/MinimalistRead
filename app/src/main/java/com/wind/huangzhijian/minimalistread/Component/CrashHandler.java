package com.wind.huangzhijian.minimalistread.Component;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.orhanobut.logger.Logger;
import com.wind.huangzhijian.minimalistread.APP.App;
import com.wind.huangzhijian.minimalistread.R;
import com.wind.huangzhijian.minimalistread.Util.LogUtil;
import com.wind.huangzhijian.minimalistread.Util.ToastUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by huangzhijian on 2017/2/23.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static Thread.UncaughtExceptionHandler defaultHandler = null;

    private  Context context = null;

    private final String TAG = CrashHandler.class.getSimpleName();

    public  CrashHandler(Context context){this.context =context;}

    //初始化的时候将默认crash处理器赋给defaultHandler,并将crashHandler设置为默认crash处理器
    public static void init(CrashHandler crashHandler){
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }



    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(e.toString());
        LogUtil.e(TAG,e.toString());
        LogUtil.e(TAG,collectionCrashDeviceInfo());
        LogUtil.e(TAG,getCrashInfo(e));
        defaultHandler.uncaughtException(t, e);
        ToastUtil.shortShow(context.getString(R.string.crash_exit_sorry));
        App.getInstance().exitApp();
    }

    public String getCrashInfo(Throwable e){
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        e.setStackTrace(e.getStackTrace());
        e.printStackTrace(printWriter);
        return result.toString();
    }

    public String collectionCrashDeviceInfo(){
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info =pm.getPackageInfo(context.getPackageName(),PackageManager.GET_ACTIVITIES);
            String versionName = info.versionName;
            String model = Build.MODEL;
            String androidVersion = Build.VERSION.RELEASE;
            String manufacturer = Build.MANUFACTURER;
            return versionName + "   " + model + "   " + androidVersion + "   " + manufacturer ;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
