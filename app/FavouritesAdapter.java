package com.example.gyogynovenykisokos;

import java.util.List;
import android.os.Bundle;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.
public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder>{
    private List<FavouritePlant> favouritePlants;

    public FavouritesAdapter(List<FavouritePlant> favouritePlants) {
        this.favouritePlants = favouritePlants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite_plant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavouritePlant favouritePlant = favouritePlants.get(position);
        holder.commonNameTextView.setText(favouritePlant.getCommonName());
        holder.scientificNameTextView.setText(favouritePlant.getScientificName());
    }

    @Override
    public int getItemCount() {
        return favouritePlants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView commonNameTextView;
        TextView scientificNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            commonNameTextView = itemView.findViewById(R.id.commonNameTextView);
            scientificNameTextView = itemView.findViewById(R.id.scientificNameTextView);
        }
    }
}
}
