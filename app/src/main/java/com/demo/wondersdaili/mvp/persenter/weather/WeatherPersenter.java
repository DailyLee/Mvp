package com.demo.wondersdaili.mvp.persenter.weather;

import com.demo.wondersdaili.mvp.model.weather.Api;
import com.demo.wondersdaili.mvp.model.weather.WeatherBean;
import com.demo.wondersdaili.mvp.model.weather.WeatherObserver;
import com.demo.wondersdaili.mvp.persenter.base.BaseView;
import com.demo.wondersdaili.mvp.view.base.LazyWeatherFragment;
import com.demo.wondersdaili.mvp.widget.EmptyLayout;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by daili on 2017/3/9.
 */

public class WeatherPersenter implements WeatherContract.Persenter {
    private Api mApi;
    private LazyWeatherFragment mView;
    private CompositeDisposable mDisposables;

    public WeatherPersenter(Api api) {
        mApi = api;
    }

    @Override
    public void queryWeather(final int format, final String key, final String cityName, final boolean isRefreshing) {
        WeatherObserver weatherSubsribe = new WeatherObserver() {

            @Override
            protected void onStart() {
                super.onStart();
                mView.showLoading(isRefreshing);
            }

            @Override
            public void onQuerySuccess(WeatherBean weatherBean) {
                mView.hideLoading();
                mView.finishRefresh();
                //获取数据成功显示数据
                mView.loadWeatherData(weatherBean.getResult());
            }

            @Override
            public void onQueryFail(WeatherBean weatherBean) {
                mView.hideLoading();
                if (weatherBean == null) {
                    mView.showNetError(new EmptyLayout.OnRetryListener() {
                        @Override
                        public void onRetry() {
                            queryWeather(format, key, cityName, false);
                        }
                    });
                }else {
                    mView.showNoData(weatherBean.getReason());
                }
                mView.loadErrorData(weatherBean);
            }
        };

        Observable<WeatherBean> observable = mApi.queryWeather(format, key, cityName);
        if (mDisposables == null) {
            mDisposables = new CompositeDisposable();
        }
        mDisposables.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribeWith(weatherSubsribe));

    }


    @Override
    public void register(BaseView baseView) {
        mView = (LazyWeatherFragment) baseView;
    }

    @Override
    public void unRegister() {
        mView = null;
       if (mDisposables != null) {
           mDisposables.dispose();
       }
    }
}
