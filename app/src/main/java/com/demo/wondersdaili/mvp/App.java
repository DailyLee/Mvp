package com.demo.wondersdaili.mvp;

import android.app.Application;

import com.demo.wondersdaili.mvp.Dagger2.AppComponent;
import com.demo.wondersdaili.mvp.Dagger2.DaggerAppComponent;
import com.demo.wondersdaili.mvp.Dagger2.WeatherModules;

/**
 * Created by daili on 2017/3/10.
 */

public class App extends Application {

    private AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerAppComponent.builder().weatherModules(new WeatherModules())
                .build();
        mComponent.inject(this);
    }

    public AppComponent getComponent() {
        return mComponent;
    }
}
