package com.demo.wondersdaili.mvp.model.bean;

import android.databinding.BaseObservable;
import android.graphics.drawable.Drawable;

import static com.demo.wondersdaili.mvp.utils.CommonUtil.getImageFuture;

/**
 * Created by daili on 2017/7/24.
 */
public class FutureBean extends BaseObservable {
    /**
     * temperature : 6℃~code14℃
     * weather : 多云转阴
     * weather_id : {"fa":"code01","fb":"code02"}
     * wind : 东风3-4 级
     * week : 星期三
     * date : 20170315
     */

    private String temperature;
    private String weather;
    private WeatherIdBean weather_id;
    private String wind;
    private String week;
    private String date;

    public Drawable getImageFutureFa() {
        return getImageFuture(getWeather_id().getFa());
    }

    public Drawable getImageFutureFb() {
        return getImageFuture(getWeather_id().getFb());
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public WeatherIdBean getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(WeatherIdBean weather_id) {
        this.weather_id = weather_id;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
