package com.example.gamecenter.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gamecenter.R;
import com.example.gamecenter.network.RetrofitClient;
import com.example.gamecenter.network.SearchPageApiService;
import com.example.gamecenter.network.responses.SearchGameResponse;
import com.example.gamecenter.ui.adapter.SearchPageAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchResultFragment extends Fragment {
    private RecyclerView recyclerView;
    private SearchPageAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        // 获取 Bundle 和参数
        String userInput = "";
        Bundle args = getArguments();
        if (args != null) {
            userInput = args.getString("user_input");
        }
        Log.d("kyw", "userInput: " + userInput);
        recyclerView = view.findViewById(R.id.recycler_view_search_result);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1)); // 设置每行显示三个游戏
        fetchGameData(userInput);
        return view;
    }


    private void fetchGameData(String search) {
        int current = 1;
        int size = 7;
        Log.d("kyw", "发起请求 ");
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