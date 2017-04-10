package com.demo.wondersdaili.mvp.view.location;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;

import com.baidu.location.BDLocation;
import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.R;
import com.demo.wondersdaili.mvp.utils.ToastUtils;
import com.demo.wondersdaili.mvp.view.base.BaseLocationActivity;
import com.demo.wondersdaili.mvp.view.base.BaseWeatherFragment;
import com.demo.wondersdaili.mvp.view.weather.FutureWeatherWeatherFragment;
import com.demo.wondersdaili.mvp.view.weather.TodayWeatherWeatherFragment;
import com.demo.wondersdaili.mvp.widget.YRotationAnimation;

public class MainLocationActivity extends BaseLocationActivity implements SearchView.OnQueryTextListener {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private SearchView mSearchView;
    private long exitTime = 0;
    private TabPagerAdapter mAdapter;
    private BaseWeatherFragment[] mWeatherFragments = new BaseWeatherFragment[2];
    private View mActionView;


    @Override
    protected void initViews() {
        setContentView(R.layout.app_bar_main);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(App.getCity());
        setSupportActionBar(mToolbar);
        //设置上次使用城市
        SharedPreferences sp = this.getSharedPreferences("CITY", MODE_PRIVATE);
        String city = sp.getString("CityName", "北京");
        App.setCity(city);
        mToolbar.setTitle(city);
        mAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mAdapter.setTabTitles(new String[]{"今日天气", "未来七天"});

    }

    @Override
    protected void initData() {
        mLocationPersenter.queryLateKnownLocation();
        mWeatherFragments[0] = TodayWeatherWeatherFragment.newInstance("1");
        mWeatherFragments[1] = FutureWeatherWeatherFragment.newInstance("2");
        mAdapter.setItems(mWeatherFragments);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    /***
     * 定位结束之后调用
     * @param location
     */
    @Override
    public void loadLocation(BDLocation location) {
        super.loadLocation(location);
        ClearLocationState(mActionView);
        SwitchCity(location);
    }


    @Override
    public void loadLocationError(BDLocation location) {
        super.loadLocationError(location);
        ClearLocationState(mActionView);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        //搜索框和定位按钮
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setOnQueryTextListener(this);
        final MenuItem location = menu.findItem(R.id.action_location);
        mActionView = location.getActionView();
        ShowAnimation(mActionView);
        mActionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(location);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_location) {
            Log.e("@@@@@@@@","checked");
            mLocationPersenter.queryLocation();
            View actionView = item.getActionView();
            ShowAnimation(actionView);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 开始定位动画
     * @param actionView
     */
    private void ShowAnimation(View actionView) {
        YRotationAnimation animation = new YRotationAnimation();
        animation.setRepeatCount(Animation.INFINITE);
        actionView.startAnimation(animation);
        actionView.setClickable(false);
        actionView.setSelected(true);
    }

    /**
     * 定位之后询问是否切换城市
     *
     * @param location
     */
    private void SwitchCity(BDLocation location) {
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

    /**
     * 清空定位图片状态
     */
    private void ClearLocationState(View actionView) {
        actionView.setClickable(true);
        actionView.setSelected(false);
        actionView.clearAnimation();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (mWeatherFragments[0] == null || mWeatherFragments[1] == null) {
            mWeatherFragments[0] = TodayWeatherWeatherFragment.newInstance("1");
            mWeatherFragments[1] = FutureWeatherWeatherFragment.newInstance("2");
        }

        TodayWeatherWeatherFragment todayWeatherFragment = (TodayWeatherWeatherFragment) mWeatherFragments[0];
        FutureWeatherWeatherFragment futureWeatherFragment = (FutureWeatherWeatherFragment) mWeatherFragments[1];

        todayWeatherFragment.queryWeatherForResult(false, query, new TodayWeatherWeatherFragment.queryResultListener() {
            @Override
            public void succeed() {
                mToolbar.setTitle(App.getCity());
            }
        });
        futureWeatherFragment.queryWeatherForResult(false, query);
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
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //保存最近一次定位城市
        String city = App.getCity();
        SharedPreferences sp = this.getSharedPreferences("CITY", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("CityName", city);
        editor.apply();
        System.exit(0);
    }
}
