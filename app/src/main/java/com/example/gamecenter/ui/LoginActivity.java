package com.example.gamecenter.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gamecenter.R;
import com.example.gamecenter.network.GetSmsCodeService;
import com.example.gamecenter.network.RetrofitClient;
import com.example.gamecenter.network.models.GetSmsCodeRequest;
import com.example.gamecenter.network.responses.GetSmsCodeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextPhone;//手机号输入框
    private EditText editTextVerificationCode;//验证码输入框
    private CheckBox checkBoxReadAndAgree;//阅读并同意
    private Button buttonLogin;//登录按钮
    private TextView textViewGetVerificationCode;//获取验证码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 初始化 视图
        editTextVerificationCode = findViewById(R.id.edit_text_verification_code);
        editTextPhone = findViewById(R.id.edit_text_phone);
        checkBoxReadAndAgree = findViewById(R.id.checkbox_read_and_agree);
        buttonLogin = findViewById(R.id.button_login);
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


        handleGetVerificationCode();// 处理获取验证码点击事件
        handleLoginButtonClick();//处理登录按钮点击事件

    }

    // 处理获取验证码点击事件
    private void handleGetVerificationCode() {
        // 获取 TextView 并设置点击事件
        textViewGetVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入
                String phoneNumber = editTextPhone.getText().toString();
                // 验证手机号
                if (isValidPhoneNumber(phoneNumber)) {
                    textViewGetVerificationCode.setEnabled(false);// 设置为不可点击
                    textViewGetVerificationCode.setTextColor(Color.GRAY);
                    startCountDown();//开始倒计时，倒计时结束后恢复原状，可以点击
                    fetchVerificationCode(phoneNumber);// 获取验证码
                } else {
                    // 弹出提示
                    Toast.makeText(LoginActivity.this, "请输入有效的11位手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //先设置按钮不可点击，只有输入了手机号且长度为11，并且验证码输入了6位，并且阅读同意选框勾选了，按钮才能点击
    private void handleLoginButtonClick() {
        // 设置按钮初始状态为不可点击
        buttonLogin.setEnabled(false);
        // 监听输入框内容变化
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 检查输入框内容变化并更新按钮状态
                validateInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        editTextPhone.addTextChangedListener(textWatcher);
        editTextVerificationCode.addTextChangedListener(textWatcher);
        // 监听复选框状态变化
        checkBoxReadAndAgree.setOnCheckedChangeListener((buttonView, isChecked) -> validateInputs());
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入
                String phoneNumber = editTextPhone.getText().toString();
                String verificationCode = editTextVerificationCode.getText().toString();

                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 检查三个输入框内容是否满足要求
    private void validateInputs() {
        String phone = editTextPhone.getText().toString().trim();
        String verificationCode = editTextVerificationCode.getText().toString().trim();
        boolean isAgreementChecked = checkBoxReadAndAgree.isChecked();
        boolean isPhoneValid = phone.length() == 11;
        boolean isVerificationCodeValid = verificationCode.length() == 6;
        // 根据条件启用或禁用登录按钮
        buttonLogin.setEnabled(isPhoneValid && isVerificationCodeValid && isAgreementChecked);
    }

    // 获取验证码
    private void fetchVerificationCode(String phoneNumber) {
        GetSmsCodeRequest getSmsCodeRequest = new GetSmsCodeRequest(phoneNumber);
        GetSmsCodeService getSmsCodeService = RetrofitClient.getClient().create(GetSmsCodeService.class);

        Call<GetSmsCodeResponse> call = getSmsCodeService.getCodeData(getSmsCodeRequest);
        call.enqueue(new Callback<GetSmsCodeResponse>() {
            @Override
            public void onResponse(Call<GetSmsCodeResponse> call, Response<GetSmsCodeResponse> response) {
                if (response.isSuccessful()) {
                    // 请求成功，处理响应
                    GetSmsCodeResponse responseData = response.body();
                    if (responseData != null && responseData.getCode() == 200) {
                        // 显示 Toast 消息
                        Toast.makeText(LoginActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 处理请求失败的情况
                    Toast.makeText(LoginActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetSmsCodeResponse> call, Throwable t) {
                // 处理网络请求失败的情况
                Toast.makeText(LoginActivity.this, "网络请求错误", Toast.LENGTH_SHORT).show();
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

    // 验证手机号是否有效
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 11 && phoneNumber.matches("\\d+");
    }
}