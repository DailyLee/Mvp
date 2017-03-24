package com.demo.wondersdaili.mvp.Api;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by daili on 2017/3/9.
 * 聚合数据获取天气预报api
 */

public interface Api {
    @GET("weather/index")
    Observable<WeatherBean> queryWeather(@Query("format") int format, @Query("key") String key, @Query("cityname") String city);
}
