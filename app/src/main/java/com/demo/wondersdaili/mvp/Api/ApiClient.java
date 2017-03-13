package com.demo.wondersdaili.mvp.Api;

import com.demo.wondersdaili.mvp.ConstantApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daili on 2017/3/9.
 */

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getInstance(){
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient();
            OkHttpClient.Builder builder = okHttpClient.newBuilder();
            builder.retryOnConnectionFailure(true);
            GsonConverterFactory factory = GsonConverterFactory.create();
            retrofit = new Retrofit.Builder().client(okHttpClient)
                    .baseUrl(ConstantApi.BaseUrl)
                    .addConverterFactory(factory)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
