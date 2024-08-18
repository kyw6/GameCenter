package com.example.gamecenter.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.LinkedHashSet;
import java.util.Set;

public class SearchHistoryManager {
    private static final String PREFS_NAME = "search_history";
    private static final String HISTORY_KEY = "history";
    private static final int MAX_HISTORY_SIZE = 10;

    private SharedPreferences sharedPreferences;

    public SearchHistoryManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveSearch(String query) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> searchHistory = new LinkedHashSet<>(getSearchHistory());
        searchHistory.add(query);
        if (searchHistory.size() > MAX_HISTORY_SIZE) {
            searchHistory.remove(searchHistory.iterator().next()); // Remove the oldest entry
        }
        editor.putStringSet(HISTORY_KEY, searchHistory);
        //使用commit()方法提交更改满了也会成功，使用apply()方法提交更改满了会失败
        editor.commit();
    }

    public Set<String> getSearchHistory() {
        Set<String> history = sharedPreferences.getStringSet(HISTORY_KEY, new LinkedHashSet<>());
        return history;
    }

}
