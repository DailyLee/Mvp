package com.demo.wondersdaili.mvp.model.weather;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by daili on 2017/3/9.
 * 聚合数据获取天气预报api
 */

public interface Api {
    @Headers("cache:3600")
    @GET("weather/index")
    Observable<WeatherBean> queryWeather(@Query("format") int format, @Query("key") String key, @Query("cityname") String city);
}
