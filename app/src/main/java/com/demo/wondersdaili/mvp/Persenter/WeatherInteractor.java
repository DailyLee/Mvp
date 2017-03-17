package com.demo.wondersdaili.mvp.Persenter;


import rx.Subscription;

/**
 * Created by daili on 2017/3/9.
 */

public interface WeatherInteractor<T> {
    Subscription queryWeather(int format,String key,String cityName,boolean isRefreshing);

     void register(T t);


     void unRegister();

}
