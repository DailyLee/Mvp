package com.demo.wondersdaili.mvp.view.location;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.demo.wondersdaili.mvp.App;
import com.demo.wondersdaili.mvp.R;
import com.demo.wondersdaili.mvp.utils.CommonUtil;
import com.demo.wondersdaili.mvp.utils.PrefUtil;
import com.demo.wondersdaili.mvp.utils.ToastUtils;
import com.demo.wondersdaili.mvp.view.base.BaseLocationActivity;
import com.demo.wondersdaili.mvp.view.base.LazyWeatherFragment;
import com.demo.wondersdaili.mvp.view.weather.FutureWeatherWeatherFragment;
import com.demo.wondersdaili.mvp.view.weather.TodayWeatherWeatherFragment;
import com.demo.wondersdaili.mvp.widget.YRotationAnimation;

import java.util.List;

public class MainLocationActivity extends BaseLocationActivity implements SearchView.OnQueryTextListener, View.OnClickListener, View.OnLongClickListener {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private SearchView mSearchView;
    private TabPagerAdapter mAdapter;
    private View mActionView;
    private FloatingActionButton mFloatingActionButton;
    private FrameLayout mViewCloud;
    private LinearLayout mRlCity;
    private LazyWeatherFragment[] mWeatherFragments = new LazyWeatherFragment[2];
    private AlertDialog.Builder mBuilder;
    private boolean fabOpened = false;
    private long exitTime = 0;
    private static final int CITY_NUM = 3;
    private List<String> mCityList;
    private EditText mEditText;


