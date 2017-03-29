package com.demo.wondersdaili.mvp.Persenter;


import com.demo.wondersdaili.mvp.BaseActivity;

/**
 * Created by daili on 2017/3/9.
 */

public interface LocationInteractor<T extends BaseActivity> {
    void queryLocation();

    void queryLateKnownLocation();

    void register(T t);


    void unRegister();

}
