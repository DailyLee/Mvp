package com.demo.wondersdaili.mvp.Dagger2;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.Persenter.WeatherInteractor;

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
