package com.demo.wondersdaili.mvp.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by daili on 2017/4/5.
 */

public class NetWorkUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm != null && cm.getActiveNetworkInfo().isAvailable();
    }
}
