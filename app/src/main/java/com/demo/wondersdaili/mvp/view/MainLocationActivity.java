package com.demo.wondersdaili.mvp.view;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
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
import com.demo.wondersdaili.mvp.R;
import com.demo.wondersdaili.mvp.base.BaseLocationActivity;
import com.demo.wondersdaili.mvp.base.BaseWeatherFragment;
import com.demo.wondersdaili.mvp.utils.ToastUtils;
import com.demo.wondersdaili.mvp.utils.UIUtils;

public class MainLocationActivity extends BaseLocationActivity implements SearchView.OnQueryTextListener {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private SearchView mSearchView;
    private long exitTime = 0;
    private TabPagerAdapter mAdapter;
    private BaseWeatherFragment[] mWeatherFragments  = new BaseWeatherFragment[2];


    @Override
    protected void initViews() {
        setContentView(R.layout.app_bar_main);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(App.getCity());
        setSupportActionBar(mToolbar);
        mAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mAdapter.setTabTitles(new String[]{"今日天气", "未来七天"});
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void initData() {
        mLocationPersenter.queryLateKnownLocation();
    }

    /***
     * 定位结束之后调用
     * @param location
     */
    @Override
    public void loadLateKnownLocation(BDLocation location) {
        super.loadLateKnownLocation(location);
        mToolbar.setTitle(App.getCity());

        mWeatherFragments[0] = TodayWeatherWeatherFragment.newInstance("1");
        mWeatherFragments[1] = FutureWeatherWeatherFragment.newInstance("2");
        mAdapter.setItems(mWeatherFragments);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void loadLateKnownLocationError(BDLocation location) {
        super.loadLateKnownLocationError(location);
        UIUtils.showReTryView(this, "点击重试", false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocationPersenter.queryLateKnownLocation();
                UIUtils.hideReTryView(MainLocationActivity.this);
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
        if (mWeatherFragments[0] == null || mWeatherFragments[1] == null){
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
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
