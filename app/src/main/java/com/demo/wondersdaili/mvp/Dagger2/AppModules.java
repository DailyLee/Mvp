package com.demo.wondersdaili.mvp.Dagger2;

import com.demo.wondersdaili.mvp.Api.Api;
import com.demo.wondersdaili.mvp.Api.ApiClient;
import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.Location.RxLocation;
import com.demo.wondersdaili.mvp.Persenter.LocationInteractor;
import com.demo.wondersdaili.mvp.Persenter.LocationInteractorImpl;
import com.demo.wondersdaili.mvp.Persenter.WeatherInteractor;
import com.demo.wondersdaili.mvp.Persenter.WeatherInteractorImpl;
import com.demo.wondersdaili.mvp.Location.LocationService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by daili on 2017/3/10.
 */
@Module
public class AppModules {

    @Provides
    public Retrofit providesApiClient(){
        return ApiClient.getInstance();
    }

    @Provides
    public Api providersApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Provides
    public WeatherInteractor providersWeatherInteractor(Api api) {
        return new WeatherInteractorImpl(api);
    }

    @Provides
    public LocationService providersLocation(){
        return new LocationService(App.getApplication());
    }

    @Provides
    public RxLocation providersRxLocation(){
        return RxLocation.getInstance();
    }

    @Provides
    public LocationInteractor providersLocationInteractor(RxLocation rxLocation) {
        return new LocationInteractorImpl(rxLocation);
    }
}
