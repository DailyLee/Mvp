package com.demo.wondersdaili.mvp.module.weather;

import android.support.annotation.LayoutRes;

import com.demo.wondersdaili.mvp.databinding.RlFutureItemBinding;
import com.demo.wondersdaili.mvp.model.bean.FutureBean;

import java.util.List;

/**
 * Created by daili on 2017/3/15.
 */
class FutureWeatherAdapter extends CommonAdapter<FutureBean> {
    FutureWeatherAdapter(List<FutureBean> data, @LayoutRes int layoutId) {
        super(data, layoutId);
    }

    @Override
    public void onBindViewHolder(CommonAdapter.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ((RlFutureItemBinding) holder.getViewDataBinding()).setResultFuture(mData.get(position));
    }
}
