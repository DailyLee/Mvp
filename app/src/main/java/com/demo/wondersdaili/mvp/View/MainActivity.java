package com.demo.wondersdaili.mvp.View;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.demo.wondersdaili.mvp.BaseActivity;
import com.demo.wondersdaili.mvp.GsonBean;
import com.demo.wondersdaili.mvp.R;

public class MainActivity extends BaseActivity<GsonBean> implements SearchView.OnQueryTextListener{
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Fragment[] mWeatherFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mWeatherFragments = new WeatherFragment[2];
        mWeatherFragments[0] = WeatherFragment.newInstance("1");
        mWeatherFragments[1] = WeatherFragment.newInstance("2");
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), mWeatherFragments);
        adapter.setTabTitles(new String[]{"今日天气","未来七天"});
        mViewPager.setAdapter(adapter);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu,menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
        searchView.setSearchableInfo(searchableInfo);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        WeatherFragment weatherFragment1 = (WeatherFragment) mWeatherFragments[0];
        WeatherFragment weatherFragment2 = (WeatherFragment) mWeatherFragments[1];
        weatherFragment1.queryWeather(false,query);
        weatherFragment2.queryWeather(false,query);
        Toast.makeText(this,query,Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


}
