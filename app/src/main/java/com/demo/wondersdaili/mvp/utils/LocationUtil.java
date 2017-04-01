package com.demo.wondersdaili.mvp.utils;

import com.baidu.location.BDLocation;

/**
 * Created by daili on 2017/3/21.
 */

public class LocationUtil {
    public static boolean isLocationResultEffective(BDLocation bdLocation) {
        return bdLocation != null
                && (bdLocation.getLocType() == BDLocation.TypeGpsLocation
                || bdLocation.getLocType() == BDLocation.TypeNetWorkLocation);
    }
}
