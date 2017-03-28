package com.demo.wondersdaili.mvp.Persenter;


/**
 * Created by daili on 2017/3/9.
 */

public interface WeatherInteractor<T> {
    void queryWeather(int format, String key, String cityName, boolean isRefreshing);

    void register(T t);


    void unRegister();

}
