package com.demo.wondersdaili.mvp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by daili on 2017/3/20.
 * 来自互联网
 */

public class ToastUtils {

    private static String oldMsg ;
    private static Toast toast = null ;
    private static long oneTime = 0 ;
    private static long twoTime = 0 ;

    /**
     * 显示Toast
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message){
        if(toast == null){
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show() ;
            oneTime = System.currentTimeMillis() ;
        }else{
            twoTime = System.currentTimeMillis() ;
            if(message.equals(oldMsg)){
                if(twoTime - oneTime > Toast.LENGTH_SHORT){
                    toast.show() ;
                }
            }else{
                oldMsg = message ;
                toast.setText(message) ;
                toast.show() ;
            }
        }
        oneTime = twoTime ;
    }
}

