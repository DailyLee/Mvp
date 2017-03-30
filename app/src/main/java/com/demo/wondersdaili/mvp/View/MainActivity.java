package com.demo.wondersdaili.mvp.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.baidu.location.BDLocation;
import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.BaseActivity;
import com.demo.wondersdaili.mvp.R;
import com.demo.wondersdaili.mvp.Utils.ToastUtils;
import com.demo.wondersdaili.mvp.Utils.UIUtils;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Fragment[] mWeatherFragments;
    private Toolbar mToolbar;
    private SearchView mSearchView;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    /***
     * 定位结束之后调用
     * @param location
     */
    @Override
    public void loadLateKnownLocation(BDLocation location) {
        super.loadLateKnownLocation(location);
        mToolbar.setTitle(App.getCity());
        setSupportActionBar(mToolbar);
        mWeatherFragments = new WeatherFragment[2];
        mWeatherFragments[0] = WeatherFragment.newInstance("1");
        mWeatherFragments[1] = WeatherFragment.newInstance("2");
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), mWeatherFragments);
        adapter.setTabTitles(new String[]{"今日天气", "未来七天"});
        mViewPager.setAdapter(adapter);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void loadLateKnownLocationError(BDLocation location) {
        super.loadLateKnownLocationError(location);
        UIUtils.showReTryView(this, "点击重试", false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocationPersenter.queryLateKnownLocation();
                UIUtils.hideReTryView(MainActivity.this);
            }
        });
    }

    @Override
    public void loadLocation(BDLocation location) {
        super.loadLocation(location);
        String city = location.getCity();
        city = city.substring(0, city.length() - 1);
        if (!city.equals(App.getCity())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("是否切换到当前城市:" + city);
            builder.setTitle("位置提示");
            final String finalCity = city;
            builder.setPositiveButton("切换", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    App.setCity(finalCity);
                    mToolbar.setTitle(App.getCity());
                    onQueryTextSubmit(App.getCity());
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();

        } else {
            ToastUtils.showToast(this, "当前城市:" + city);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        //搜索框和定位按钮
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setOnQueryTextListener(this);
        MenuItem location = menu.findItem(R.id.action_location);
        location.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mLocationPersenter.queryLocation();
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        WeatherFragment weatherFragment1 = (WeatherFragment) mWeatherFragments[0];
        WeatherFragment weatherFragment2 = (WeatherFragment) mWeatherFragments[1];
        weatherFragment1.queryWeatherForResult(false, query, new WeatherFragment.queryResultListener() {
            @Override
            public void succeed() {
                mToolbar.setTitle(App.getCity());
            }
        });
        weatherFragment2.queryWeather(false, query);
        mSearchView.clearFocus();
        mSearchView.onActionViewCollapsed();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                ToastUtils.showToast(getApplicationContext(), "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
