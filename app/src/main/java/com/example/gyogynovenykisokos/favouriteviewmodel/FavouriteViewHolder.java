package com.example.gyogynovenykisokos.favouriteviewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gyogynovenykisokos.R;

public class FavouriteViewHolder extends RecyclerView.ViewHolder {
    private final TextView favouriteItemView;

    private FavouriteViewHolder(View itemView) {
        super(itemView);
        favouriteItemView = itemView.findViewById(R.id.recyclerview_favourite);
    }

    public void bind(String text) {
        favouriteItemView.setText(text);
    }

    static FavouriteViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_plant, parent, false);
        return new FavouriteViewHolder(view);

        }
    }
