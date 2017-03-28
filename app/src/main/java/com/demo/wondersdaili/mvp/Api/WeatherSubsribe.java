package com.demo.wondersdaili.mvp.Api;

import android.app.Activity;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.BaseSubsribe;
import com.demo.wondersdaili.mvp.Utils.ToastUtils;

/**
 * Created by daili on 2017/3/21.
 */

public abstract class WeatherSubsribe extends BaseSubsribe<WeatherBean> {

    public WeatherSubsribe(Activity activity) {
        super(activity);
    }


    @Override
    public void onComplete() {
        super.onComplete();

    }

    @Override
    public void onError(Throwable throwable) {
       super.onError(throwable);
        //ToastUtils.showToast(App.getApplication(), "获取天气失败,错误信息:" + throwable.getMessage());
        onQueryFail(null);
    }

    @Override
    public void onNext(WeatherBean weatherBean) {
        super.onNext(weatherBean);
        if (!"200".equals(weatherBean.getResultcode())) {
            ToastUtils.showToast(App.getApplication(), "获取天气失败,错误码:" + weatherBean.getError_code());
            onQueryFail(weatherBean);
        } else {
            if (weatherBean.getResult() != null) {
                onQuerySuccess(weatherBean);
            } else {
                onQueryFail(weatherBean);
            }
        }
    }

    public abstract void onQuerySuccess( WeatherBean weatherBean);

    public abstract void onQueryFail(WeatherBean weatherBean);
}
