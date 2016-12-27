package com.jack.utils;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/12/25.
 */

public class FileUtils {
    /**
     * 获取缓存存储路径
     * @param context 上下对象
     * @param uniqueName  文件夹名字
     * @return
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (getExitSDCard()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }
    public  static boolean getExitSDCard(){
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable();
    }
    public  static  boolean writeString(String result, OutputStream out){
        out=new BufferedOutputStream(out);
        try {
            out.write(result.getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("写入数据不成功");
            return false;
        }finally {
           closeStream(out);
        }

    }
    public  static String readString(InputStream in){
        StringBuilder mStr=new StringBuilder();
        BufferedInputStream bufOut=new BufferedInputStream(in);
        byte[] buffer = new byte[1024];
        int bytesRead ;
        try {
            while ((bytesRead = bufOut.read(buffer)) != -1) {
                 String chunk = new String(buffer, 0, bytesRead);
                 mStr.append(chunk);
            }
            return mStr.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return   mStr.toString();
    }
    public  static  void  closeStream( OutputStream out){
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
