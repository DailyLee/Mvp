package com.demo.wondersdaili.rrd.Persenter;

import android.app.Activity;

import com.demo.wondersdaili.rrd.Api.Api;
import com.demo.wondersdaili.rrd.Api.GsonBean;
import com.demo.wondersdaili.rrd.Api.MySubsribe;
import com.demo.wondersdaili.rrd.MainActivity;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by daili on 2017/3/9.
 */

public class WeatherInteractorImpl implements WeatherInteractor {
    private Api mApi;
    private MySubsribe<GsonBean> mMySubsribe;
    private Activity mActivity;

    @Inject
    public WeatherInteractorImpl(Api api){
        mApi = api;
    }


    @Override
    public Subscription queryWeather(String key, String cityName) {
        mMySubsribe = new MySubsribe<GsonBean>(){
            @Override
            public void onNext(GsonBean gsonBean) {
                //获取数据成功显示数据
                ((MainActivity)mActivity).loadWeatherData(gsonBean);
            }
        };

        Observable<GsonBean> observable = mApi.queryWeather(key, cityName);
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(mMySubsribe);
        return subscription;
    }

    @Override
    public void register(Activity activity) {
        mActivity = activity;

    }


    @Override
    public void unRegister() {
        if (mMySubsribe != null && mMySubsribe.isUnsubscribed())
            mMySubsribe.unsubscribe();
        mActivity = null;
    }
}
