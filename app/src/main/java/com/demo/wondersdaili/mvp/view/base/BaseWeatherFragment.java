package com.demo.wondersdaili.mvp.view.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.wondersdaili.mvp.R;
import com.demo.wondersdaili.mvp.persenter.weather.WeatherContract;
import com.demo.wondersdaili.mvp.widget.EmptyLayout;

import static android.databinding.DataBindingUtil.inflate;

/**
 * Created by daili on 2017/4/1.
 */

public abstract class BaseWeatherFragment extends Fragment implements WeatherContract.View, SwipeRefreshLayout.OnRefreshListener {

    protected SwipeRefreshLayout mSwipeView;
    protected FragmentActivity mActivity;
    protected ViewDataBinding mInflate;
    private View mRootView;
    private boolean mIsMulti = false;
    private EmptyLayout mEmptyLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mInflate = inflate(inflater, attachLayoutRes(), container, false);
            mRootView = mInflate.getRoot();
            mSwipeView = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe);
            mEmptyLayout = (EmptyLayout) mRootView.findViewById(R.id.empty_layout);
            if (mSwipeView != null) {
                mSwipeView.setOnRefreshListener(this);
            }
            initInjector();
            initViews();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateData(false);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateData(false);
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    @Override
    public void onRefresh() {
        showLoading(true);
        updateData(true);
    }


    @Override
    public void showLoading(boolean isRefreshing) {
        if (isRefreshing) {
            if (mSwipeView != null) {
                mSwipeView.setEnabled(true);
                mSwipeView.setRefreshing(true);
            }
        } else {
            if (mEmptyLayout != null) {
                mSwipeView.setEnabled(false);
                mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
            }
        }

    }

    @Override
    public void showNoData() {
        if (mEmptyLayout != null) {
            mSwipeView.setEnabled(false);
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_DATA);
        }
    }

    @Override
    public void hideLoading() {
        if (mSwipeView != null) {
            mSwipeView.setRefreshing(false);
        }
    }

    @Override
    public void showNetError(final EmptyLayout.OnRetryListener onRetryListener) {
        if (mEmptyLayout != null) {
            mSwipeView.setEnabled(false);
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(onRetryListener);
        }
    }

    @Override
    public void finishRefresh() {
        mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_HIDE);
        mSwipeView.setEnabled(true);
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    protected abstract int attachLayoutRes();

    protected abstract void initViews();

    protected abstract void initInjector();

    protected abstract void updateData(boolean b);

}
