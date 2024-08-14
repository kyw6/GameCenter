package com.example.gamecenter.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamecenter.R;
import com.example.gamecenter.network.models.Tag;

import java.util.List;



public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {

    private List<Tag> tags;
    private OnTagClickListener onTagClickListener;

    public interface OnTagClickListener {
        void onTagClick(String tag);
    }

    public TagAdapter(List<Tag> tags, OnTagClickListener listener) {
        this.tags = tags;
        this.onTagClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tag tag = tags.get(position);
        holder.textView.setText(tag.getText());
        holder.itemView.setOnClickListener(v -> onTagClickListener.onTagClick(tag.getText()));
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tagTextView);
        }
    }
}

