package com.demo.wondersdaili.mvp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.wondersdaili.mvp.R;

import java.lang.ref.WeakReference;

/**
 * Created by daili on 2017/3/21.
 */

public class UIUtils {
    private static WeakReference<Dialog> sDialogRef;
    private static WeakReference<Dialog> sDialogReTry;


    /**
     * 显示加载弹窗
     *
     * @param activity
     * @param msg
     * @param canceledOnTouchOutside
     */
    public static Dialog showProgressBar(Activity activity, String msg,boolean canceledOnTouchOutside) {
        if (activity == null || activity.isFinishing()) return null;
        hideProgressBar(activity);
        Dialog dialog = new Dialog(activity, R.style.FullScreenDialog);
        sDialogRef = new WeakReference<>(dialog);
        LayoutInflater mLayoutInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = mLayoutInflater.inflate(R.layout.dialog_notice, null);
        // 显示宽度为屏幕的3/5
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tvMsg = (TextView) layout.findViewById(R.id.tv_textview);
        ImageView ivProgressBar = (ImageView) layout.findViewById(R.id.iv_imageview);
        AnimationDrawable drawable = (AnimationDrawable) activity.getResources().getDrawable(R.drawable.loading_data);
        drawable.start();
        ivProgressBar.setImageDrawable(drawable);

        if (TextUtils.isEmpty(msg)) {
            tvMsg.setText("加载中");
        } else {
            tvMsg.setText(msg);
        }
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        dialog.setContentView(layout, params);
        dialog.show();
        return dialog;
    }

    /**
     * 显示重试弹窗
     * @param activity
     * @param msg
     * @param canceledOnTouchOutside
     * @param listener
     */
    public static Dialog showReTryView(Activity activity, String msg, boolean canceledOnTouchOutside, View.OnClickListener listener) {
        if (activity == null || activity.isFinishing()) return null;
        hideProgressBar(activity);
        Dialog dialog = new Dialog(activity, R.style.FullScreenDialog);
        sDialogReTry = new WeakReference<>(dialog);
        LayoutInflater mLayoutInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = mLayoutInflater.inflate(R.layout.dialog_notice, null);
        // 显示宽度为屏幕的3/5
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tvMsg = (TextView) layout.findViewById(R.id.tv_textview);
        ImageView iv = (ImageView) layout.findViewById(R.id.iv_imageview);
        iv.setOnClickListener(listener);
        Drawable drawable = activity.getResources().getDrawable(R.mipmap.retry);
        iv.setImageDrawable(drawable);

        if (TextUtils.isEmpty(msg)) {
            tvMsg.setText("点击重试");
        } else {
            tvMsg.setText(msg);
        }
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        dialog.setContentView(layout, params);
        dialog.show();
        return dialog;
    }

    public static Dialog showErrorView(Activity activity, String msg,boolean canceledOnTouchOutside) {
        if (activity == null || activity.isFinishing()) return null;
        hideProgressBar(activity);
        Dialog dialog = new Dialog(activity, R.style.FullScreenDialog);
        sDialogRef = new WeakReference<>(dialog);
        LayoutInflater mLayoutInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = mLayoutInflater.inflate(R.layout.dialog_notice, null);
        // 显示宽度为屏幕的3/5
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tvMsg = (TextView) layout.findViewById(R.id.tv_textview);
        ImageView ivErrorView = (ImageView) layout.findViewById(R.id.iv_imageview);
        ivErrorView.setImageResource(R.mipmap.unkonw);
        if (TextUtils.isEmpty(msg)) {
            tvMsg.setText("加载错误");
        } else {
            tvMsg.setText(msg);
        }
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        dialog.setContentView(layout, params);
        dialog.show();
        return dialog;
    }

    /**
     * 隐藏进度条
     * hideProgressBar
     * @param activity
     */
    public static void hideProgressBar(Activity activity) {
        if (activity == null || activity.isFinishing()) return;
        if (sDialogRef != null && sDialogRef.get() != null) {
            try {
                sDialogRef.get().dismiss();
            } finally {
                sDialogRef.clear();
                sDialogRef = null;
            }
        }
    }

    /**
     * 隐藏重试弹窗
     * hideReTryViewBar
     * @param activity
     */
    public static void hideReTryView(Activity activity) {
        if (activity == null || activity.isFinishing()) return;
        if (sDialogReTry != null && sDialogReTry.get() != null) {
            try {
                sDialogReTry.get().dismiss();
            } finally {
                sDialogReTry.clear();
                sDialogReTry = null;
            }
        }
    }
}
