package com.example.gamecenter.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gamecenter.R;

public class LoginActivity extends AppCompatActivity {
    private TextView textViewGetVerificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 初始化 TextView
        textViewGetVerificationCode = findViewById(R.id.textview_get_verification_code);

        // 设置状态栏颜色
        Window window = getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // 图标是深色的
        );

        handleGetVerificationCode();// 处理获取验证码

    }

    // 处理获取验证码
    private void handleGetVerificationCode() {
        // 获取 TextView 并设置点击事件
        textViewGetVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示 Toast 消息
                Toast.makeText(LoginActivity.this, "获取验证码", Toast.LENGTH_SHORT).show();
                // 设置为不可点击
                textViewGetVerificationCode.setEnabled(false);
                textViewGetVerificationCode.setTextColor(Color.GRAY);
                startCountDown();//开始倒计时，倒计时结束后恢复原状，可以点击

            }
        });
    }


    // 开始倒计时
    private void startCountDown() {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textViewGetVerificationCode.getLayoutParams();
        new CountDownTimer(59000, 1000) { // 59秒倒计时，每秒更新一次
            public void onTick(long millisUntilFinished) {
                textViewGetVerificationCode.setText("获取验证码(" + millisUntilFinished / 1000 + "s)");
                // 动态修改 layout_marginStart 值
                int margin = dpToPx(230); // 将 dp 转换为像素
                layoutParams.setMarginStart(margin);
                textViewGetVerificationCode.setLayoutParams(layoutParams);
            }

            public void onFinish() {
                // 倒计时结束，恢复原状态
                int margin = dpToPx(250); // 将 dp 转换为像素
                layoutParams.setMarginStart(margin);
                textViewGetVerificationCode.setLayoutParams(layoutParams);
                textViewGetVerificationCode.setText("获取验证码");
                textViewGetVerificationCode.setTextColor(getResources().getColor(R.color.color_text_yellow_login_page)); // 恢复原来的颜色
                textViewGetVerificationCode.setEnabled(true); // 启用点击
            }
        }.start();
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}