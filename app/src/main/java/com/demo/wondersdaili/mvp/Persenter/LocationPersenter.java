package com.demo.wondersdaili.mvp.Persenter;

import android.support.annotation.NonNull;

import com.baidu.location.BDLocation;
import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.BaseActivity;
import com.demo.wondersdaili.mvp.Location.LocationObserver;
import com.demo.wondersdaili.mvp.Location.RxLocation;
import com.demo.wondersdaili.mvp.Utils.ToastUtils;
import com.demo.wondersdaili.mvp.View.BaseView;

import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Created by daili on 2017/3/21.
 */

public class LocationPersenter implements LocationContract.Persenter {
    private RxLocation mRxLocation;
    private BaseActivity mLocationView;
    private LocationObserver mSubscriber;

    public LocationPersenter(RxLocation rxLocation) {
        mRxLocation = rxLocation;
    }

    @Override
    public void queryLocation() {
        mSubscriber = new LocationObserver(mLocationView) {
            @Override
            public void onLocationSuccess(@NonNull BDLocation location) {
                mLocationView.loadLocation(location);
            }

            @Override
            public void onLocationFail(BDLocation location) {
                ToastUtils.showToast(App.getApplication(),"获取定位失败");
            }
        };
        mRxLocation.locate().observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }

    @Override
    public void queryLateKnownLocation() {
        mSubscriber = new LocationObserver(mLocationView) {
            @Override
            public void onLocationSuccess(@NonNull BDLocation location) {
                mLocationView.loadLateKnownLocation(location);
            }

            @Override
            public void onLocationFail(BDLocation location) {
                ToastUtils.showToast(App.getApplication(),"获取定位失败");
                mLocationView.loadLateKnownLocationError(location);
            }
        };
        mRxLocation.locateLastKnown().observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }



    @Override
    public void register(BaseView baseView) {
        mLocationView = (BaseActivity) baseView;
    }

    @Override
    public void unRegister() {
        mLocationView = null;
    }
}
