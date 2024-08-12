package com.example.gamecenter.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.gamecenter.R;
import com.example.gamecenter.SearchActivity;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
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
}

