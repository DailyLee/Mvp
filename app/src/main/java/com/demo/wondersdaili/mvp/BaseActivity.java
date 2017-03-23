package com.demo.wondersdaili.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baidu.location.BDLocation;
import com.demo.wondersdaili.mvp.Persenter.LocationInteractor;
import com.demo.wondersdaili.mvp.Utils.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daili on 2017/3/20.
 */

public class BaseActivity<T> extends AppCompatActivity {
    private List<Integer> mList;
    protected LocationInteractor mLocationInteractor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPermission();
        mLocationInteractor = App.getApplication().getComponent().getLocationInteractor();
        mLocationInteractor.register(this);
        mLocationInteractor.queryLateKnownLocation();
    }


    /***
     * loadLateKnownLocation
     * @param t
     */
    public void loadLateKnownLocation(T t) {
        if(t != null){
            String city = ((BDLocation) t).getCity();
            city = city.substring(0,city.length()-1);
            App.setCity(city);
        }
    }

    /***
     * loadLocation
     * @param t
     */
    public void loadLocation(T t){

    }



    private void initPermission() {
        mList = new ArrayList<>();
        mList.add(PermissionUtils.CODE_ACCESS_COARSE_LOCATION);
        mList.add(PermissionUtils.CODE_ACCESS_FINE_LOCATION);
        mList.add(PermissionUtils.CODE_READ_PHONE_STATE);
        mList.add(PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE);
        mList.add(PermissionUtils.CODE_READ_EXTERNAL_STORAGE);
        for (int i = 0; i < mList.size(); i++) {
            PermissionUtils.requestPermission(this, mList.get(i), new PermissionUtils.PermissionGrant() {
                @Override
                public void onPermissionGranted(int requestCode) {

                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationInteractor.unRegister();
    }
}
