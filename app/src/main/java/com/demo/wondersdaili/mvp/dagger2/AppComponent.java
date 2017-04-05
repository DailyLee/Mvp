package com.demo.wondersdaili.mvp.dagger2;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.persenter.location.LocationPersenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by daili on 2017/3/code10.
 */

@Singleton
@Component(
        modules = {
                AppModules.class,
        }
)

public interface AppComponent {
    void inject(App app);

    LocationPersenter getLocationPersenter();
}
