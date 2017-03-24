package com.demo.wondersdaili.mvp;

import android.app.Activity;
import android.util.Log;

import com.demo.wondersdaili.mvp.Utils.UIUtils;

import rx.Subscriber;

/**
 * Created by daili on 2017/3/9.
 */

public abstract class BaseSubsribe<T> extends Subscriber<T> {
    private Activity mActivity;

    public BaseSubsribe(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        UIUtils.showProgressBar(mActivity,"正在加载");
        Log.i("BaseSubsribe", "onStart");
    }


    @Override
    public void onCompleted() {
        UIUtils.hideProgressBar(mActivity);
        Log.i("BaseSubsribe", "onCompleted");
    }

    @Override
    public void onNext(T t) {
        Log.i("BaseSubsribe", "onNext");
    }


    @Override
    public void onError(Throwable e) {
        UIUtils.hideProgressBar(mActivity);
        UIUtils.showErrorView(mActivity,"加载错误");
        e.printStackTrace();
        Log.i("BaseSubsribe", "onError" + e.getMessage());
    }
}
