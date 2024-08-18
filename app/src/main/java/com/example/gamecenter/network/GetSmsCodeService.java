package com.example.gamecenter.network;

import com.example.gamecenter.network.models.GetSmsCodeRequest;
import com.example.gamecenter.network.responses.GetSmsCodeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetSmsCodeService {
    @POST("quick-game/api/auth/sendCode")
    Call<GetSmsCodeResponse> getCodeData(@Body GetSmsCodeRequest getSmsCodeRequest);
}
