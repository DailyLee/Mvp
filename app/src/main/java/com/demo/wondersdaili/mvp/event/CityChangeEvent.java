package com.demo.wondersdaili.mvp.event;

import com.demo.wondersdaili.mvp.App;

/**
 * Created by daili on 2017/7/24.
 * 当前城市改变事件
 */
public class CityChangeEvent {
    public String mCity;

    public CityChangeEvent(String city) {
        App.setCity(city);
        this.mCity = city;
    }
}
