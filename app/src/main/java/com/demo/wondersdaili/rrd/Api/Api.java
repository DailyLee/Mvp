package com.demo.wondersdaili.rrd.Api;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by daili on 2017/3/9.
 * 聚合数据获取天气预报api
 */

public interface Api {
    @GET("weather/index")
    Observable<GsonBean> queryWeather(@Query("key") String key, @Query("cityname") String city);
}
