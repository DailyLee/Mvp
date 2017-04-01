package com.demo.wondersdaili.mvp.base;

import com.demo.wondersdaili.mvp.widget.EmptyLayout;

/**
 * Created by daili on 2017/4/1.
 */

public interface BaseRefreshView extends BaseView {
    /**
     * 显示加载动画
     */
    void showLoading(boolean isRefresh);

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示网络错误
     */
    void showNetError(EmptyLayout.OnRetryListener onRetryListener);

    /**
     * 显示无数据
     */
    void showNoData();


    /**
     * 完成刷新, 新增控制刷新
     */
    void finishRefresh();
}
