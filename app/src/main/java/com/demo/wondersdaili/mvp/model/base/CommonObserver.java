package com.demo.wondersdaili.mvp.model.base;

import android.util.Log;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by daili on 2017/3/9.
 */

public class CommonObserver<T> extends DisposableObserver<T> {

    public CommonObserver() {
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onNext(T t) {
        Log.i("CommonObserver", "onNext");
    }


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Log.i("CommonObserver", "onError" + e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.i("CommonObserver", "onCompleted");
    }
}
