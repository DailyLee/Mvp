package com.demo.wondersdaili.mvp.Api;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.GsonBean;
import com.demo.wondersdaili.mvp.MySubsribe;
import com.demo.wondersdaili.mvp.Utils.ToastUtils;

/**
 * Created by daili on 2017/3/21.
 */

public abstract class WeatherSubsribe extends MySubsribe<GsonBean> {

    public WeatherSubsribe(Activity activity) {
        super(activity);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onCompleted() {
        super.onCompleted();

    }

    @Override
    public void onError(Throwable throwable) {
       super.onError(throwable);
        //ToastUtils.showToast(App.getApplication(), "获取天气失败,错误信息:" + throwable.getMessage());
        onQueryFail(null);
    }

    @Override
    public void onNext(GsonBean gsonBean) {
        super.onNext(gsonBean);
        if (!"200".equals(gsonBean.getResultcode())) {
            ToastUtils.showToast(App.getApplication(), "获取天气失败,错误码:" + gsonBean.getError_code());
            onQueryFail(gsonBean);
        } else {
            if (gsonBean.getResult() != null) {
                onQuerySuccess(gsonBean);
            } else {
                onQueryFail(gsonBean);
            }
        }
    }

    public abstract void onQuerySuccess(@NonNull GsonBean gsonBean);

    public abstract void onQueryFail(GsonBean gsonBean);
}
