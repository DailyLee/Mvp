package com.demo.wondersdaili.mvp;

import android.app.Activity;
import android.util.Log;

import com.demo.wondersdaili.mvp.Utils.UIUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by daili on 2017/3/9.
 */

public  class BaseSubsribe<T> implements Observer<T> {
    private Activity mActivity;
    private Disposable mDisposable;

    public BaseSubsribe(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void onSubscribe(Disposable d) {
        UIUtils.showProgressBar(mActivity,"正在加载");
        mDisposable = d;
        Log.i("BaseSubsribe", "onStart");
    }

    @Override
    public void onNext(T t) {
        Log.i("BaseSubsribe", "onNext");
    }


    @Override
    public void onError(Throwable e) {
        UIUtils.hideProgressBar(mActivity);
        UIUtils.showErrorView(mActivity,"加载错误");
        if (!mDisposable.isDisposed()){
            mDisposable.dispose();
        }
        e.printStackTrace();
        Log.i("BaseSubsribe", "onError" + e.getMessage());
    }

    @Override
    public void onComplete() {
        UIUtils.hideProgressBar(mActivity);
        if (!mDisposable.isDisposed()){
            mDisposable.dispose();
        }
        Log.i("BaseSubsribe", "onCompleted");
    }
}
