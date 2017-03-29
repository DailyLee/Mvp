package com.demo.wondersdaili.mvp.Persenter;


import android.support.v4.app.Fragment;

/**
 * Created by daili on 2017/3/9.
 */

public interface WeatherInteractor<T extends Fragment> {
    void queryWeather(int format, String key, String cityName, boolean isRefreshing);

    void register(T t);


    void unRegister();

}
