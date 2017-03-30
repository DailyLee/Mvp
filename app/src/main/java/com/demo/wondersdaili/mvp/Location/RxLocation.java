package com.demo.wondersdaili.mvp.Location;

import android.content.Context;

import com.baidu.location.BDLocation;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by daili on 2017/3/21.
 */

public class RxLocation {
    private static RxLocation instance = null;

    private RxLocation () {}

    @Inject
    public static RxLocation getInstance() {
        synchronized (RxLocation.class) {
            if(instance == null){
                instance = new RxLocation();
            }
        }
        return instance;
    }

    public Observable<BDLocation> locate() {
        return Observable.create(new LocationOnSubscribe());
    }

    public Observable<BDLocation> locateLastKnown() {
        return Observable.create(new LocationLateKnownOnSubscribe());
    }
}
