package com.example.gamecenter.fragment;

import static com.example.gamecenter.utils.PreferenceKeys.KEY_PRIVACY_ACCEPTED;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamecenter.MainActivity;
import com.example.gamecenter.R;
import com.example.gamecenter.utils.PreferencesUtil;

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
            // 跳转到主界面
            startMainActivity();
            // 保存到SharedPreferences中
            PreferencesUtil.init(getActivity());
            PreferencesUtil.setBoolean(KEY_PRIVACY_ACCEPTED, true);
        });

        // 设置不同意按钮的点击事件
        disagreeButton.setOnClickListener(v -> {
            // 退出应用
            getActivity().finish();
            System.exit(0); // 结束进程
        });

        initTextView(view);
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

    // 富文本设置点击事件、颜色、去掉下划线
    private void initTextView(View view) {

        TextView disclaimerAndTermsDetailed = view.findViewById(R.id.disclaimer_and_terms_detailed);

        // 原始文本
        String text = getString(R.string.splash_disclaimer_and_terms_detail);

        // 创建可点击的富文本
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);

        // 定位并设置《用户协议》的点击事件
        String userAgreement = getString(R.string.splash_user_agreement);
        int startUserAgreement = text.indexOf(userAgreement);
        int endUserAgreement = startUserAgreement + userAgreement.length();
        SpannableString userAgreementSpan = new SpannableString(userAgreement);
        ClickableSpan userAgreementClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // 处理用户协议的点击事件
                Toast.makeText(getContext(), "跳转到 用户协议 网站", Toast.LENGTH_SHORT).show();
                // 创建Intent跳转到指定网址
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com"));
                startActivity(browserIntent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false); // 去掉下划线
                ds.setColor(getResources().getColor(R.color.splash_yellow));
            }
        };
        //应用点击事件、颜色、去掉下划线
        userAgreementSpan.setSpan(userAgreementClickableSpan, 0, userAgreement.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.replace(startUserAgreement, endUserAgreement, userAgreementSpan);


        // 定位并设置《隐私协议》的点击事件
        String privacyPolicy = getString(R.string.splash_privacy_agreement);
        int startPrivacyPolicy = text.indexOf(privacyPolicy);
        int endPrivacyPolicy = startPrivacyPolicy + privacyPolicy.length();
        SpannableString privacyPolicySpan = new SpannableString(privacyPolicy);
        ClickableSpan privacyPolicyClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // 处理隐私协议的点击事件
                Toast.makeText(getContext(), "跳转到 隐私协议 网站", Toast.LENGTH_SHORT).show();
                // 创建Intent跳转到指定网址
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com"));
                startActivity(browserIntent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false); // 去掉下划线
                ds.setColor(getResources().getColor(R.color.splash_yellow));
            }
        };
        //应用点击事件、颜色、去掉下划线
        privacyPolicySpan.setSpan(privacyPolicyClickableSpan, 0, privacyPolicy.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.replace(startPrivacyPolicy, endPrivacyPolicy, privacyPolicySpan);

        // 设置文本到TextView
        disclaimerAndTermsDetailed.setText(spannableStringBuilder);
        disclaimerAndTermsDetailed.setMovementMethod(LinkMovementMethod.getInstance());  // 确保链接可点击
    }

    private void startMainActivity() {
        // 启动主Activity
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
