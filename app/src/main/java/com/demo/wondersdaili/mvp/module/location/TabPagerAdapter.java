package com.demo.wondersdaili.mvp.module.location;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 首页TabPagerAdapter
 * <p/>
 */
class TabPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[];
    private Fragment[] fragments;

    TabPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new Fragment[2];
    }

    void setTabTitles(@NonNull String[] tabTitles) {
        this.tabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        if (fragments == null || fragments.length == 0)
            return 0;
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (tabTitles == null)
            return "";
        return tabTitles[position];
    }

    public void setItems(Fragment[] fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }
}