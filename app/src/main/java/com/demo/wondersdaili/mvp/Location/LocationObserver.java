package com.demo.wondersdaili.mvp.Location;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.baidu.location.BDLocation;
import com.demo.wondersdaili.mvp.BaseObserver;
import com.demo.wondersdaili.mvp.Utils.LocationUtil;

/**
 * Created by daili on 2017/3/21.
 */

public abstract class LocationObserver extends BaseObserver<BDLocation> {
    protected LocationObserver(Activity activity) {
        super(activity);
    }

    @Override
    public void onComplete() {
        super.onComplete();
    }

    @Override
    public void onError(Throwable throwable) {
        super.onError(throwable);
        //ToastUtils.showToast(App.getApplication(),"获取位置失败,错误信息:" + throwable.getMessage());
        onLocationFail(null);
    }

    @Override
    public void onNext(BDLocation location) {
        super.onNext(location);
        if (LocationUtil.isLocationResultEffective(location)) {
            onLocationSuccess(location);
        } else {
            onLocationFail(location);
        }
    }

    public abstract void onLocationSuccess(@NonNull BDLocation location);
    public abstract void onLocationFail(BDLocation location);
}