package com.demo.wondersdaili.mvp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.demo.wondersdaili.mvp.Dagger2.AppComponent;
import com.demo.wondersdaili.mvp.Persenter.WeatherInteractor;
import com.demo.wondersdaili.mvp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AppComponent mComponent;
    private WeatherInteractor mWeatherInteractor;
    private ActivityMainBinding mBinding;
    private GsonBean.ResultBean mResultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.button.setOnClickListener(this);
        mBinding.button2.setOnClickListener(this);
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
        mResultBean.setToday(today);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                mWeatherInteractor.queryWeather("30c5ae51f0cfec2a5132dc338195946d",mBinding.editText.getText().toString());
                break;
            case R.id.button2:
                mBinding.editText.setText("");
                break;
            default:
                break;
        }
    }
}
