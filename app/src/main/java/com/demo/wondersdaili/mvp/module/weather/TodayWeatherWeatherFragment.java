package com.demo.wondersdaili.mvp.module.weather;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.Constants;
import com.demo.wondersdaili.mvp.R;
import com.demo.wondersdaili.mvp.dagger2.DaggerFragmentComponent;
import com.demo.wondersdaili.mvp.dagger2.FragmentComponent;
import com.demo.wondersdaili.mvp.dagger2.FragmentModules;
import com.demo.wondersdaili.mvp.databinding.FragmentWeatherTodayBinding;
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
public class TodayWeatherWeatherFragment extends LazyWeatherFragment {
    @Inject
    WeatherPersenter mWeatherPersenter;
    private WeatherBean mResultBean = new WeatherBean();
    private String mCity;
    private RecyclerView mRecy;
    private List<FutureBean> mFutures = new ArrayList<>();
    private TodayWeatherAdapter mAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_weather_today;
    }

    @Override
    protected void initViews() {
        ((FragmentWeatherTodayBinding) mInflate).setResult(mResultBean);
        mRecy = ((FragmentWeatherTodayBinding) mInflate).recy;
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecy.setLayoutManager(layout);
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
    protected void updateData(boolean b) {
        mCity = App.getCity();
        queryWeather(b, mCity);
    }

    @Override
    public void onRefresh() {
        queryWeather(true, mCity);
    }

    /***
     * 获取数据之后调用此方法
     * @param resultBean
     */
    @Override
    public void loadWeatherData(WeatherBean.ResultBean resultBean) {
        App.setCity(resultBean.getToday().getCity());
        mResultBean.setResult(resultBean);
        List<FutureBean> future = resultBean.getFuture();
        mFutures.clear();
        mFutures.addAll(future);
        if (mAdapter != null) {
            //更新天气温度折线图数据
            mAdapter.setData(mFutures);
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter = new TodayWeatherAdapter(mFutures, R.layout.rl_today_item);
            mRecy.setAdapter(mAdapter);
        }
    }

    /***
     * 获取数据失败之后调用此方法
     * @param weatherBean
     */
    @Override
    public void loadErrorData(WeatherBean weatherBean) {
        if (weatherBean != null) {
            ToastUtils.showToast(getContext(), weatherBean.getReason());
        }
    }

    public void queryWeather(boolean isRefreshing, String city) {
        mCity = city;
        App.setCity(mCity);
        if (mWeatherPersenter != null) {
            mWeatherPersenter.queryWeather(WeatherPersenter.FORMAT_MODE_TWO, Constants.UrlKey, city, isRefreshing);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWeatherPersenter.unRegister();
    }
}