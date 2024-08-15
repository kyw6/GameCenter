package com.example.gamecenter.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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


public class SearchResultFragment extends Fragment {
    private RecyclerView recyclerView;
    private SearchPageAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_search_result);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1)); // 设置每行显示游戏数量
        return view;
    }

    public void fetchGameData(String search) {
        int current = 1;
        int size = 12;
        SearchPageApiService searchPageApiService = RetrofitClient.getClient().create(SearchPageApiService.class);
        Call<SearchGameResponse> call = searchPageApiService.searchGameCenterData(search, current, size);
        call.enqueue(new Callback<SearchGameResponse>() {
            @Override
            public void onResponse(Call<SearchGameResponse> call, Response<SearchGameResponse> response) {
                if (response.isSuccessful()) {
                    SearchGameResponse gameCenterResponse = response.body();
                    if (gameCenterResponse != null) {
                        List<SearchGameResponse.Data.Record> gameInfoList = gameCenterResponse.getData().getRecords();
                        if (gameInfoList.isEmpty()) {
                            showNoSearchFragment() ;
                        }
                        adapter = new SearchPageAdapter(gameInfoList);
                        recyclerView.setAdapter(adapter);
                    }
                } else {
                    // 处理请求失败的情况
                    showErrorFragment();

                }
            }

            @Override
            public void onFailure(Call<SearchGameResponse> call, Throwable t) {
                // 处理请求失败的情况
                showErrorFragment();
            }
        });
    }
    private void showErrorFragment() {
        FragmentManager fragmentManager = getParentFragmentManager(); // 获取父 FragmentManager
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_search_container, new ErrorFragment());
        fragmentTransaction.addToBackStack(null); // 可选：将替换操作添加到返回栈
        fragmentTransaction.commit();
    }

    private void showNoSearchFragment() {
        FragmentManager fragmentManager = getParentFragmentManager(); // 获取父 FragmentManager
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_search_container, new NoSearchResultFragment());
        fragmentTransaction.addToBackStack(null); // 可选：将替换操作添加到返回栈
        fragmentTransaction.commit();
    }


}