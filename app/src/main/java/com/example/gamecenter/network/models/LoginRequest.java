package com.example.gamecenter.network.models;

public class LoginRequest {
    private String phone;
    private String smsCode;

    // Constructor
    public LoginRequest(String phone, String smsCode) {
        this.phone = phone;
        this.smsCode = smsCode;
    }

    // Getters and Setters
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
