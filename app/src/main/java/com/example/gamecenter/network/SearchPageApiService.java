package com.example.gamecenter.network;


import com.example.gamecenter.network.responses.SearchGameResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchPageApiService {

    @GET("quick-game/game/search")
    Call<SearchGameResponse> searchGameCenterData(
            @Query("search") String search,
            @Query("current") int current,
            @Query("size") int size
    );
}
