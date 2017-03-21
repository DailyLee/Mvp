package com.demo.wondersdaili.mvp.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
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


    /**
     * 显示加载弹窗
     *
     * @param activity
     * @param msg
     */
    public static Dialog showProgressBar(Activity activity, String msg) {
        if (activity == null || activity.isFinishing()) return null;
        hideProgressBar(activity);
        Dialog dialog = new Dialog(activity, R.style.FullScreenDialog);
        sDialogRef = new WeakReference<Dialog>(dialog);
        LayoutInflater mLayoutInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = mLayoutInflater.inflate(R.layout.dialog_loading, null);
        // 显示宽度为屏幕的3/5
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tvMsg = (TextView) layout.findViewById(R.id.txtv_loading_text);
        ImageView ivProgressBar = (ImageView) layout.findViewById(R.id.iv_progressbar);
        AnimationDrawable drawable = (AnimationDrawable) activity.getResources().getDrawable(R.drawable.loading_data);
        drawable.start();
        ivProgressBar.setImageDrawable(drawable);

        if (TextUtils.isEmpty(msg)) {
            tvMsg.setText("加载中");
        } else {
            tvMsg.setText(msg);
        }
        dialog.setContentView(layout, params);
        dialog.show();
        return dialog;
    }

    public static Dialog showErrorView(Activity activity, String msg) {
        if (activity == null || activity.isFinishing()) return null;
        hideProgressBar(activity);
        Dialog dialog = new Dialog(activity, R.style.FullScreenDialog);
        sDialogRef = new WeakReference<Dialog>(dialog);
        LayoutInflater mLayoutInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = mLayoutInflater.inflate(R.layout.dialog_error, null);
        // 显示宽度为屏幕的3/5
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tvMsg = (TextView) layout.findViewById(R.id.txtv_loading_text);
        ImageView ivErrorView = (ImageView) layout.findViewById(R.id.iv_errorview);
        ivErrorView.setImageResource(R.mipmap.unkonw);
        if (TextUtils.isEmpty(msg)) {
            tvMsg.setText("加载错误");
        } else {
            tvMsg.setText(msg);
        }
        dialog.setContentView(layout, params);
        dialog.show();
        return dialog;
    }

    /**
     * 隐藏进度条
     * hideProgressBar
     *
     * @param activity
     * @since 1.0
     */
    public static void hideProgressBar(Activity activity) {
        if (activity == null || activity.isFinishing()) return;
        if (sDialogRef != null && sDialogRef.get() != null) {
            try {
                sDialogRef.get().dismiss();
            } catch (Exception e) {
            } finally {
                sDialogRef.clear();
                sDialogRef = null;
            }
        }
    }
}
