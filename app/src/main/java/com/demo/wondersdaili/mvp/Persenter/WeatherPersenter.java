package com.demo.wondersdaili.mvp.Persenter;

import com.demo.wondersdaili.mvp.Api.Api;
import com.demo.wondersdaili.mvp.Api.WeatherBean;
import com.demo.wondersdaili.mvp.Api.WeatherObserver;
import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.Utils.ToastUtils;
import com.demo.wondersdaili.mvp.View.BaseView;
import com.demo.wondersdaili.mvp.View.WeatherFragment;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by daili on 2017/3/9.
 */

public class WeatherPersenter implements WeatherContract.Persenter {
    private Api mApi;
    private WeatherFragment mView;

    public WeatherPersenter(Api api) {
        mApi = api;
    }

    @Override
    public void queryWeather(int format, String key, String cityName, final boolean isRefreshing) {
        WeatherObserver weatherSubsribe = new WeatherObserver(mView.getActivity()) {
            @Override
            public void onQuerySuccess(WeatherBean weatherBean) {
                if (isRefreshing) {
                    ToastUtils.showToast(App.getApplication(), "刷新成功");
                }
                //获取数据成功显示数据
                mView.loadWeatherData(weatherBean.getResult());
            }

            @Override
            public void onQueryFail(WeatherBean weatherBean) {
                mView.loadErrorData(weatherBean);
            }
        };

        Observable<WeatherBean> observable = mApi.queryWeather(format, key, cityName);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(weatherSubsribe);
    }


    @Override
    public void register(BaseView baseView) {
        mView = (WeatherFragment) baseView;
    }

    @Override
    public void unRegister() {
        mView = null;
    }
}
