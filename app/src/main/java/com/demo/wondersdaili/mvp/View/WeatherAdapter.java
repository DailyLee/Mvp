package com.demo.wondersdaili.mvp.View;

import com.demo.wondersdaili.mvp.Api.WeatherBean;
import com.demo.wondersdaili.mvp.databinding.RlItemBinding;

import java.util.List;

/**
 * Created by daili on 2017/3/code15.
 */

 class WeatherAdapter extends CommonAdapter {
     WeatherAdapter(List data, int layoutId) {
        super(data, layoutId);

    }

    @Override
    public void onBindViewHolder(CommonAdapter.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ((RlItemBinding)holder.getViewDataBinding()).setResultFuture((WeatherBean.ResultBean.FutureBean) mData.get(position));
    }
}
