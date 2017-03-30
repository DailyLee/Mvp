package com.demo.wondersdaili.mvp.Dagger2;

import android.support.v4.app.Fragment;

import com.demo.wondersdaili.mvp.Persenter.WeatherPersenter;

import dagger.Component;

/** * Created by daili on 2017/3/30.
 */

@WeatherFragmentScope
@Component(
        modules = {
                FragmentModules.class,
        }
)

public interface FragmentComponent {
    void inject(Fragment fragment);

    WeatherPersenter getWeatherPersenter();

}
