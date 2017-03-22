package com.demo.wondersdaili.mvp.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.wondersdaili.mvp.Api.ConstantApi;
import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.Dagger2.AppComponent;
import com.demo.wondersdaili.mvp.GsonBean;
import com.demo.wondersdaili.mvp.Persenter.WeatherInteractor;
import com.demo.wondersdaili.mvp.R;
import com.demo.wondersdaili.mvp.Utils.ToastUtils;
import com.demo.wondersdaili.mvp.databinding.FragmentWeatherTodayBinding;

import java.util.ArrayList;
import java.util.List;

import static android.databinding.DataBindingUtil.inflate;
import static com.demo.wondersdaili.mvp.R.id.swipe;

/**
 * Created by daili on 2017/3/22.
 */

public class WeatherFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private AppComponent mComponent;
    private WeatherInteractor mWeatherInteractor;
    private CommonAdapter mAdapter;
    private GsonBean mResultBean = new GsonBean();
    private List<GsonBean.ResultBean.FutureBean> mFutures = new ArrayList<>();
    private RecyclerView mRvFuture;
    private String mType;
    private String mCity;
    private SwipeRefreshLayout mSwipe;

    public interface queryResultListener {
        void succeed();
    }

    private queryResultListener mListener;

    public static WeatherFragment newInstance(String type) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取对象
        mComponent = App.getApplication().getComponent();
        mWeatherInteractor = mComponent.getWeatherInteractor();
        //注册
        mWeatherInteractor.register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mType = getArguments().getString("type");
        mCity = App.getCity();
        View view;
        if ("1".equals(mType)) {
            FragmentWeatherTodayBinding inflate = inflate(inflater, R.layout.fragment_weather_today, container, false);
            mSwipe = inflate.swipe;
            mSwipe.setOnRefreshListener(this);
            inflate.setResult(mResultBean);
            view = inflate.getRoot();
        } else {
            view = inflater.inflate(R.layout.fragment_weather_future, container, false);
            mSwipe = (SwipeRefreshLayout) view.findViewById(swipe);
            mRvFuture = (RecyclerView) view.findViewById(R.id.rv_future);
            mRvFuture.setLayoutManager(new LinearLayoutManager(getContext()));
            mSwipe.setOnRefreshListener(this);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queryWeather(false, mCity);
    }

    /***
     * 获取数据之后调用此方法
     * @param resultBean
     */
    public void loadWeatherData(GsonBean.ResultBean resultBean) {
        App.setCity(resultBean.getToday().getCity());
        mResultBean.setResult(resultBean);
        mSwipe.setRefreshing(false);
        if ("2".equals(mType)) {
            loadFutureData(mResultBean);
        } else {
            if (mListener != null) {
                mListener.succeed();
            }
        }
    }


    /***
     * 获取数据失败之后调用此方法
     * @param gsonBean
     */
    public void loadErrorData(GsonBean gsonBean) {
        mSwipe.setRefreshing(false);
        if (gsonBean != null){
            ToastUtils.showToast(getContext(),gsonBean.getReason());
        }else {
            ToastUtils.showToast(getContext(),"无法连接网络");
        }
    }

    private void loadFutureData(GsonBean resultBean) {
        List<GsonBean.ResultBean.FutureBean> future = resultBean.getResult().getFuture();
        mFutures.clear();
        mFutures.addAll(future);
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter = new WeatherAdapter(mFutures, R.layout.rl_item);
            mRvFuture.setAdapter(mAdapter);
        }
    }

    public void queryWeatherForResult(boolean isRefreshing, String string, queryResultListener listener) {
        mListener = listener;
        queryWeather(isRefreshing, string);
    }

    public void queryWeather(boolean isRefreshing, String string) {
        mCity = string;
        if (mWeatherInteractor != null)
        mWeatherInteractor.queryWeather(2, ConstantApi.UrlKey, mCity, isRefreshing);
    }

    @Override
    public void onRefresh() {
        ToastUtils.showToast(getContext(),"正在刷新");
        queryWeather(true, mCity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWeatherInteractor.unRegister();
    }
}
