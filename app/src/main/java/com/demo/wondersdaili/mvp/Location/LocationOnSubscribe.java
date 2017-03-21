package com.demo.wondersdaili.mvp.Location;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.demo.wondersdaili.mvp.App;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by daili on 2017/3/21.
 */

public class LocationOnSubscribe implements Observable.OnSubscribe<BDLocation> {
    private final Context context;

    public LocationOnSubscribe(Context context) {
        this.context = context;
    }

    @Override
    public void call(final Subscriber<? super BDLocation> subscriber) {
        BDLocationListener bdLocationListener = new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                subscriber.onNext(bdLocation);
                subscriber.onCompleted();
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {

            }
        };
        App.getLocationService().locate(bdLocationListener);
    }
}
