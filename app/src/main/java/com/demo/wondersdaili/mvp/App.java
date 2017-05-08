package com.demo.wondersdaili.mvp;

import android.app.Application;

import com.demo.wondersdaili.mvp.dagger2.AppComponent;
import com.demo.wondersdaili.mvp.dagger2.DaggerAppComponent;
import com.demo.wondersdaili.mvp.dagger2.AppModules;
import com.demo.wondersdaili.mvp.model.location.LocationService;

import javax.inject.Inject;

/**
 * Created by daili on 2017/3/10.
 */

public class App extends Application {

    private AppComponent mComponent;
    private static App app;
    private static String mCity;
    @Inject
    static LocationService mLocationService;


    @Override
    public void onCreate() {
        super.onCreate();
        mCity = "北京";
        app = this;
        //DaggerAppComponent需要rebuild
        mComponent = DaggerAppComponent.builder().appModules(new AppModules())
                .build();
        mComponent.inject(this);
        CrashHandler.getInstance().init();
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
