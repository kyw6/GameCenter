package com.example.gamecenter.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
        Glide.with(holder.itemView.getContext())
                .load(game.getIcon())
                .into(holder.gameIcon);
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        ImageView gameIcon;
        TextView gameName;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            gameIcon = itemView.findViewById(R.id.game_icon);
            gameName = itemView.findViewById(R.id.game_name);
        }
    }
}
