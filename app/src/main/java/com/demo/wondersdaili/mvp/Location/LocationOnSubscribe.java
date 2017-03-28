package com.demo.wondersdaili.mvp.Location;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.demo.wondersdaili.mvp.App;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


/**
 * Created by daili on 2017/3/21.
 */

public class LocationOnSubscribe implements ObservableOnSubscribe<BDLocation> {
    private final Context context;

    public LocationOnSubscribe(Context context) {
        this.context = context;
    }

    @Override
    public void subscribe(final ObservableEmitter<BDLocation> subscriber) throws Exception {
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
