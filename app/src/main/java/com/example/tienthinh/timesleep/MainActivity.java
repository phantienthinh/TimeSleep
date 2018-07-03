package com.example.tienthinh.timesleep;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tienthinh.timesleep.fragment.FragmentOne;
import com.example.tienthinh.timesleep.fragment.FragmentThree;
import com.example.tienthinh.timesleep.fragment.FragmentTwo;

public class MainActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
    ImageView imv_setting;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new FragmentOne();
                    break;
                case R.id.navigation_dashboard:
                    selectedFragment = new FragmentTwo();
                    break;
                case R.id.navigation_notifications:
                    selectedFragment = new FragmentThree();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmnet_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClickImvSetting();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmnet_container, new FragmentOne()).commit();
    }

    private void onClickImvSetting() {
        imv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        imv_setting = (ImageView)findViewById(R.id.imageViewSetting);
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn Lần Nữa Để Thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}

