package com.example.gamecenter.ui.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.gamecenter.R;
import com.example.gamecenter.network.responses.SearchGameResponse;

import java.util.List;

public class SearchPageAdapter extends RecyclerView.Adapter<SearchPageAdapter.GameViewHolder> {
    private List<SearchGameResponse.Data.Record> gameList;

    public SearchPageAdapter(List<SearchGameResponse.Data.Record> gameList) {
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public SearchPageAdapter.GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_result, parent, false);
        return new SearchPageAdapter.GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchPageAdapter.GameViewHolder holder, int position) {
        SearchGameResponse.Data.Record game = gameList.get(position);
        holder.gameName.setText(game.getGameName());
        holder.brief.setText(game.getBrief());
        holder.gameLabelContainer.removeAllViews();
        String tags = game.getTags();
        // 分割 tags
        String[] tagArray = tags.split(",");
        // 创建并添加 TextView 标签
        for (String tag : tagArray) {
            TextView tagView = new TextView(holder.itemView.getContext());
            tagView.setText(tag.trim()); // 去除可能的空格
            tagView.setWidth(150);
            tagView.setHeight(50);
            tagView.setGravity(Gravity.CENTER); // 设置文字在背景中居中显示
            tagView.setTextSize(12);
            // 创建 LayoutParams 并设置 margin
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(8, 3, 8, 3); // 设置 margin
            tagView.setLayoutParams(layoutParams);
            tagView.setBackgroundResource(R.drawable.rectangle_background_search_page); // 设定背景
            holder.gameLabelContainer.addView(tagView);
        }
        Glide.with(holder.itemView.getContext())
                .load(game.getIcon())
                .transform(new RoundedCorners(32))//圆角设置
                .into(holder.gameIcon);
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        ImageView gameIcon;
        TextView gameName;
        TextView brief;
        LinearLayout gameLabelContainer;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            gameIcon = itemView.findViewById(R.id.game_icon);
            gameName = itemView.findViewById(R.id.game_name);
            brief = itemView.findViewById(R.id.game_describe);
            gameLabelContainer = itemView.findViewById(R.id.game_label_container);
        }
    }
}
