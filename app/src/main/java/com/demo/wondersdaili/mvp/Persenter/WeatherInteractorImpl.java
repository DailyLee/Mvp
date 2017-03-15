package com.demo.wondersdaili.mvp.Persenter;

import android.app.Activity;
import android.widget.Toast;

import com.demo.wondersdaili.mvp.Api.Api;
import com.demo.wondersdaili.mvp.Api.MySubsribe;
import com.demo.wondersdaili.mvp.GsonBean;
import com.demo.wondersdaili.mvp.MainActivity;

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
    public WeatherInteractorImpl(Api api) {
        mApi = api;
    }


    @Override
    public Subscription queryWeather(int format, String key, String cityName) {
        mMySubsribe = new MySubsribe<GsonBean>() {
            @Override
            public void onNext(GsonBean gsonBean) {
                if (!"200".equals(gsonBean.getResultcode())) {
                    Toast.makeText(mActivity, "获取天气失败,错误码:" + gsonBean.getError_code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                //获取数据成功显示数据
                ((MainActivity) mActivity).loadWeatherData(gsonBean);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Toast.makeText(mActivity, "获取天气失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        Observable<GsonBean> observable = mApi.queryWeather(format, key, cityName);
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(mMySubsribe);
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
