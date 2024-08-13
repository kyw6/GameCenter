package com.example.gamecenter.ui.adapter;

// HomePageAdapter.java

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gamecenter.R;
import com.example.gamecenter.network.responses.GameCenterResponse;

import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.GameViewHolder> {

    private List<GameCenterResponse.GameInfo> gameList;

    public HomePageAdapter(List<GameCenterResponse.GameInfo> gameList) {
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_page_item, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        GameCenterResponse.GameInfo game = gameList.get(position);
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