    @Override
    protected void initViews() {
        setContentView(R.layout.app_bar_main);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floating);
        mViewCloud = (FrameLayout) findViewById(R.id.cloud);
        mRlCity = (LinearLayout) findViewById(R.id.rl_city);
        mViewCloud.setOnClickListener(this);
        mFloatingActionButton.setOnClickListener(this);
        mToolbar.setTitle(App.getCity());
        setSupportActionBar(mToolbar);
        //设置上次使用城市
        String city = PrefUtil.getString(this, "CityName", "北京");
        App.setCity(city);
        mToolbar.setTitle(city);
        mAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mAdapter.setTabTitles(new String[]{"今日天气", "未来七天"});

    }

    @Override
    protected void initData() {
        mWeatherFragments[0] = TodayWeatherWeatherFragment.newInstance("1");
        mWeatherFragments[1] = FutureWeatherWeatherFragment.newInstance("2");
        mAdapter.setItems(mWeatherFragments);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        initFab();
    }

    private void initFab() {
        mCityList = PrefUtil.getListString(this, "city");
        mRlCity.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < mCityList.size(); i++) {
            TextView city = (TextView) LayoutInflater.from(this).inflate(R.layout.fab_city, null);
            city.setText(mCityList.get(i));
            city.setLayoutParams(layoutParams);
            city.setId(i);
            city.setOnClickListener(this);
            city.setOnLongClickListener(this);
            mRlCity.addView(city);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating:
                if (!fabOpened) {
                    openMenu(v);
                } else {
                    showCollectCityDialog();
                }
                break;
            case R.id.cloud:
                if (fabOpened)
                    closeMenu(mFloatingActionButton);
                break;
            case 0:
                ToastUtils.showToast(MainLocationActivity.this, mCityList.get(0));
                onQueryTextSubmit(mCityList.get(0));
                closeMenu(mFloatingActionButton);
                break;
            case 1:
                ToastUtils.showToast(MainLocationActivity.this, mCityList.get(1));
                onQueryTextSubmit(mCityList.get(1));
                closeMenu(mFloatingActionButton);
                break;
            case 2:
                ToastUtils.showToast(MainLocationActivity.this, mCityList.get(2));
                onQueryTextSubmit(mCityList.get(2));
                closeMenu(mFloatingActionButton);
                break;
            default:
                break;
        }
    }

    /**
     * 显示增加收藏对话框
     */
    private void showCollectCityDialog() {
        mEditText = new EditText(this);
        new AlertDialog.Builder(this).setTitle("添加收藏")
                .setMessage(null)
                .setView(mEditText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!isAddCitySuccess()) return;
                        dialog.dismiss();
                        initFab();
                        closeMenu(mFloatingActionButton);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        closeMenu(mFloatingActionButton);
                    }
                }).setCancelable(false).show();
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case 0:
                RemoveCityDialog(0);
                closeMenu(mFloatingActionButton);
                break;
            case 1:
                RemoveCityDialog(1);
                closeMenu(mFloatingActionButton);
                break;
            case 2:
                RemoveCityDialog(2);
                closeMenu(mFloatingActionButton);
                break;
            default:
                break;
        }
        return true;
    }

    private void RemoveCityDialog(final int index) {
        if (mBuilder == null)
            mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("取消收藏")
                .setMessage("是否删除" + mCityList.get(index) + "?")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface diaog, int which) {
                        mCityList.remove(index);
                        PrefUtil.putListString(MainLocationActivity.this, "city", mCityList);
                        initFab();
                        diaog.dismiss();
                    }
                }).setCancelable(false).show();
    }

    private boolean isAddCitySuccess() {
        String city = mEditText.getText().toString().trim();
        if (TextUtils.isEmpty(city)) {
            ToastUtils.showToast(MainLocationActivity.this, "城市名称为空");
            return false;
        }
        List<String> string = PrefUtil.getListString(this, "city");
        if (CommonUtil.isChinese(city)) {
            if (string.contains(city)) {
                ToastUtils.showToast(MainLocationActivity.this, "您已经添加过" + city);
                return false;
            }
            if (string.size() > CITY_NUM) {
                ToastUtils.showToast(MainLocationActivity.this, "城市收藏夹已满");
                return false;
            }
            mCityList.add(city);
            PrefUtil.putListString(MainLocationActivity.this, "city", mCityList);
        } else {
            ToastUtils.showToast(MainLocationActivity.this, "添加失败");
            return false;
        }
        ToastUtils.showToast(MainLocationActivity.this, "添加成功");
        return true;
    }


    private void closeMenu(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 0, 0, 0);
        objectAnimator.setDuration(300);
        objectAnimator.start();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.7f, 0);
        alphaAnimation.setDuration(300);
        alphaAnimation.setFillAfter(true);
        mViewCloud.startAnimation(alphaAnimation);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0, 1.0f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);
        scaleAnimation.setDuration(300);
        scaleAnimation.setFillAfter(true);
        mRlCity.startAnimation(scaleAnimation);
        //清除动画,不然无法设置visbility
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mViewCloud.clearAnimation();
                mRlCity.clearAnimation();
                mViewCloud.setVisibility(View.GONE);
                mRlCity.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mFloatingActionButton.setImageResource(R.mipmap.citywhite);
        fabOpened = false;
    }

    private void openMenu(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation", -135, 20, -0);
        objectAnimator.setDuration(300);
        objectAnimator.start();
        mViewCloud.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 0.7f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setFillAfter(true);
        mViewCloud.startAnimation(alphaAnimation);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1.0f, 0, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);
        scaleAnimation.setDuration(300);
        scaleAnimation.setFillAfter(true);
        mRlCity.startAnimation(scaleAnimation);
        mRlCity.setVisibility(View.VISIBLE);
        mFloatingActionButton.setImageResource(R.mipmap.add);
        fabOpened = true;
    }


    /***
     * 定位结束之后调用
     * @param location
     */
    @Override
    public void loadLocation(BDLocation location) {
        super.loadLocation(location);
        if (mActionView != null)
            ClearLocationState(mActionView);
        SwitchCity(location);
    }


    @Override
    public void loadLocationError(BDLocation location) {
        super.loadLocationError(location);
        if (mActionView != null)
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
        //获取当前位置
        mLocationPersenter.queryLateKnownLocation();
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
            mLocationPersenter.queryLocation();
            View actionView = item.getActionView();
            ShowAnimation(actionView);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 开始定位动画
     *
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
            final String finalCity = city;
            if (mBuilder == null) {
                mBuilder = new AlertDialog.Builder(this);
            }
            mBuilder.setMessage("是否切换到当前城市:" + city)
                    .setTitle("位置提示")
                    .setPositiveButton("切换", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            App.setCity(finalCity);
                            mToolbar.setTitle(App.getCity());
                            onQueryTextSubmit(App.getCity());
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).setCancelable(false).show();

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
        PrefUtil.putString(this, "CityName", city);
        // System.exit(0);
    }
}