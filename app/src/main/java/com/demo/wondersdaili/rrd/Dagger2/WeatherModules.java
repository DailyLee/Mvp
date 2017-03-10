package com.demo.wondersdaili.rrd.Dagger2;

import com.demo.wondersdaili.rrd.Api.Api;
import com.demo.wondersdaili.rrd.Api.ApiClient;
import com.demo.wondersdaili.rrd.Persenter.WeatherInteractor;
import com.demo.wondersdaili.rrd.Persenter.WeatherInteractorImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by daili on 2017/3/10.
 */
@Module
public class WeatherModules {

    @Provides
    public Retrofit providesApiClient(){
        return ApiClient.getInstance();
    }

    @Provides
    public Api providersApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Provides
    public WeatherInteractor providersInteractor(Api api) {
        return new WeatherInteractorImpl(api);
    }
}
