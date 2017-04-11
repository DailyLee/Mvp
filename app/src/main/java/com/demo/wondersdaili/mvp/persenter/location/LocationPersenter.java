package com.demo.wondersdaili.mvp.persenter.location;

import android.support.annotation.NonNull;

import com.baidu.location.BDLocation;
import com.demo.wondersdaili.mvp.model.location.LocationObserver;
import com.demo.wondersdaili.mvp.model.location.RxLocation;
import com.demo.wondersdaili.mvp.persenter.base.BaseView;
import com.demo.wondersdaili.mvp.view.base.BaseLocationActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by daili on 2017/3/21.
 */

public class LocationPersenter implements LocationContract.Persenter {
    private RxLocation mRxLocation;
    private BaseLocationActivity mLocationView;
    private LocationObserver mSubscriber;
    private CompositeDisposable mDisposables;

    public LocationPersenter(RxLocation rxLocation) {
        mRxLocation = rxLocation;
    }

    @Override
    public void queryLocation() {
        mSubscriber = new LocationObserver() {
            @Override
            public void onLocationSuccess(@NonNull BDLocation location) {
                mLocationView.loadLocation(location);
            }

            @Override
            public void onLocationFail(BDLocation location) {
                mLocationView.loadLocationError(location);
            }
        };
        if (mDisposables == null) {
            mDisposables = new CompositeDisposable();
        }
        mDisposables.add(mRxLocation.locate().observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(mSubscriber));

    }

    @Override
    public void queryLateKnownLocation() {
        mSubscriber = new LocationObserver() {
            @Override
            public void onLocationSuccess(@NonNull BDLocation location) {
                mLocationView.loadLocation(location);
            }

            @Override
            public void onLocationFail(BDLocation location) {
                mLocationView.loadLocationError(location);
            }
        };
        if (mDisposables == null) {
            mDisposables = new CompositeDisposable();
        }
        mDisposables.add(mRxLocation.locateLastKnown().observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(mSubscriber));
    }


    @Override
    public void register(BaseView baseView) {
        mLocationView = (BaseLocationActivity) baseView;
    }

    @Override
    public void unRegister() {
        mLocationView = null;
        if (mDisposables != null) {
            mDisposables.dispose();
        }
    }
}
