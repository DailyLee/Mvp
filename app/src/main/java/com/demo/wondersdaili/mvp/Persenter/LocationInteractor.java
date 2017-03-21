package com.demo.wondersdaili.mvp.Persenter;


/**
 * Created by daili on 2017/3/9.
 */

public interface LocationInteractor<T> {
    void queryLocation();

    void queryLateKnownLocation();

    void register(T t);


    void unRegister();

}
