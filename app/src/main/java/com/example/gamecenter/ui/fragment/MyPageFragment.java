package com.example.gamecenter.ui.fragment;

import static com.example.gamecenter.utils.PreferenceKeys.KEY_LOGIN_TOKEN;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gamecenter.R;
import com.example.gamecenter.ui.LoginActivity;
import com.example.gamecenter.utils.PreferencesUtil;

import java.util.Objects;

public class MyPageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);
        ImageView headImage = view.findViewById(R.id.image_head_my_page);
        TextView username = view.findViewById(R.id.text_login_my_page);

        if (isLoggedIn(getContext())) {
            username.setText("疯狂的马老师");
            headImage.setImageResource(R.drawable.ic_head_login);
        } else {
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
        }
        return view;
    }

    // 检查是否已登录
    private boolean isLoggedIn(Context context) {
        PreferencesUtil.init(context);
        if (PreferencesUtil.getString(KEY_LOGIN_TOKEN, null) != null) {
            return true;
        }
        return false;
    }
}
