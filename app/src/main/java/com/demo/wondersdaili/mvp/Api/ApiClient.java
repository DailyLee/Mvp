package com.demo.wondersdaili.mvp.Api;

import com.demo.wondersdaili.mvp.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
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
                    .baseUrl(Constants.BaseUrl)
                    .addConverterFactory(factory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
