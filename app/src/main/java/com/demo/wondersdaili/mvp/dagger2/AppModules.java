package com.demo.wondersdaili.mvp.dagger2;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.location.LocationService;
import com.demo.wondersdaili.mvp.location.RxLocation;
import com.demo.wondersdaili.mvp.persenter.LocationPersenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daili on 2017/3/10.
 */
@Module
public class AppModules {

    @Provides
    public LocationService providersLocation(){
        return new LocationService(App.getApplication());
    }

    @Provides
    public RxLocation providersRxLocation(){
        return RxLocation.getInstance();
    }

    @Provides
    public LocationPersenter providersLocationPersenter (RxLocation rxLocation) {
        return new LocationPersenter(rxLocation);
    }
}
