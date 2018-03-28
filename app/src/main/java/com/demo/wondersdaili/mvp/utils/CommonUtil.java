package com.demo.wondersdaili.mvp.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.R;

/**
 * Created by daili on 2017/5/5.
 */
public class CommonUtil {

    public static int dp2px(float dpValue, Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static boolean isChinese(String string) {
        int n = 0;
        for (int i = 0; i < string.length(); i++) {
            n = (int) string.charAt(i);
            if (!(19968 <= n && n < 40869)) {
                return false;
            }
        }
        return true;
    }

    public static int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    public static int getMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    public static Drawable getImageFuture(String ff) {
        int[] drawableIds = {R.mipmap.code00, R.mipmap.code01, R.mipmap.code02,
                R.mipmap.code03, R.mipmap.code04, R.mipmap.code05,
                R.mipmap.code06, R.mipmap.code07, R.mipmap.code08,
                R.mipmap.code09, R.mipmap.code10, R.mipmap.code11,
                R.mipmap.code12, R.mipmap.code13, R.mipmap.code14,
                R.mipmap.code15, R.mipmap.code16, R.mipmap.code17,
                R.mipmap.code18, R.mipmap.code19, R.mipmap.code20,
        };
        int drawableId;
        try {
            int i = Integer.parseInt(ff);
            if (i == 53) {
                return App.getApplication().getResources().getDrawable(R.mipmap.code53);
            }
            drawableId = drawableIds[i];
        } catch (Exception e) {
            return App.getApplication().getResources().getDrawable(R.mipmap.unkonw);
        }
        return App.getApplication().getResources().getDrawable(drawableId);
    }
}