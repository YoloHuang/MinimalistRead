package com.wind.huangzhijian.minimalistread.Util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wind.huangzhijian.minimalistread.APP.App;
import com.wind.huangzhijian.minimalistread.R;

/**
 * Created by huangzhijian on 2017/2/23.
 */
public class ToastUtil {

    static ToastUtil toastUtil;

    Context context;
    Toast toast;
    String msg;

    public ToastUtil(Context context){this.context = context;}

    public Toast create(){
        View view = View.inflate(context, R.layout.toast_dialog,null);
        TextView textView = (TextView) view.findViewById(R.id.tv_toast_msg);
        toast = new Toast(context);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0 ,0);
        toast.setDuration(Toast.LENGTH_LONG);
        textView.setText(msg);
        return toast;
    }

    public Toast shortCreate(){
        View view = View.inflate(context, R.layout.toast_dialog,null);
        TextView textView = (TextView) view.findViewById(R.id.tv_toast_msg);
        toast = new Toast(context);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0 ,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        textView.setText(msg);
        return toast;
    }

    public void setText(String string){
        this.msg =string;
    }

    public static void  show(int setId){show(App.getInstance().getString(setId));}

    public static void show(String string){
        if(toastUtil == null){
            toastUtil = new ToastUtil(App.getInstance());
        }
        toastUtil.setText(string);
        toastUtil.create().show();
    }

    public static void shortShow(String string){
        if(toastUtil == null){
            toastUtil = new ToastUtil(App.getInstance());
        }
        toastUtil.setText(string);
        toastUtil.shortCreate().show();
    }
}
