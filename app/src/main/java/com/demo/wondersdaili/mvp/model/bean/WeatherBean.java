package com.demo.wondersdaili.mvp.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.demo.wondersdaili.mvp.BR;

import java.util.List;


/**
 * Created by daili on 2017/3/9.
 */
public class WeatherBean extends BaseObservable {
    /**
     * resultcode : 200
     * reason : successed!
     * result : {"sk":{"temp":"code11","wind_direction":"东北风","wind_strength":"2级","humidity":"28%","time":"code11:49"},"today":{"temperature":"6℃~code14℃","weather":"多云转阴","weather_id":{"fa":"code01","fb":"code02"},"wind":"东风3-4 级","week":"星期三","city":"苏州","date_y":"2017年03月15日","dressing_index":"较冷","dressing_advice":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。","uv_index":"最弱","comfort_index":"","wash_index":"较适宜","travel_index":"较不宜","exercise_index":"较不宜","drying_index":""},"future":[{"temperature":"6℃~code14℃","weather":"多云转阴","weather_id":{"fa":"code01","fb":"code02"},"wind":"东风3-4 级","week":"星期三","date":"20170315"},{"temperature":"4℃~code11℃","weather":"阴转晴","weather_id":{"fa":"code02","fb":"code00"},"wind":"东风3-4 级","week":"星期四","date":"20170316"},{"temperature":"8℃~code14℃","weather":"多云转小雨","weather_id":{"fa":"code01","fb":"code07"},"wind":"东风3-4 级","week":"星期五","date":"20170317"},{"temperature":"8℃~13℃","weather":"阴","weather_id":{"fa":"code02","fb":"code02"},"wind":"东北风3-4 级","week":"星期六","date":"20170318"},{"temperature":"8℃~code16℃","weather":"阴转小雨","weather_id":{"fa":"code02","fb":"code07"},"wind":"东北风3-4 级","week":"星期日","date":"20170319"},{"temperature":"8℃~13℃","weather":"阴","weather_id":{"fa":"code02","fb":"code02"},"wind":"东北风3-4 级","week":"星期一","date":"20170320"},{"temperature":"8℃~13℃","weather":"阴","weather_id":{"fa":"code02","fb":"code02"},"wind":"东北风3-4 级","week":"星期二","date":"20170321"}]}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Bindable
    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
        notifyPropertyChanged(BR.result);
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean extends BaseObservable {
        /**
         * sk : {"temp":"code11","wind_direction":"东北风","wind_strength":"2级","humidity":"28%","time":"code11:49"}
         * today : {"temperature":"6℃~code14℃","weather":"多云转阴","weather_id":{"fa":"code01","fb":"code02"},"wind":"东风3-4 级","week":"星期三","city":"苏州","date_y":"2017年03月15日","dressing_index":"较冷","dressing_advice":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。","uv_index":"最弱","comfort_index":"","wash_index":"较适宜","travel_index":"较不宜","exercise_index":"较不宜","drying_index":""}
         * future : [{"temperature":"6℃~code14℃","weather":"多云转阴","weather_id":{"fa":"code01","fb":"code02"},"wind":"东风3-4 级","week":"星期三","date":"20170315"},{"temperature":"4℃~code11℃","weather":"阴转晴","weather_id":{"fa":"code02","fb":"code00"},"wind":"东风3-4 级","week":"星期四","date":"20170316"},{"temperature":"8℃~code14℃","weather":"多云转小雨","weather_id":{"fa":"code01","fb":"code07"},"wind":"东风3-4 级","week":"星期五","date":"20170317"},{"temperature":"8℃~13℃","weather":"阴","weather_id":{"fa":"code02","fb":"code02"},"wind":"东北风3-4 级","week":"星期六","date":"20170318"},{"temperature":"8℃~code16℃","weather":"阴转小雨","weather_id":{"fa":"code02","fb":"code07"},"wind":"东北风3-4 级","week":"星期日","date":"20170319"},{"temperature":"8℃~13℃","weather":"阴","weather_id":{"fa":"code02","fb":"code02"},"wind":"东北风3-4 级","week":"星期一","date":"20170320"},{"temperature":"8℃~13℃","weather":"阴","weather_id":{"fa":"code02","fb":"code02"},"wind":"东北风3-4 级","week":"星期二","date":"20170321"}]
         */

        private SkBean sk;
        private TodayBean today;
        private List<FutureBean> future;

        @Bindable
        public SkBean getSk() {
            return sk;
        }

        public void setSk(SkBean sk) {
            this.sk = sk;
            notifyPropertyChanged(BR.sk);
        }

        @Bindable
        public TodayBean getToday() {
            return today;
        }

        public void setToday(TodayBean today) {
            this.today = today;
            notifyPropertyChanged(BR.today);
        }

        @Bindable
        public List<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(List<FutureBean> future) {
            this.future = future;
            notifyPropertyChanged(BR.future);
        }

    }
}
