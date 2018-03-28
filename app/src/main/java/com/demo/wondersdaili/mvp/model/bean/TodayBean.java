package com.demo.wondersdaili.mvp.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.BR;
import com.demo.wondersdaili.mvp.R;

import static com.demo.wondersdaili.mvp.utils.CommonUtil.getImageFuture;

/**
 * Created by daili on 2017/7/24.
 */
public class TodayBean extends BaseObservable {
    /**
     * temperature : 6℃~code14℃
     * weather : 多云转阴
     * weather_id : {"fa":"code01","fb":"code02"}
     * wind : 东风3-4 级
     * week : 星期三
     * city : 苏州
     * date_y : 2017年03月15日
     * dressing_index : 较冷
     * dressing_advice : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
     * uv_index : 最弱
     * comfort_index :
     * wash_index : 较适宜
     * travel_index : 较不宜
     * exercise_index : 较不宜
     * drying_index :
     */

    private String temperature;
    private String weather;
    private WeatherIdBean weather_id;
    private String wind;
    private String week;
    private String city;
    private String date_y;
    private String dressing_index;
    private String dressing_advice;
    private String uv_index;
    private String comfort_index;
    private String wash_index;
    private String travel_index;
    private String exercise_index;
    private String drying_index;

    @Bindable
    public Drawable getImageTodayFa() {
        return getImageFuture(getWeather_id().getFa());
    }

    @Bindable
    public Drawable getImageTodayFb() {
        return getImageFuture(getWeather_id().getFb());
    }

    @Bindable
    public SpannableString getRealToday() {
        String s = date_y + "\n" + "\n" +
                "紫外线强度: " + uv_index + "\t" + "\t" + " ಠ_ಠ" + "\n" + "\n" +
                "温度指数: " + dressing_index + "\t" + "\t" + "(oﾟωﾟo)" + "\n" + "\n" +
                "洗衣指数: " + wash_index + "\t" + "\t" + "(=￣ω￣=)" + "\n" + "\n" +
                "锻炼指数: " + exercise_index + "\t" + "\t" + "╮(￣▽￣)╭" + "\n" + "\n" +
                "出游指数: " + travel_index + "\t" + "\t" + "(｡・`ω´･)" + "\n" + "\n" +
                "穿衣指数: " + dressing_advice + "\t" + "\t" + "(＞﹏＜)";
        SpannableString string = new SpannableString(s);
        StyleSpan styleSpan = new StyleSpan(Typeface.NORMAL);
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(60);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(App.getApplication().getResources().getColor(R.color.colorAccent));
        string.setSpan(foregroundColorSpan, 0, 11, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        string.setSpan(styleSpan, 0, 11, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        string.setSpan(sizeSpan, 0, 11, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return string;
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

    @Bindable
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        notifyPropertyChanged(BR.city);
    }

    public String getDate_y() {
        return date_y;
    }

    public void setDate_y(String date_y) {
        this.date_y = date_y;
    }

    public String getDressing_index() {
        return dressing_index;
    }

    public void setDressing_index(String dressing_index) {
        this.dressing_index = dressing_index;
    }

    public String getDressing_advice() {
        return dressing_advice;
    }

    public void setDressing_advice(String dressing_advice) {
        this.dressing_advice = dressing_advice;
    }

    public String getUv_index() {
        return uv_index;
    }

    public void setUv_index(String uv_index) {
        this.uv_index = uv_index;
    }

    public String getComfort_index() {
        return comfort_index;
    }

    public void setComfort_index(String comfort_index) {
        this.comfort_index = comfort_index;
    }

    public String getWash_index() {
        return wash_index;
    }

    public void setWash_index(String wash_index) {
        this.wash_index = wash_index;
    }

    public String getTravel_index() {
        return travel_index;
    }

    public void setTravel_index(String travel_index) {
        this.travel_index = travel_index;
    }

    public String getExercise_index() {
        return exercise_index;
    }

    public void setExercise_index(String exercise_index) {
        this.exercise_index = exercise_index;
    }

    public String getDrying_index() {
        return drying_index;
    }

    public void setDrying_index(String drying_index) {
        this.drying_index = drying_index;
    }

}
