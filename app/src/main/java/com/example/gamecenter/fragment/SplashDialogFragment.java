package com.example.gamecenter.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.gamecenter.MainActivity;
import com.example.gamecenter.R;
import com.example.gamecenter.SplashActivity;

public class SplashDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_window, container, false);

        // 找到按钮视图
        FrameLayout agreeButton = view.findViewById(R.id.agree_button);
        FrameLayout disagreeButton = view.findViewById(R.id.disagree_button);

        // 设置同意按钮的点击事件
        agreeButton.setOnClickListener(v -> {
            // 跳转到另一个Activity
            startMainActivity();
        });

        // 设置不同意按钮的点击事件
        disagreeButton.setOnClickListener(v -> {
            // 退出应用
            getActivity().finish();
            System.exit(0); // 结束进程
        });

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.fragment_splash_window);
        // 获取对话框的窗口
        Window window = dialog.getWindow();
        if (window != null) {
            // 设置圆角
            window.setBackgroundDrawableResource(R.drawable.splash_rounded_corners);
            // 设置对话框的宽度和高度
            window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            // 设置对话框居中
            window.setGravity(Gravity.CENTER);

        }
        return dialog;
    }

    private void startMainActivity() {
        // 启动主Activity
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
