package com.demo.wondersdaili.mvp.module.weather;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.R;
import com.demo.wondersdaili.mvp.dagger2.DaggerFragmentComponent;
import com.demo.wondersdaili.mvp.dagger2.FragmentComponent;
import com.demo.wondersdaili.mvp.dagger2.FragmentModules;
import com.demo.wondersdaili.mvp.databinding.FragmentWeatherFutureBinding;
import com.demo.wondersdaili.mvp.event.CityChangeEvent;
import com.demo.wondersdaili.mvp.model.bean.FutureBean;
import com.demo.wondersdaili.mvp.model.bean.WeatherBean;
import com.demo.wondersdaili.mvp.module.base.LazyWeatherFragment;
import com.demo.wondersdaili.mvp.persenter.weather.WeatherPersenter;
import com.demo.wondersdaili.mvp.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by daili on 2017/3/22.
 */
public class FutureWeatherWeatherFragment extends LazyWeatherFragment {
    @Inject
    WeatherPersenter mWeatherPersenter;
    private CommonAdapter mAdapter;
    private List<FutureBean> mFutures = new ArrayList<>();
    private String mCity;
    private RecyclerView mRvFuture;

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
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(CityChangeEvent event) {
        //刷新数据
        updateData(false);
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
        List<FutureBean> future = resultBean.getFuture();
        mFutures.clear();
        mFutures.addAll(future);
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter = new FutureWeatherAdapter(mFutures, R.layout.rl_future_item);
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

    public void queryWeather(boolean isRefreshing, String string) {
        mCity = string;
        if (mWeatherPersenter != null){
            mWeatherPersenter.queryWeather(mCity, isRefreshing);
        }
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
