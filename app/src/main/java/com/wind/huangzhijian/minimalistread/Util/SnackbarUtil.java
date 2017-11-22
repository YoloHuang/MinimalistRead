package com.wind.huangzhijian.minimalistread.Util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by huangzhijian on 2017/3/20.
 */
public class SnackbarUtil {
    public static void show(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
        LogUtil.e(msg,"");
        Exception e = new Exception("haha");

        e.printStackTrace();
    }
}
