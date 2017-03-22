package com.wind.huangzhijian.minimalistread.presenter.contract;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.wind.huangzhijian.minimalistread.base.BasePresenter;
import com.wind.huangzhijian.minimalistread.base.BaseView;

/**
 * Created by huangzhijian on 2017/3/14.
 */
public interface MainContract {

     interface View extends BaseView{
        void showupdateDialog(String versionContent);

        void startDownloadService();
    }

    interface Presenter extends BasePresenter<View>{
        void checkVersion(String currentVersion);

        void checkPermission(RxPermissions rxPermissions);
    }
}
