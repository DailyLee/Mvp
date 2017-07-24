package com.demo.wondersdaili.mvp.module;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.demo.wondersdaili.mvp.module.location.MainLocationActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, MainLocationActivity.class));
        finish();
    }
}
