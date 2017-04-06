package com.demo.wondersdaili.mvp.view.weather;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.Constants;
import com.demo.wondersdaili.mvp.R;
import com.demo.wondersdaili.mvp.dagger2.DaggerFragmentComponent;
import com.demo.wondersdaili.mvp.dagger2.FragmentComponent;
import com.demo.wondersdaili.mvp.dagger2.FragmentModules;
import com.demo.wondersdaili.mvp.databinding.FragmentWeatherFutureBinding;
import com.demo.wondersdaili.mvp.model.weather.WeatherBean;
import com.demo.wondersdaili.mvp.persenter.weather.WeatherPersenter;
import com.demo.wondersdaili.mvp.utils.ToastUtils;
import com.demo.wondersdaili.mvp.view.base.BaseWeatherFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by daili on 2017/3/22.
 */

public class FutureWeatherWeatherFragment extends BaseWeatherFragment {
    @Inject
    WeatherPersenter mWeatherPersenter;
    private CommonAdapter mAdapter;
    private List<WeatherBean.ResultBean.FutureBean> mFutures = new ArrayList<>();
    private String mCity;
    private RecyclerView mRvFuture;

    public static FutureWeatherWeatherFragment newInstance(String type) {
        FutureWeatherWeatherFragment fragment = new FutureWeatherWeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_weather_future;
    }

    @Override
    protected void initViews() {
        mRvFuture = ((FragmentWeatherFutureBinding)mInflate).rvFuture;
        mRvFuture.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    protected void initInjector() {
        FragmentComponent component = DaggerFragmentComponent.builder().fragmentModules(new FragmentModules(mActivity))
                .build();
        component.inject(this);
        //注册
        mWeatherPersenter.register(this);
    }

    @Override
    protected void updateData(boolean b) {
        mCity = App.getCity();
        queryWeather(b, mCity);
    }


    /***
     * 获取数据之后调用此方法
     * @param resultBean
     */
    @Override
    public void loadWeatherData(WeatherBean.ResultBean resultBean) {
        List<WeatherBean.ResultBean.FutureBean> future = resultBean.getFuture();
        mFutures.clear();
        mFutures.addAll(future);
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter = new WeatherAdapter(mFutures, R.layout.rl_item);
            mRvFuture.setAdapter(mAdapter);
        }
    }


    /***
     * 获取数据失败之后调用此方法
     * @param weatherBean
     */
    @Override
    public void loadErrorData(WeatherBean weatherBean) {
        if (weatherBean != null){
            ToastUtils.showToast(getContext(), weatherBean.getReason());
        }
    }


    public void queryWeatherForResult(boolean isRefreshing, String string) {
        queryWeather(isRefreshing, string);
    }

    public void queryWeather(boolean isRefreshing, String string) {
        mCity = string;
        if (mWeatherPersenter != null)
        mWeatherPersenter.queryWeather(2, Constants.UrlKey, mCity, isRefreshing);
    }

    @Override
    public void onRefresh() {
        queryWeather(true, mCity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWeatherPersenter.unRegister();
    }
}
