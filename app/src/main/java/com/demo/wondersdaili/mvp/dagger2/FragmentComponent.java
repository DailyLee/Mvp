package com.demo.wondersdaili.mvp.dagger2;

import com.demo.wondersdaili.mvp.view.FutureWeatherWeatherFragment;
import com.demo.wondersdaili.mvp.view.TodayWeatherWeatherFragment;

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
    void inject(TodayWeatherWeatherFragment fragment);
    void inject(FutureWeatherWeatherFragment fragment);
}
