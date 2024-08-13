package com.example.gamecenter.ui;

import static com.example.gamecenter.utils.PreferenceKeys.KEY_PRIVACY_ACCEPTED;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;


import androidx.appcompat.app.AppCompatActivity;


import com.example.gamecenter.R;
import com.example.gamecenter.ui.fragment.SplashDialogFragment;
import com.example.gamecenter.utils.PreferencesUtil;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 2000; // 闪屏显示时间，单位是毫秒


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置状态栏颜色
        Window window = getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // 图标是深色的
        );
        setContentView(R.layout.activity_splash);

        PreferencesUtil.init(this);
        boolean privacyAccepted = PreferencesUtil.getBoolean(KEY_PRIVACY_ACCEPTED, false);
        new Handler().postDelayed(() -> {
            if (privacyAccepted) {
                // 用户已经同意隐私政策，直接进入主页面
                startMainActivity();
            } else {
                // 显示DialogFragment
                SplashDialogFragment dialogFragment = new SplashDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void startMainActivity() {
        // 启动主Activity
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
