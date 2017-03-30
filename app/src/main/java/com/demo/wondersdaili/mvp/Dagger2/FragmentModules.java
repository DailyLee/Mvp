package com.demo.wondersdaili.mvp.Dagger2;

import android.app.Activity;

import com.demo.wondersdaili.mvp.Api.Api;
import com.demo.wondersdaili.mvp.Api.ApiClient;
import com.demo.wondersdaili.mvp.Persenter.WeatherPersenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by daili on 2017/3/10.
 */
@Module
public class FragmentModules {
    private Activity mActivity;

    public FragmentModules(Activity activity) {
        mActivity = activity;
    }

    @Provides
    public Retrofit providesApiClient() {
        return ApiClient.getInstance();
    }

    @Provides
    public Api providersApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Provides
    public WeatherPersenter providersWeatherPersenter(Api api) {
        return new WeatherPersenter(api);
    }

}
