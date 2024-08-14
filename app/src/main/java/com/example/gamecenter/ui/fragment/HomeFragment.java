package com.example.gamecenter.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamecenter.R;
import com.example.gamecenter.network.HomePageApiService;
import com.example.gamecenter.network.RetrofitClient;
import com.example.gamecenter.network.responses.GameCenterResponse;
import com.example.gamecenter.ui.SearchActivity;
import com.example.gamecenter.ui.adapter.MultiTypeHomePageAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private MultiTypeHomePageAdapter adapter;
    private List<GameCenterResponse.GameInfo> gameInfoList;
//    private HomePageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.home_page_recycler_view_games);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 12);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // 获取当前位置的 GameInfo 对象
                GameCenterResponse.GameInfo gameInfo = gameInfoList.get(position);

                // 根据 style 返回不同的跨度
                switch (gameInfo.getStyle()) {
                    case 1:
                        return 4; // 例如，style 为 1 时，一行显示12/4个
                    case 2:
                        return 3; // 例如，style 为 2 时，一行显示12/3个
                    default:
                        return 12; // 一行显示12/12个
                }
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);

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
        int size = 10;
        HomePageApiService homePageApiService = RetrofitClient.getClient().create(HomePageApiService.class);
        Call<GameCenterResponse> call = homePageApiService.getGameCenterData(current, size);
        call.enqueue(new Callback<GameCenterResponse>() {
            @Override
            public void onResponse(Call<GameCenterResponse> call, Response<GameCenterResponse> response) {
                if (response.isSuccessful()) {
                    GameCenterResponse gameCenterResponse = response.body();
                    if (gameCenterResponse != null) {
                        gameInfoList = addStyleToGameInfo(gameCenterResponse);
//                        adapter = new HomePageAdapter(gameInfoList);
//                        recyclerView.setAdapter(adapter);
                        adapter = new MultiTypeHomePageAdapter(gameInfoList);
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

    public List<GameCenterResponse.GameInfo> addStyleToGameInfo(GameCenterResponse gameCenterResponse) {
        // 创建一个新的列表来存储所有的 GameInfo
        List<GameCenterResponse.GameInfo> allGameInfoList = new ArrayList<>();

        // 获取 records 列表
        List<GameCenterResponse.Record> records = gameCenterResponse.getData().getRecords();

        // 遍历所有的 Record
        for (GameCenterResponse.Record record : records) {
            // 获取当前 Record 的 style 和 gameInfoList
            int style = record.getStyle();
            List<GameCenterResponse.GameInfo> CurrentgameInfoList = record.getGameInfoList();

            // 将 style 添加到每个 gameInfo 对象中
            for (GameCenterResponse.GameInfo gameInfo : CurrentgameInfoList) {
                gameInfo.setStyle(style);  // 假设 GameInfo 类中有 setStyle 方法
                allGameInfoList.add(gameInfo); // 添加到总列表中
            }
        }

        // 返回合并后的 gameInfoList 列表
        return allGameInfoList;
    }


}

