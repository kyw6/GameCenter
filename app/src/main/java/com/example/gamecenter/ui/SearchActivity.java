package com.example.gamecenter.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gamecenter.R;
import com.example.gamecenter.ui.fragment.SearchFragment;
import com.example.gamecenter.ui.fragment.SearchResultFragment;
import com.example.gamecenter.utils.SearchHistoryManager;

/**
 * 搜索界面
 *
 * kyw目前待修复的：
 *  1. 最近搜索tag样式，间距
 *  2. tag排序不对，最新的放最前面，最旧的最后面，超出后删除最旧的
 *  3. tag最好能按照行数限制，而不是按照个数限制
 *  4. 删除按钮删除全部tag
 *  5. 动态搜索
 *  6. 下拉刷新
 *  7. 搜索结果界面，点击tag跳转到搜索结果界面
 */
public class SearchActivity extends AppCompatActivity {
    private EditText editTextSearch;
    private FrameLayout fragmentContainer;
    private TextView searchTextViewButton;
    private ImageButton buttonReturnSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这个方法有问题，Activity"1.9.1"版本会导致布局错乱，改成"1.8.0"后正常
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        // 获取 EditText 实例
        editTextSearch = findViewById(R.id.edit_text_search);
        fragmentContainer = findViewById(R.id.fragment_search_container);
        searchTextViewButton = findViewById(R.id.text_view_search);
        buttonReturnSearch = findViewById(R.id.button_return_search);

        // 初始化并显示默认 Fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_search_container, new SearchFragment())
                    .commit();
        }

        searchTextViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editTextSearch.getText().toString().trim();
                if (!query.isEmpty()) {
                    // 保存搜索历史
                    saveSearchHistory(query);
                }
                // 获取用户输入
                String userInput = editTextSearch.getText().toString();
                // 输出用户输入到日志
                if (userInput.isEmpty()) {
                    userInput = editTextSearch.getHint().toString();
                }
                // 跳转到搜索结果Fragment，传递用户输入
                SearchResultFragment searchResultFragment = new SearchResultFragment();
                searchResultFragment.fetchGameData(userInput);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_search_container, searchResultFragment)
                        .commit();
            }
        });

        buttonReturnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void saveSearchHistory(String query) {
        SearchHistoryManager searchHistoryManager = new SearchHistoryManager(this);
        searchHistoryManager.saveSearch(query);
    }

}