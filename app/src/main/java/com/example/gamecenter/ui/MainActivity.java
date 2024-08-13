package com.example.gamecenter.ui;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.gamecenter.R;
import com.example.gamecenter.ui.fragment.HomeFragment;
import com.example.gamecenter.ui.fragment.MyPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 设置状态栏颜色
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        // 设置状态栏图标颜色为深色（适用于白色背景）
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        // 底部导航栏
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_my_page) {
                selectedFragment = new MyPageFragment();
            }
            if (selectedFragment != null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_home_container, selectedFragment)
                        .commit();
            }
            return true;
        });

        // 默认显示首页
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
    }
}