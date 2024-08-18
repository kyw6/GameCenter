package com.example.gamecenter.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamecenter.R;
import com.example.gamecenter.network.models.Tag;
import com.example.gamecenter.ui.adapter.TagAdapter;
import com.example.gamecenter.utils.SearchHistoryManager;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchFragment extends Fragment {
    private RecyclerView recyclerView;
    private TagAdapter tagAdapter;
    private List<Tag> tags = new ArrayList<>();
    private SearchHistoryManager searchHistoryManager;
    private EditText searchEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView_fragment_search_tab);
        searchEditText = getActivity().findViewById(R.id.edit_text_search);
        searchHistoryManager = new SearchHistoryManager(getContext());
        tagAdapter = new TagAdapter(tags, new TagAdapter.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                // 设置搜索框的文本
                searchEditText.setText(tag);
                searchEditText.setSelection(tag.length());
                //直接切换到搜索结果页面
                SearchResultFragment searchResultFragment = new SearchResultFragment();
                searchResultFragment.fetchGameData(tag);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_search_container, searchResultFragment)
                        .commit();
            }
        });

        recyclerView.setAdapter(tagAdapter);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);//紧密布局
        recyclerView.setLayoutManager(layoutManager);
        updateTagList();

    }


    private void updateTagList() {
        tags.clear();
        Set<String> history = searchHistoryManager.getSearchHistory();
        for (String tag : history) {
            tags.add(new Tag(tag));
        }
        tagAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateTagList();
    }
}