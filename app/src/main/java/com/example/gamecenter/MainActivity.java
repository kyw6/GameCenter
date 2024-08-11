package com.example.gamecenter;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.gamecenter.fragment.HomeFragment;
import com.example.gamecenter.fragment.MyPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });

        // 默认显示首页
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

//        DpUtils dpUtils = new DpUtils(this);
//        Toast.makeText(this,dpUtils.pxToDp(40) + "dp" + " " + dpUtils.pxToDp(42) + "dp",Toast.LENGTH_SHORT).show();

    }
}