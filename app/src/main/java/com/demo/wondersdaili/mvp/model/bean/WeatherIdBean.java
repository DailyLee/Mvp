package com.demo.wondersdaili.mvp.model.bean;

import android.databinding.BaseObservable;

/**
 * Created by daili on 2017/7/31.
 */
public class WeatherIdBean extends BaseObservable {
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
