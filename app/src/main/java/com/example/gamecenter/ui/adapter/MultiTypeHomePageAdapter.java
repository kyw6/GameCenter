package com.example.gamecenter.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.gamecenter.R;
import com.example.gamecenter.network.responses.GameCenterResponse;
import com.example.gamecenter.network.responses.SearchGameResponse;

import java.util.List;

public class MultiTypeHomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_LAYOUT_TITLE = 0;
    private static final int TYPE_LAYOUT_THREE_NUM = 1;
    private static final int TYPE_LAYOUT_FOUR_NUM = 2;
    private static final int TYPE_LAYOUT_ONE_NUM = 3;

    private List<GameCenterResponse.GameInfo> gameList;

    public MultiTypeHomePageAdapter(List<GameCenterResponse.GameInfo> gameList) {
        this.gameList = gameList;

    }

    @Override
    public int getItemViewType(int position) {
        return gameList.get(position).getStyle();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TYPE_LAYOUT_TITLE:
                View view1 = inflater.inflate(R.layout.item_home_page_title, parent, false);
                return new ViewHolderTitle(view1);
            case TYPE_LAYOUT_THREE_NUM:
                View view2 = inflater.inflate(R.layout.item_home_page_three_num, parent, false);
                return new ViewHolderThreeNum(view2);
            case TYPE_LAYOUT_FOUR_NUM:
                View view3 = inflater.inflate(R.layout.item_home_page_four_num, parent, false);
                return new ViewHolderFourNum(view3);
            case TYPE_LAYOUT_ONE_NUM:
                View view4 = inflater.inflate(R.layout.item_home_page_one_num, parent, false);
                return new ViewHolderOneNum(view4);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GameCenterResponse.GameInfo game = gameList.get(position);
        int viewType = getItemViewType(position);

        // 根据不同的 ViewHolder 绑定数据
        switch (viewType) {
            case TYPE_LAYOUT_TITLE:
                if (holder instanceof ViewHolderTitle) {
//                    ((ViewHolderTitle) holder).gameName.setText(game.getGameName());
//                    Glide.with(holder.itemView.getContext())
//                            .load(game.getIcon())
//                            .transform(new RoundedCorners(32))//圆角设置
//                            .into(((ViewHolderTitle) holder).gameIcon);
                }
                break;
            case TYPE_LAYOUT_THREE_NUM:
                if (holder instanceof ViewHolderThreeNum) {
                    ((ViewHolderThreeNum) holder).gameName.setText(game.getGameName());
                    Glide.with(holder.itemView.getContext())
                            .load(game.getIcon())
                            .transform(new RoundedCorners(32))//圆角设置
                            .into(((ViewHolderThreeNum) holder).gameIcon);

                }
                break;
            case TYPE_LAYOUT_FOUR_NUM:
                if (holder instanceof ViewHolderFourNum) {
                    ((ViewHolderFourNum) holder).gameName.setText(game.getGameName());
                    Glide.with(holder.itemView.getContext())
                            .load(game.getIcon())
                            .transform(new RoundedCorners(32))//圆角设置
                            .into(((ViewHolderFourNum) holder).gameIcon);
                }
                break;
            case TYPE_LAYOUT_ONE_NUM:
                if (holder instanceof ViewHolderOneNum) {
                    ((ViewHolderOneNum) holder).gameName.setText(game.getGameName());
                    Glide.with(holder.itemView.getContext())
                            .load(game.getIcon())
                            .transform(new RoundedCorners(32))//圆角设置
                            .into(((ViewHolderOneNum) holder).gameIcon);
                }
                break;
        }
    }


    @Override
    public int getItemCount() {
        return gameList.size();
    }

    static class ViewHolderTitle extends RecyclerView.ViewHolder {
//        ImageView gameIcon;
//        TextView gameName;

        public ViewHolderTitle(View itemView) {
            super(itemView);
//            gameIcon = itemView.findViewById(R.id.imageView_item_title);
//            gameName = itemView.findViewById(R.id.textView_item_title);
        }


    }

    static class ViewHolderThreeNum extends RecyclerView.ViewHolder {
        ImageView gameIcon;
        TextView gameName;

        public ViewHolderThreeNum(View itemView) {
            super(itemView);
            gameIcon = itemView.findViewById(R.id.imageView_item_three_num);
            gameName = itemView.findViewById(R.id.textView_item_three_num);
        }

    }

    static class ViewHolderFourNum extends RecyclerView.ViewHolder {
        ImageView gameIcon;
        TextView gameName;

        public ViewHolderFourNum(View itemView) {
            super(itemView);
            gameIcon = itemView.findViewById(R.id.imageView_item_four_num);
            gameName = itemView.findViewById(R.id.textView_item_four_num);
        }


    }

    static class ViewHolderOneNum extends RecyclerView.ViewHolder {
        ImageView gameIcon;
        TextView gameName;

        public ViewHolderOneNum(View itemView) {
            super(itemView);
            gameIcon = itemView.findViewById(R.id.imageView_item_one_num);
            gameName = itemView.findViewById(R.id.textView_item_one_num);
        }

    }
}
