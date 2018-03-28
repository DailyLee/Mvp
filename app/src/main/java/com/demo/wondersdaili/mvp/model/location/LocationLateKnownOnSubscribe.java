package com.demo.wondersdaili.mvp.model.location;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.utils.LocationUtil;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by daili on 2017/3/21.
 */

class LocationLateKnownOnSubscribe implements ObservableOnSubscribe<BDLocation> {

    @Override
    public void subscribe(final ObservableEmitter<BDLocation> subscriber) throws Exception {
        BDLocation lateKnownLocation = App.getLocationService().getLateKnownLocation();
        if (LocationUtil.isLocationResultEffective(lateKnownLocation)) {
            subscriber.onNext(lateKnownLocation);
            subscriber.onComplete();
        } else {
            BDLocationListener bdLocationListener = new BDLocationListener() {
                @Override
                public void onReceiveLocation(BDLocation bdLocation) {
                    subscriber.onNext(bdLocation);
                    subscriber.onComplete();
                }

                @Override
                public void onConnectHotSpotMessage(String s, int i) {

                }
            };
            App.getLocationService().locate(bdLocationListener);
        }
    }
}
