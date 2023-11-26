package com.example.gyogynovenykisokos.favouritelistadapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.gyogynovenykisokos.entity.Favourites;
import com.example.gyogynovenykisokos.favouriteviewholder.FavouriteViewHolder;

import java.util.Objects;

public class FavouriteListAdapter extends ListAdapter<Favourites, FavouriteViewHolder> {

    public FavouriteListAdapter(@NonNull DiffUtil.ItemCallback<Favourites> diffCallback) {
        super(diffCallback);
    }

    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return FavouriteViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder holder, int position) {
        Favourites current = getItem(position);
        holder.bind(current.getId());
    }

    public static class FavouritesDiff extends DiffUtil.ItemCallback<Favourites> {

        @Override
        public boolean areItemsTheSame(@NonNull Favourites oldItem, @NonNull Favourites newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Favourites oldItem, @NonNull Favourites newItem) {
            return oldItem != null && newItem != null && Objects.equals(oldItem.getId(), newItem.getId());

        }
    }
}