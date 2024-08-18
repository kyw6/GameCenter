package com.example.gamecenter.network.models;
// 获取验证码请求
public class GetSmsCodeRequest {
    private String phone;

    // 构造函数
    public GetSmsCodeRequest(String phone) {
        this.phone = phone;
    }

    // Getter 和 Setter
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
