package com.example.gyogynovenykisokos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {
    private Context context;
    private List<PlantData> plantDataList;
    private ItemClickListener itemClickListener;


    public PlantAdapter(Context context, List<PlantData> plantDataList, ItemClickListener listener) {
        this.context = context;
        this.plantDataList = plantDataList;
        this.itemClickListener = listener;
    }
    public void updateData(List<PlantData> newPlantDataList) {
        plantDataList.clear();
        plantDataList.addAll(newPlantDataList);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant, parent, false);
        return new PlantViewHolder(view, itemClickListener, plantDataList);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        PlantData plantData = plantDataList.get(position);

        holder.commonNameTextView.setText(plantData.getCommon_name());
        holder.scientificNameTextView.setText(plantData.getScientific_name().toString());



        ImageData imageData = plantData.getDefault_image();
        if (imageData != null && imageData.getThumbnail() != null) {
            Glide.with(context)
                    .load(imageData.getThumbnail())
                    .into(holder.thumbnailImageView);
        } else {
            holder.thumbnailImageView.setImageDrawable(null);
        }

    }

    @Override
    public int getItemCount() {
        return plantDataList.size();
    }



    static class PlantViewHolder extends RecyclerView.ViewHolder {
        TextView commonNameTextView;
        TextView scientificNameTextView;
        ImageView thumbnailImageView;
        ImageView originalImageView;


        PlantViewHolder(View itemView, final ItemClickListener listener, final List<PlantData> dataList) {
            super(itemView);
            commonNameTextView = itemView.findViewById(R.id.commonNameTextView);
            scientificNameTextView = itemView.findViewById(R.id.scientificNameTextView);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
            originalImageView = itemView.findViewById(R.id.originalImageView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(dataList.get(position));
                        }
                    }
                }
            });
        }

    }
}
