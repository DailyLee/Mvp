package com.demo.wondersdaili.mvp.view.weather;

import com.demo.wondersdaili.mvp.model.weather.WeatherBean;
import com.demo.wondersdaili.mvp.databinding.RlFutureItemBinding;

import java.util.List;

/**
 * Created by daili on 2017/3/15.
 */

 class FutureWeatherAdapter extends CommonAdapter {
     FutureWeatherAdapter(List data, int layoutId) {
        super(data, layoutId);

    }

    @Override
    public void onBindViewHolder(CommonAdapter.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ((RlFutureItemBinding)holder.getViewDataBinding()).setResultFuture((WeatherBean.ResultBean.FutureBean) mData.get(position));
    }
}
