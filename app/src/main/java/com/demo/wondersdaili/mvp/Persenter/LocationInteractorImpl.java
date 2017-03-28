package com.demo.wondersdaili.mvp.Persenter;

import android.support.annotation.NonNull;

import com.baidu.location.BDLocation;
import com.demo.wondersdaili.mvp.BaseActivity;
import com.demo.wondersdaili.mvp.Location.LocationSubsribe;
import com.demo.wondersdaili.mvp.Location.RxLocation;
import com.demo.wondersdaili.mvp.Utils.ToastUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Created by daili on 2017/3/21.
 */

public class LocationInteractorImpl implements LocationInteractor<BaseActivity> {
    private RxLocation mRxLocation;
    private BaseActivity mBaseActivity;
    private LocationSubsribe mSubscriber;

    @Inject
    public LocationInteractorImpl(RxLocation rxLocation) {
        mRxLocation = rxLocation;
    }

    @Override
    public void queryLocation() {
        mSubscriber = new LocationSubsribe(mBaseActivity) {
            @Override
            public void onLocationSuccess(@NonNull BDLocation location) {
                mBaseActivity.loadLocation(location);
            }

            @Override
            public void onLocationFail(BDLocation location) {
                ToastUtils.showToast(mBaseActivity,"获取定位失败");
            }
        };
        mRxLocation.locate(mBaseActivity).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }

    @Override
    public void queryLateKnownLocation() {
        mSubscriber = new LocationSubsribe(mBaseActivity) {
            @Override
            public void onLocationSuccess(@NonNull BDLocation location) {
                mBaseActivity.loadLateKnownLocation(location);
            }

            @Override
            public void onLocationFail(BDLocation location) {
                ToastUtils.showToast(mBaseActivity,"获取定位失败");
            }
        };
        mRxLocation.locateLastKnown(mBaseActivity).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }

    @Override
    public void register(BaseActivity activity) {
        mBaseActivity = activity;
    }


    @Override
    public void unRegister() {
        mBaseActivity = null;
    }
}
