package com.demo.wondersdaili.rrd.Dagger2;

import com.demo.wondersdaili.rrd.App;
import com.demo.wondersdaili.rrd.Persenter.WeatherInteractor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by daili on 2017/3/10.
 */

@Singleton
@Component(
        modules = {
                WeatherModules.class,
        }
)

public interface AppComponent {
    void inject(App app);

    WeatherInteractor getWeatherInteractor();
}
