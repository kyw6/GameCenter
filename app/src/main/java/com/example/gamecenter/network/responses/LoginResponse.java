package com.example.gamecenter.network.responses;

public class LoginResponse {
    private int code;
    private String msg;
    private Object data; // 使用 Object，如果 data 类型已知，可以更改为具体类型

    // Constructor
    public LoginResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // Getters and Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
