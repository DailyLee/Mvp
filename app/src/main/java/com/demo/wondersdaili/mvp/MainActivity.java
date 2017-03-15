package com.demo.wondersdaili.mvp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.demo.wondersdaili.mvp.Dagger2.AppComponent;
import com.demo.wondersdaili.mvp.Persenter.WeatherInteractor;
import com.demo.wondersdaili.mvp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AppComponent mComponent;
    private WeatherInteractor mWeatherInteractor;
    private ActivityMainBinding mBinding;
    private GsonBean.ResultBean mResultBean;
    private CommonAdapter mAdapter;
    private List<GsonBean.ResultBean.FutureBean> mFutures  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.button.setOnClickListener(this);
        mBinding.rlFuture.setLayoutManager(new LinearLayoutManager(this));
        mResultBean = new GsonBean.ResultBean();
        mBinding.setResult(mResultBean);
        //获取dagger2注入对象
        mComponent = ((App) getApplication()).getComponent();
        mWeatherInteractor = mComponent.getWeatherInteractor();
        //注册
        mWeatherInteractor.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeatherInteractor.unRegister();
    }

    public void loadWeatherData(GsonBean gsonBean) {
        GsonBean.ResultBean.TodayBean today = gsonBean.getResult().getToday();
        List<GsonBean.ResultBean.FutureBean> future = gsonBean.getResult().getFuture();
        mFutures.clear();
        for (GsonBean.ResultBean.FutureBean bean: future) {
            mFutures.add(bean);
        }
        //result数据内容变化,自动刷新Ui
        mResultBean.setToday(today);
        if(mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }else {
            mAdapter = new WeatherAdapter(mFutures, R.layout.rl_item);
            mBinding.rlFuture.setAdapter(mAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                String string = mBinding.editText.getText().toString();
                mWeatherInteractor.queryWeather(2,"30c5ae51f0cfec2a5132dc338195946d", string);
                break;
            default:
                break;
        }
    }
}
