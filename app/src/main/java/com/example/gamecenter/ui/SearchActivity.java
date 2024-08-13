package com.example.gamecenter.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gamecenter.R;
import com.example.gamecenter.ui.fragment.SearchFragment;
import com.example.gamecenter.ui.fragment.SearchResultFragment;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        // 获取 EditText 实例
        EditText editTextSearch = findViewById(R.id.edit_text_search);
        FrameLayout fragmentContainer = findViewById(R.id.fragment_search_container);
        TextView searchTextView = findViewById(R.id.text_view_search);

        // 初始化并显示默认 Fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_search_container, new SearchFragment())
                    .commit();
        }

        searchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入
                String userInput = editTextSearch.getText().toString();
                // 输出用户输入到日志
                Log.d("SearchActivity", "User Input: " + userInput);
                // 创建新的 Fragment 实例
                SearchResultFragment searchResultFragment = new SearchResultFragment();

                // 创建 Bundle 并将数据放入其中
                Bundle args = new Bundle();
                args.putString("user_input", userInput);
                // 将 Bundle 设置到 Fragment 中
                searchResultFragment.setArguments(args);
                // 替换当前 Fragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_search_container, searchResultFragment)
                        .commit();
            }
        });
    }

}