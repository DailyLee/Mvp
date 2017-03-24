package com.demo.wondersdaili.mvp.Api;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.BR;
import com.demo.wondersdaili.mvp.R;

import java.util.List;


/**
 * Created by daili on 2017/3/9.
 */

public class WeatherBean extends BaseObservable  {


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

        public static class SkBean extends BaseObservable {
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
            public String getRealSk() {
                return wind_direction+wind_strength+"  "+ "湿度:"+humidity+"\n"+
                        "更新时间:"+time ;
            }

            @Bindable
            public String getRealTemp() {
                return temp+"℃";
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

        public static class TodayBean extends BaseObservable {
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
                }catch (Exception e){
                    return App.getApplication().getResources().getDrawable(R.mipmap.unkonw);
                }
                return  App.getApplication().getResources().getDrawable(drawableId);
            }

            @Bindable
            public Drawable getImageTodayFb() {
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
                }catch (Exception e){
                    return App.getApplication().getResources().getDrawable(R.mipmap.unkonw);
                }
                return  App.getApplication().getResources().getDrawable(drawableId);
            }

            @Bindable
            public String getRealToday() {
                return "身体温感: " +dressing_index+"\n"+
                        "穿衣建议 : " +dressing_advice;
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

            public static class WeatherIdBean extends BaseObservable{
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

        public static class FutureBean extends BaseObservable{
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
                }catch (Exception e){
                    return App.getApplication().getResources().getDrawable(R.mipmap.unkonw);
                }
                return  App.getApplication().getResources().getDrawable(drawableId);
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
                }catch (Exception e){
                    return App.getApplication().getResources().getDrawable(R.mipmap.unkonw);
                }
                return  App.getApplication().getResources().getDrawable(drawableId);
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

            public static class WeatherIdBeanX extends BaseObservable{
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
    }
}
