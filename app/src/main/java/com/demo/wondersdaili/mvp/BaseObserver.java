package com.demo.wondersdaili.mvp;

import android.app.Activity;
import android.util.Log;

import com.demo.wondersdaili.mvp.Utils.UIUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by daili on 2017/3/9.
 */

public class BaseObserver<T> implements Observer<T> {
    Activity mActivity;

    private Disposable mDisposable;

    public BaseObserver(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void onSubscribe(Disposable d) {
        UIUtils.showProgressBar(mActivity, "正在加载", true);
        mDisposable = d;
        Log.i("BaseObserver", "onStart");
    }

    @Override
    public void onNext(T t) {
        Log.i("BaseObserver", "onNext");
    }


    @Override
    public void onError(Throwable e) {
        UIUtils.showErrorView(mActivity, "加载错误", true);
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        e.printStackTrace();
        Log.i("BaseObserver", "onError" + e.getMessage());
    }

    @Override
    public void onComplete() {
        UIUtils.hideProgressBar(mActivity);
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        Log.i("BaseObserver", "onCompleted");
    }
}
