package com.demo.wondersdaili.mvp.Api;

import android.util.Log;

import rx.Subscriber;

/**
 * Created by daili on 2017/3/9.
 */

public abstract class MySubsribe<T> extends Subscriber<T> {
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(T t) {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Log.i("MySubsribe", "onError" + e.getMessage());
    }
}
