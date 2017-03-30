package com.demo.wondersdaili.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baidu.location.BDLocation;
import com.demo.wondersdaili.mvp.Persenter.LocationContract;
import com.demo.wondersdaili.mvp.Persenter.LocationPersenter;
import com.demo.wondersdaili.mvp.Utils.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daili on 2017/3/20.
 */

public class BaseActivity extends AppCompatActivity implements LocationContract.View{
    protected LocationPersenter mLocationPersenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPermission();
        mLocationPersenter = App.getApplication().getComponent().getLocationPersenter();
        mLocationPersenter.register(this);
        mLocationPersenter.queryLateKnownLocation();
    }


    /***
     * loadLateKnownLocation
     * @param location
     */
    @Override
    public void loadLateKnownLocation(BDLocation location) {
        if (location != null) {
            String city = location.getCity();
            city = city.substring(0, city.length() - 1);
            App.setCity(city);
        }
    }

    /***
     * 获取定位失败,重试
     */
    @Override
    public void loadLateKnownLocationError(BDLocation location) {

    }

    /***
     * loadLocation
     * @param location
     */
    @Override
    public void loadLocation(BDLocation location) {

    }


    private void initPermission() {
        List<Integer> list = new ArrayList<>();
        list.add(PermissionUtils.CODE_ACCESS_COARSE_LOCATION);
        list.add(PermissionUtils.CODE_ACCESS_FINE_LOCATION);
        list.add(PermissionUtils.CODE_READ_PHONE_STATE);
        list.add(PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE);
        list.add(PermissionUtils.CODE_READ_EXTERNAL_STORAGE);
        for (int i = 0; i < list.size(); i++) {
            PermissionUtils.requestPermission(this, list.get(i), new PermissionUtils.PermissionGrant() {
                @Override
                public void onPermissionGranted(int requestCode) {

                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationPersenter.unRegister();
    }
}
