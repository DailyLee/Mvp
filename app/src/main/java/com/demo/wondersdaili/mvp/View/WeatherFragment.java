package com.demo.wondersdaili.mvp.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.Dagger2.AppComponent;
import com.demo.wondersdaili.mvp.GsonBean;
import com.demo.wondersdaili.mvp.Persenter.WeatherInteractor;
import com.demo.wondersdaili.mvp.R;
import com.demo.wondersdaili.mvp.databinding.FragmentWeatherTodayBinding;

import java.util.ArrayList;
import java.util.List;

import static android.databinding.DataBindingUtil.inflate;
import static com.demo.wondersdaili.mvp.R.id.swipe;

/**
 * Created by daili on 2017/3/code16.
 */

public class WeatherFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private AppComponent mComponent;
    private WeatherInteractor mWeatherInteractor;
    private CommonAdapter mAdapter;
    private FragmentWeatherTodayBinding mInflate1;
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
        if ("1".equals(mType)) {
            mInflate1 = inflate(inflater, R.layout.fragment_weather_today, container, false);
            mSwipe = mInflate1.swipe;
            mSwipe.setOnRefreshListener(this);
            mInflate1.setResult(mResultBean);
            return mInflate1.getRoot();
        } else {
            View view = inflater.inflate(R.layout.fragment_weather_future, container, false);
            mSwipe = (SwipeRefreshLayout) view.findViewById(swipe);
            mRvFuture = (RecyclerView) view.findViewById(R.id.rv_future);
            mRvFuture.setLayoutManager(new LinearLayoutManager(getContext()));
            mSwipe.setOnRefreshListener(this);
            return view;
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queryWeather(false, mCity);
    }

    public void queryWeatherForResult(boolean isRefreshing, String string, queryResultListener listener) {
        mListener = listener;
        queryWeather(isRefreshing, string);
    }

    public void queryWeather(boolean isRefreshing, String string) {
        mCity = string;
        mWeatherInteractor.queryWeather(2, "30c5ae51f0cfec2a5132dc338195946d", mCity, isRefreshing);
    }

    /***
     * 获取数据之后调用此方法
     * @param gsonBean
     */
    public void loadWeatherData(GsonBean.ResultBean gsonBean) {
        App.setCity(gsonBean.getToday().getCity());
        mResultBean.setResult(gsonBean);
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
     * @param e
     */
    public void loadErrorData(Throwable e) {
        mSwipe.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getContext(), "正在刷新", Toast.LENGTH_SHORT).show();
        queryWeather(true, mCity);
    }
}
