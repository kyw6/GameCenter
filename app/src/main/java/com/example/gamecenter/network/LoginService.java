package com.example.gamecenter.network;

import com.example.gamecenter.network.models.LoginRequest;
import com.example.gamecenter.network.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("quick-game/api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
