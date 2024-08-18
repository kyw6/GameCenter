package com.example.gamecenter.network.responses;

public class LoginGetCodeResponse {

    private int code;
    private String msg;
    private boolean data;

    // Getter 和 Setter 方法

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

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginGetCodeResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
