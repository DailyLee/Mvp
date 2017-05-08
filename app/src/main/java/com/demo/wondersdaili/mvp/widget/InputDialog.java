package com.demo.wondersdaili.mvp.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.demo.wondersdaili.mvp.R;

/**
 * Created by daili on 2017/5/5.
 */

public class InputDialog extends Dialog {

    private Button mButCancel;
    private Button mButConfirm;
    private EditText mEtCity;
    private Context mContext;
    private View.OnClickListener mOnClickListener;

    public InputDialog(@NonNull Context context, View.OnClickListener onClickListener) {
        super(context);
        mContext = context;
        mOnClickListener = onClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog);
        mButCancel = (Button) findViewById(R.id.but_cancel);
        mButConfirm = (Button) findViewById(R.id.but_confirm);
        mEtCity = (EditText) findViewById(R.id.et_city);

        Window dialogWindow = this.getWindow();

        WindowManager m = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(p);
        mButCancel.setOnClickListener(mOnClickListener);
        mButConfirm.setOnClickListener(mOnClickListener);
        this.setCancelable(true);
    }

    public void ShowSoftInput() {
        mEtCity.requestFocus();
        InputMethodManager imm =
                (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        //imm.hideSoftInputFromWindow(mEtCity.getWindowToken(), 0);
        //imm.showSoftInputFromInputMethod(mEtCity.getWindowToken(), 0);
        imm.showSoftInput(mEtCity,InputMethodManager.SHOW_IMPLICIT);
    }

    public String getEditText() {
        return mEtCity.getText().toString().trim();
    }
}
