package com.demo.wondersdaili.mvp.module.weather;

import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;

import com.demo.wondersdaili.mvp.databinding.RlTodayItemBinding;
import com.demo.wondersdaili.mvp.model.bean.FutureBean;
import com.demo.wondersdaili.mvp.utils.CommonUtil;
import com.demo.wondersdaili.mvp.widget.ShakeMaps;

import java.util.List;

/**
 * Created by daili on 2017/5/18.
 */
class ShakeMapWeatherAdapter extends CommonAdapter<FutureBean> {
    private int[] highTemp = new int[mData.size()];
    private int[] lowTemp = new int[mData.size()];
    private int mMax;
    private int mMin;

    ShakeMapWeatherAdapter(List<FutureBean> data, @LayoutRes int layoutId) {
        super(data, layoutId);
        manageData(data);
    }

    private void manageData(List data) {
        for (int i = 0; i < data.size(); i++) {
            String temperature = (mData.get(i)).getTemperature();
            highTemp[i] = Integer.parseInt(temperature.split("℃")[1].substring(1));
            lowTemp[i] = Integer.parseInt(temperature.split("℃")[0]);
        }
        mMax = CommonUtil.getMax(highTemp);
        mMin = CommonUtil.getMin(lowTemp);
    }

    void setData(List data) {
        manageData(data);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        RlTodayItemBinding viewDataBinding = (RlTodayItemBinding) holder.getViewDataBinding();
        viewDataBinding.setResultFuture(mData.get(position));
        ShakeMaps shake = viewDataBinding.shake;

        float[] highTemp3 = new float[3];
        float[] lowTemp3 = new float[3];
        highTemp3[1] = highTemp[position];
        lowTemp3[1] = lowTemp[position];

        if (position != 0) {
            highTemp3[0] = highTemp[position - 1];
            lowTemp3[0] = lowTemp[position - 1];
        } else {
            highTemp3[0] = 100;
            lowTemp3[0] = -100;
        }

        if (position != mData.size() - 1) {
            highTemp3[2] = highTemp[position + 1];
            lowTemp3[2] = lowTemp[position + 1];
        } else {
            highTemp3[2] = 100;
            lowTemp3[2] = -100;
        }
        Drawable imageFutureFa = (mData.get(position)).getImageFutureFa();
        Drawable imageFutureFb = (mData.get(position)).getImageFutureFb();
        shake.setData(highTemp3, lowTemp3, mMax, mMin);
        shake.setBitmap(imageFutureFa, imageFutureFb);
    }
}