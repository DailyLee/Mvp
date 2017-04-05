package com.demo.wondersdaili.mvp.model.weather;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.Constants;
import com.demo.wondersdaili.mvp.utils.NetWorkUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daili on 2017/3/9.
 */

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            //缓存容量
            long SIZE_OF_CACHE = 1024 * 1024; // 1 MiB
            //缓存路径
            String cacheFile = App.getApplication().getCacheDir() + "/http";
            Cache cache = new Cache(new File(cacheFile), SIZE_OF_CACHE);
            //利用okhttp实现缓存
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时
                    .readTimeout(10, TimeUnit.SECONDS)//读取超时
                    .writeTimeout(10, TimeUnit.SECONDS)//写入超时
                    .addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)//有网络时的拦截器
                    .addInterceptor(REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)//没网络时的拦截器
                    .cache(cache)
                    .build();
            GsonConverterFactory factory = GsonConverterFactory.create();
            retrofit = new Retrofit.Builder().client(okHttpClient)
                    .baseUrl(Constants.BaseUrl)
                    .addConverterFactory(factory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static final int TIMEOUT_CONNECT = 30 * 60; //半小时
    private static final int TIMEOUT_DISCONNECT = 60 * 60 * 24 * 7; //7天

    private static final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            //获取retrofit @headers里面的参数
            String cache = chain.request().header("cache");
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            String cacheControl = originalResponse.header("Cache-Control");
            //如果cacheControl为空，就让他TIMEOUT_CONNECT秒的缓存
            if (cacheControl == null) {
                //如果cache没值，缓存时间为TIMEOUT_CONNECT，有的话就为cache的值
                if (cache == null || "".equals(cache)) {
                    cache = TIMEOUT_CONNECT + "";
                }
                originalResponse = originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + cache)
                        .build();
                return originalResponse;
            } else {
                return originalResponse;
            }
        }
    };

    private static final Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
           //离线的时候为7天的缓存。
            if (!NetWorkUtils.isNetworkAvailable(App.getApplication())) {
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + TIMEOUT_DISCONNECT)
                        .build();
            }
            return chain.proceed(request);
        }
    };
}
