package com.demo.wondersdaili.mvp.Persenter;

import android.support.v4.app.Fragment;

import com.demo.wondersdaili.mvp.Api.Api;
import com.demo.wondersdaili.mvp.Api.WeatherSubsribe;
import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.Api.WeatherBean;
import com.demo.wondersdaili.mvp.Utils.ToastUtils;
import com.demo.wondersdaili.mvp.View.WeatherFragment;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by daili on 2017/3/9.
 */

public class WeatherInteractorImpl implements WeatherInteractor<Fragment> {
    private Api mApi;
    private WeatherSubsribe mWeatherSubsribe;
    private WeatherFragment mFragment;

    @Inject
    public WeatherInteractorImpl(Api api) {
        mApi = api;
    }


    @Override
    public Subscription queryWeather(int format, String key, String cityName, final boolean isRefreshing) {
        mWeatherSubsribe = new WeatherSubsribe(mFragment.getActivity()) {
            @Override
            public void onQuerySuccess(WeatherBean weatherBean) {
                if (isRefreshing) {
                    ToastUtils.showToast(App.getApplication(),"刷新成功");
                }
                //获取数据成功显示数据
                mFragment.loadWeatherData(weatherBean.getResult());
            }

            @Override
            public void onQueryFail(WeatherBean weatherBean) {
                mFragment.loadErrorData(weatherBean);
            }
        };

        Observable<WeatherBean> observable = mApi.queryWeather(format, key, cityName);
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(mWeatherSubsribe);
    }


    @Override
    public void register(Fragment fragment) {
        mFragment = (WeatherFragment) fragment;

    }

    @Override
    public void unRegister() {
        if (mWeatherSubsribe != null && mWeatherSubsribe.isUnsubscribed())
            mWeatherSubsribe.unsubscribe();
        mFragment = null;
    }
}
