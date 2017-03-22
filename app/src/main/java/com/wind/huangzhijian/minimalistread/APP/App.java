package com.wind.huangzhijian.minimalistread.APP;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.github.moduth.blockcanary.BlockCanary;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.sdk.QbSdk;
import com.wind.huangzhijian.minimalistread.Component.AppBlockCanaryContext;
import com.wind.huangzhijian.minimalistread.Component.CrashHandler;
import com.wind.huangzhijian.minimalistread.di.component.AppComponent;
import com.wind.huangzhijian.minimalistread.di.component.DaggerAppComponent;
import com.wind.huangzhijian.minimalistread.di.module.AppModule;
import com.wind.huangzhijian.minimalistread.di.module.HttpModule;
import com.wind.huangzhijian.minimalistread.di.module.PageModule;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by huangzhijian on 2017/2/22.
 */
public class App extends Application{

    private Set<Activity> activities;
    private static App instance;
    private static AppComponent appComponent;

    public static int SCREEN_WIDTH=-1;
    public static int SCREEN_HEIGH=-1;
    public static float DIMEN_RATE=-1.0F;
    public static int DIMEN_DPI=-1;

    public static synchronized App getInstance(){return instance;}

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

        //init  log
        Logger.init(getPackageName()).hideThreadInfo();

        //get screensize
        getScreenSize();

        //内存泄漏检测
        LeakCanary.install(this);

        //过渡绘制检测
        BlockCanary.install(this,new AppBlockCanaryContext()).start();

        //初始化crashHandler
        CrashHandler.init(new CrashHandler(getApplicationContext()));

        QbSdk.initX5Environment(getApplicationContext(), new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {

            }
        });
    }

    public void addActivity(Activity act){
        if(activities == null){
            activities = new HashSet<Activity>();
        }
        activities.add(act);
    }

    public void removeActivity(Activity act){
        if(activities != null){
            activities.remove(act);
        }
    }

    public void exitApp(){
        if(activities != null){
            synchronized (activities){
                for (Activity act :activities){
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


    private void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display =windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE=dm.density / 1.0F;
        DIMEN_DPI=dm.densityDpi;
        SCREEN_HEIGH=dm.heightPixels;
        SCREEN_WIDTH=dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGH){
            int t = SCREEN_HEIGH;
            SCREEN_HEIGH =SCREEN_WIDTH;
            SCREEN_WIDTH=t;
        }
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .pageModule(new PageModule())
                    .build();
        }
        return appComponent;
    }
}
