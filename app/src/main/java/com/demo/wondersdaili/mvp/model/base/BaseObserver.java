package com.demo.wondersdaili.mvp.model.base;

import android.util.Log;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by daili on 2017/3/9.
 */

public class BaseObserver<T> extends DisposableObserver<T> {

    public BaseObserver() {
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onNext(T t) {
        Log.i("BaseObserver", "onNext");
    }


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Log.i("BaseObserver", "onError" + e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.i("BaseObserver", "onCompleted");
    }
}
