package com.demo.wondersdaili.mvp;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.wondersdaili.mvp.databinding.RlItemBinding;

import java.util.List;

/**
 * Created by daili on 2017/3/15.
 */

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.ViewHolder> {
    private List mData;
    private int mLayoutId;

    public CommonAdapter(GsonBean.ResultBean data, int layoutId) {
        this.mData = data.getFuture();
        this.mLayoutId = layoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RlItemBinding binding = DataBindingUtil.inflate(inflater, mLayoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding.getRoot());
        viewHolder.setViewDataBinding(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getViewDataBinding().setResultFuture((GsonBean.ResultBean.FutureBean) mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private RlItemBinding mViewDataBinding;

        RlItemBinding getViewDataBinding() {
            return mViewDataBinding;
        }

        void setViewDataBinding(RlItemBinding viewDataBinding) {
            mViewDataBinding = viewDataBinding;
        }

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
