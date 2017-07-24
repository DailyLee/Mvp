package com.demo.wondersdaili.mvp.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.BR;
import com.demo.wondersdaili.mvp.R;

/**
 * Created by daili on 2017/7/24.
 */
public class SkBean extends BaseObservable {
    /**
     * temp : code11
     * wind_direction : 东北风
     * wind_strength : 2级
     * humidity : 28%
     * time : code11:49
     */

    private String temp;
    private String wind_direction;
    private String wind_strength;
    private String humidity;
    private String time;

    @Bindable
    public SpannableString getRealSk() {
        String s = wind_direction + wind_strength + "  " + "湿度:" + humidity + "\n" +
                "更新时间:" + time;
        SpannableString string = new SpannableString(s);
        StyleSpan styleSpan = new StyleSpan(Typeface.NORMAL);
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(50);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(App.getApplication().getResources().getColor(R.color.colorAccent));
        string.setSpan(foregroundColorSpan, string.length() - 10, string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        string.setSpan(styleSpan, string.length() - 10, string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        string.setSpan(sizeSpan, string.length() - 10, string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return string;
    }

    @Bindable
    public String getRealTemp() {
        return temp + "℃";
    }

    @Bindable
    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
        notifyPropertyChanged(BR.temp);
    }

    @Bindable
    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
        notifyPropertyChanged(BR.wind_direction);
    }

    @Bindable
    public String getWind_strength() {
        return wind_strength;
    }

    public void setWind_strength(String wind_strength) {
        this.wind_strength = wind_strength;
        notifyPropertyChanged(BR.wind_strength);
    }

    @Bindable
    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
        notifyPropertyChanged(BR.humidity);
    }

    @Bindable
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
    }
}
