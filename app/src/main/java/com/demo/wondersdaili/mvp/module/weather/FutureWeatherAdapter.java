package com.demo.wondersdaili.mvp.module.weather;

import com.demo.wondersdaili.mvp.databinding.RlFutureItemBinding;
import com.demo.wondersdaili.mvp.model.bean.FutureBean;

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
        ((RlFutureItemBinding)holder.getViewDataBinding()).setResultFuture((FutureBean) mData.get(position));
    }
}
