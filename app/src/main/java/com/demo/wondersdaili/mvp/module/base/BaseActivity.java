package com.demo.wondersdaili.mvp.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by daili on 2017/4/1.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPermission();
        initInjector();
        initViews();
        initData();
    }

    protected void initInjector() {

    }

    protected void initPermission() {

    }

    protected abstract void initData();

    protected abstract void initViews();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
