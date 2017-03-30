package com.demo.wondersdaili.mvp.Persenter;


import com.demo.wondersdaili.mvp.Api.WeatherBean;
import com.demo.wondersdaili.mvp.View.BaseView;

/**
 * Created by daili on 2017/3/30.
 */

public interface WeatherContract {

    interface View extends BaseView {

        void loadWeatherData(WeatherBean.ResultBean resultBean);

        void loadErrorData(WeatherBean weatherBean);

    }

    interface Persenter extends BasePersenter {

        void queryWeather(int format, String key, String cityName, boolean isRefreshing);
    }
}
