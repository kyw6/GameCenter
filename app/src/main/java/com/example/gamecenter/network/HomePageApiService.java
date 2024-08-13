package com.example.gamecenter.network;

import com.example.gamecenter.network.responses.GameCenterResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomePageApiService {
    @GET("quick-game/game/homePage")
    Call<GameCenterResponse> getGameCenterData(
            @Query("current") int current,
            @Query("size") int size
    );
}
