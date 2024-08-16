package com.example.gamecenter.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gamecenter.R;
import com.example.gamecenter.ui.LoginActivity;

public class MyPageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);
        ImageView headImage = view.findViewById(R.id.image_head_my_page);
        headImage.setOnClickListener(new View.OnClickListener() {
            // 跳转至登录页面
            @Override
            public void onClick(View v) {
                if (getContext() != null) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    getContext().startActivity(intent);
                }
            }
        });

        return view;
    }
}
