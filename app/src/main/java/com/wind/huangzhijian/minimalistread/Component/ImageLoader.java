package com.wind.huangzhijian.minimalistread.Component;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wind.huangzhijian.minimalistread.Util.SharedPreferenceUtil;

/**
 * Created by huangzhijian on 2017/3/10.
 */
public class ImageLoader {

    public static void load(Context context, String url, ImageView img){
        if (!SharedPreferenceUtil.getNoImageState()){
            Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img);
        }
    }

    public static void load(Activity activity, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        if(!activity.isDestroyed()) {
            Glide.with(activity).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }
}
