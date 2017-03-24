package com.demo.wondersdaili.mvp;

import android.app.Application;

import com.demo.wondersdaili.mvp.Dagger2.AppComponent;
import com.demo.wondersdaili.mvp.Dagger2.DaggerAppComponent;
import com.demo.wondersdaili.mvp.Dagger2.AppModules;
import com.demo.wondersdaili.mvp.Location.LocationService;

/**
 * Created by daili on 2017/3/code10.
 */

public class App extends Application {

    private AppComponent mComponent;
    private static App app;
    private static String mCity;
    private static LocationService mLocationService;


    @Override
    public void onCreate() {
        super.onCreate();
        mCity = "北京市";
        app = this;
        //DaggerAppComponent需要rebuild
        mComponent = DaggerAppComponent.builder().appModules(new AppModules())
                .build();
        mComponent.inject(this);
        mLocationService = mComponent.getLocationService();
    }


    public static App getApplication() {
        return app;
    }

    public static void setCity(String city) {
        mCity = city;
    }

    public static String getCity() {
        return mCity;
    }

    public AppComponent getComponent() {
        return mComponent;
    }

    public static LocationService getLocationService(){
        return mLocationService;
    }
}
