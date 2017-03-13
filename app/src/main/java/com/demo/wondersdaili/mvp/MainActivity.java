package com.demo.wondersdaili.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.wondersdaili.mvp.Dagger2.AppComponent;
import com.demo.wondersdaili.mvp.Api.GsonBean;
import com.demo.wondersdaili.mvp.Persenter.WeatherInteractor;
import com.demo.wondersdaili.rrd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText mEditText;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.button2)
    Button mButton2;
    private String mCity;
    private AppComponent mComponent;
    private WeatherInteractor mWeatherInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //获取dagger2注入对象
        mComponent = ((App) getApplication()).getComponent();
        mWeatherInteractor = mComponent.getWeatherInteractor();
        //注册
        mWeatherInteractor.register(this);
    }

    @OnClick({R.id.button, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                mCity = mEditText.getText().toString();
                //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
                mWeatherInteractor.queryWeather("30c5ae51f0cfec2a5132dc338195946d",mCity);
                break;
            case R.id.button2:
                mEditText.setText("");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeatherInteractor.unRegister();
    }

    public void loadWeatherData(GsonBean gsonBean) {
        GsonBean.ResultBean.TodayBean today = gsonBean.getResult().getToday();
        mTextView.setText(today.getCity()+"今天:"+today.getTemperature()+today.getWeather());

    }
}
