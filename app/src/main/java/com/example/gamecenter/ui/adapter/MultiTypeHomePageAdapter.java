package com.example.gamecenter.ui.adapter;

import android.util.Log;
import android.util.TypedValue;
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
import com.example.gamecenter.network.responses.GameCenterResponse;

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
                    ((ViewHolderTitle) holder).title.setText(game.getGameName());
                }
                break;
            case TYPE_LAYOUT_THREE_NUM:
                if (holder instanceof ViewHolderThreeNum) {
                    ((ViewHolderThreeNum) holder).gameName.setText(game.getGameName());
                    StringBuilder sb = new StringBuilder();
                    sb.append(game.getPlayNumFormat());
                    sb.append("人正在玩");
                    String playNumbers = sb.toString();
                    ((ViewHolderThreeNum) holder).playNumFormat.setText(playNumbers);
                    Glide.with(holder.itemView.getContext())
                            .load(game.getIcon())
                            .transform(new RoundedCorners(60))//圆角设置
                            .into(((ViewHolderThreeNum) holder).gameIcon);

                }
                break;
            case TYPE_LAYOUT_FOUR_NUM:
                if (holder instanceof ViewHolderFourNum) {
                    ((ViewHolderFourNum) holder).gameName.setText(game.getGameName());
                    // 获取 tags 并分割
                    String tags = game.getTags();
                    String tag = tags.split(",")[0].trim();  // 只获取第一个标签并去除可能的空格
                    Log.d("kyw", "tag: " + tag);
                    // 创建并配置 TextView 标签
                    TextView tagView = new TextView(holder.itemView.getContext());
                    tagView.setText(tag);
                    tagView.setWidth(60);
                    tagView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 28);

                    // 创建 LayoutParams 并设置 margin
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    tagView.setLayoutParams(layoutParams);

                    // 将标签添加到容器中
                    ((ViewHolderFourNum) holder).gameLabelContainer.addView(tagView);


                    Glide.with(holder.itemView.getContext())
                            .load(game.getIcon())
                            .transform(new RoundedCorners(42))//圆角设置
                            .into(((ViewHolderFourNum) holder).gameIcon);
                }
                break;
            case TYPE_LAYOUT_ONE_NUM:
                if (holder instanceof ViewHolderOneNum) {
                    ((ViewHolderOneNum) holder).gameName.setText(game.getGameName());
                    ((ViewHolderOneNum) holder).brief.setText(game.getBrief());
                    ((ViewHolderOneNum) holder).gameLabelContainer.removeAllViews();
                    // 分割 tags
                    String tags = game.getTags();
                    String[] tagArray = tags.split(",");
                    // 创建并添加 TextView 标签
                    for (String tag : tagArray) {
                        TextView tagView = new TextView(holder.itemView.getContext());
                        tagView.setText(tag.trim()); // 去除可能的空格
                        tagView.setWidth(80);
                        tagView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 36);
                        // 创建 LayoutParams 并设置 margin
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        tagView.setLayoutParams(layoutParams);
                        ((ViewHolderOneNum) holder).gameLabelContainer.addView(tagView);
                    }
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
        TextView title;

        public ViewHolderTitle(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_item_title);
        }
    }

    static class ViewHolderThreeNum extends RecyclerView.ViewHolder {
        ImageView gameIcon;
        TextView gameName;
        TextView playNumFormat;

        public ViewHolderThreeNum(View itemView) {
            super(itemView);
            gameIcon = itemView.findViewById(R.id.imageView_item_three_num);
            gameName = itemView.findViewById(R.id.textView_item_three_num);
            playNumFormat = itemView.findViewById(R.id.textView_play_num_item_three_num);
        }

    }

    static class ViewHolderFourNum extends RecyclerView.ViewHolder {
        ImageView gameIcon;
        TextView gameName;
        LinearLayout gameLabelContainer;

        public ViewHolderFourNum(View itemView) {
            super(itemView);
            gameIcon = itemView.findViewById(R.id.imageView_item_four_num);
            gameName = itemView.findViewById(R.id.textView_item_four_num);
            gameLabelContainer = itemView.findViewById(R.id.game_label_container);
        }


    }

    static class ViewHolderOneNum extends RecyclerView.ViewHolder {
        ImageView gameIcon;
        TextView gameName;
        TextView brief;
        LinearLayout gameLabelContainer;

        public ViewHolderOneNum(View itemView) {
            super(itemView);
            gameIcon = itemView.findViewById(R.id.imageView_item_one_num);
            gameName = itemView.findViewById(R.id.textView_item_one_num);
            brief = itemView.findViewById(R.id.game_describe);
            gameLabelContainer = itemView.findViewById(R.id.game_label_container);

        }

    }
}
