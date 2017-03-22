package com.wind.huangzhijian.minimalistread.APP;

import android.renderscript.ScriptIntrinsicYuvToRGB;

import java.io.File;

import javax.inject.Singleton;

/**
 * Created by huangzhijian on 2017/3/10.
 */
public class Constants {

    public static final int TYPE_ZHIHU = 101;

    public static final String SP_NO_IMAGE = "no_image";

    //--------------PATH------------//
    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String SP_AUTO_CACHE = "auto_cache";
}
