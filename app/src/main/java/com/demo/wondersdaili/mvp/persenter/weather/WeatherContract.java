package com.demo.wondersdaili.mvp.persenter.weather;


import com.demo.wondersdaili.mvp.model.bean.WeatherBean;
import com.demo.wondersdaili.mvp.persenter.base.BasePersenter;
import com.demo.wondersdaili.mvp.persenter.base.BaseRefreshView;

/**
 * Created by daili on 2017/3/30.
 */

public interface WeatherContract {

    interface View extends BaseRefreshView {

        void loadWeatherData(WeatherBean.ResultBean resultBean);

        void loadErrorData(WeatherBean weatherBean);

    }

    interface Persenter extends BasePersenter {

        void queryWeather(int format, String key, String cityName, boolean isRefreshing);

    }
}
