package com.demo.wondersdaili.rrd.Persenter;

import android.app.Activity;

import rx.Subscription;

/**
 * Created by daili on 2017/3/9.
 */

public interface WeatherInteractor {
    Subscription queryWeather(String key,String cityName);

     void register(Activity activity);

     void unRegister();

}
