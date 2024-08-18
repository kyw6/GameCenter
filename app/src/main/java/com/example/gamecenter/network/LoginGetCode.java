package com.example.gamecenter.network;

import com.example.gamecenter.network.models.LoginRequest;
import com.example.gamecenter.network.responses.LoginGetCodeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginGetCode {
    @POST("quick-game/api/auth/sendCode")
    Call<LoginGetCodeResponse> getCodeData(@Body LoginRequest loginRequest);
}
