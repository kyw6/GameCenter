package com.example.gamecenter.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.gamecenter.R;
import com.example.gamecenter.network.HomePageApiService;
import com.example.gamecenter.network.RetrofitClient;
import com.example.gamecenter.network.responses.GameCenterResponse;
import com.example.gamecenter.ui.SearchActivity;
import com.example.gamecenter.ui.adapter.HomePageAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomePageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.home_page_recycler_view_games);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3)); // 设置每行显示三个游戏

        fetchGameData();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 获取 EditText 控件
        EditText editTextSearch = view.findViewById(R.id.edit_text_home_search);

        // 设置点击事件
        editTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到搜索页面
                Intent intent = new Intent(getActivity(), SearchActivity.class); // 替换为实际的搜索页面 Activity
                startActivity(intent);
                // 禁用动画
                getActivity().overridePendingTransition(0, 0);
            }
        });
    }

    private void fetchGameData() {
        int current = 1;
        int size = 3;
        HomePageApiService homePageApiService = RetrofitClient.getClient().create(HomePageApiService.class);
        Call<GameCenterResponse> call = homePageApiService.getGameCenterData(current, size);
        call.enqueue(new Callback<GameCenterResponse>() {
            @Override
            public void onResponse(Call<GameCenterResponse> call, Response<GameCenterResponse> response) {
                if (response.isSuccessful()) {
                    GameCenterResponse gameCenterResponse = response.body();
                    if (gameCenterResponse != null) {
                        List<GameCenterResponse.GameInfo> gameInfoList = gameCenterResponse.getData().getRecords().get(0).getGameInfoList();
                        adapter = new HomePageAdapter(gameInfoList);
                        recyclerView.setAdapter(adapter);
                    }
                } else {
                    // 处理请求失败的情况
                }
            }

            @Override
            public void onFailure(Call<GameCenterResponse> call, Throwable t) {
                // 处理请求失败的情况
            }
        });
    }
}

