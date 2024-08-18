package com.example.gamecenter.network.models;

public class LoginRequest {
    private String phone;

    // 构造函数
    public LoginRequest(String phone) {
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
