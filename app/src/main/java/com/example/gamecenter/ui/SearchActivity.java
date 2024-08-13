package com.example.gamecenter.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamecenter.R;
import com.example.gamecenter.network.RetrofitClient;
import com.example.gamecenter.network.SearchPageApiService;
import com.example.gamecenter.network.responses.SearchGameResponse;
import com.example.gamecenter.ui.adapter.SearchPageAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SearchPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recycler_view_search_result);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1)); // 设置每行显示三个游戏

        fetchGameData();

    }


    private void fetchGameData() {
        int current = 1;
        int size = 3;
        Log.d("kyw", "发起请求 ");
        String search = "鱼";
        SearchPageApiService searchPageApiService = RetrofitClient.getClient().create(SearchPageApiService.class);
        Call<SearchGameResponse> call = searchPageApiService.searchGameCenterData(search, current, size);
        call.enqueue(new Callback<SearchGameResponse>() {
            @Override
            public void onResponse(Call<SearchGameResponse> call, Response<SearchGameResponse> response) {
                if (response.isSuccessful()) {
                    SearchGameResponse gameCenterResponse = response.body();
                    if (gameCenterResponse != null) {
                        List<SearchGameResponse.Data.Record> gameInfoList = gameCenterResponse.getData().getRecords();
                        Log.d("kyw", "onResponse: " + gameInfoList);
                        adapter = new SearchPageAdapter(gameInfoList);
                        recyclerView.setAdapter(adapter);
                    }
                } else {
                    // 处理请求失败的情况
                    Log.d("kyw", "失败1 ");
                }
            }

            @Override
            public void onFailure(Call<SearchGameResponse> call, Throwable t) {
                // 处理请求失败的情况
                Log.d("kyw", "失败2 ");
            }
        });
    }
}