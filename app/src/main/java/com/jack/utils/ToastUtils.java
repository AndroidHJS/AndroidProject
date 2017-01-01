package com.jack.utils;

import android.content.Context;
import android.widget.Toast;

import com.jack.AppApplication;

/**
 * Created by Administrator on 2016/12/28.
 */

public class ToastUtils {
    private static Context mContext= AppApplication.getAppApplicationContext();
    private  static  Toast mToast;
    public  static void showToast(String msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();

    }
    public  static  void  showToastLong(String  msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();
    }
}
