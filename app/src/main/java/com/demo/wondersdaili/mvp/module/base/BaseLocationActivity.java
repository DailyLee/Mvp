package com.demo.wondersdaili.mvp.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baidu.location.BDLocation;
import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.persenter.location.LocationContract;
import com.demo.wondersdaili.mvp.persenter.location.LocationPersenter;
import com.demo.wondersdaili.mvp.utils.PermissionUtils;
import com.demo.wondersdaili.mvp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daili on 2017/3/20.
 */

public abstract class BaseLocationActivity extends BaseActivity implements LocationContract.View {
    protected LocationPersenter mLocationPersenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInjector() {
        mLocationPersenter = App.getApplication().getComponent().getLocationPersenter();
        mLocationPersenter.register(this);
    }



    /***
     * loadLocation
     * @param location
     */
    @Override
    public void loadLocation(BDLocation location) {
    }

    /***
     * 获取定位失败
     */
    @Override
    public void loadLocationError(BDLocation location) {
        ToastUtils.showToast(this,"定位失败,请稍后重试");
    }

    @Override
    protected void initPermission() {
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
