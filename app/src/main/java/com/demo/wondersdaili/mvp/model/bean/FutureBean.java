package com.demo.wondersdaili.mvp.model.bean;

import android.databinding.BaseObservable;
import android.graphics.drawable.Drawable;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.R;

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
    private WeatherIdBeanX weather_id;
    private String wind;
    private String week;
    private String date;

    public Drawable getImageFutureFa() {
        int[] drawableIds = {R.mipmap.code00, R.mipmap.code01, R.mipmap.code02,
                R.mipmap.code03, R.mipmap.code04, R.mipmap.code05,
                R.mipmap.code06, R.mipmap.code07, R.mipmap.code08,
                R.mipmap.code09, R.mipmap.code10, R.mipmap.code11,
                R.mipmap.code12, R.mipmap.code13, R.mipmap.code14,
                R.mipmap.code15, R.mipmap.code16, R.mipmap.code17,
                R.mipmap.code18, R.mipmap.code19, R.mipmap.code20,
        };
        int drawableId;
        try {
            int i = Integer.parseInt(getWeather_id().fa);
            if (i == 53) {
                return App.getApplication().getResources().getDrawable(R.mipmap.code53);
            }
            drawableId = drawableIds[i];
        } catch (Exception e) {
            return App.getApplication().getResources().getDrawable(R.mipmap.unkonw);
        }
        return App.getApplication().getResources().getDrawable(drawableId);
    }

    public Drawable getImageFutureFb() {
        int[] drawableIds = {R.mipmap.code00, R.mipmap.code01, R.mipmap.code02,
                R.mipmap.code03, R.mipmap.code04, R.mipmap.code05,
                R.mipmap.code06, R.mipmap.code07, R.mipmap.code08,
                R.mipmap.code09, R.mipmap.code10, R.mipmap.code11,
                R.mipmap.code12, R.mipmap.code13, R.mipmap.code14,
                R.mipmap.code15, R.mipmap.code16, R.mipmap.code17,
                R.mipmap.code18, R.mipmap.code19, R.mipmap.code20,
        };
        int drawableId;
        try {
            int i = Integer.parseInt(getWeather_id().fb);
            if (i == 53) {
                return App.getApplication().getResources().getDrawable(R.mipmap.code53);
            }
            drawableId = drawableIds[i];
        } catch (Exception e) {
            return App.getApplication().getResources().getDrawable(R.mipmap.unkonw);
        }
        return App.getApplication().getResources().getDrawable(drawableId);
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

    public WeatherIdBeanX getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(WeatherIdBeanX weather_id) {
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

    public static class WeatherIdBeanX extends BaseObservable {
        /**
         * fa : code01
         * fb : code02
         */

        private String fa;
        private String fb;

        public String getFa() {
            return fa;
        }

        public void setFa(String fa) {
            this.fa = fa;
        }

        public String getFb() {
            return fb;
        }

        public void setFb(String fb) {
            this.fb = fb;
        }
    }
}
