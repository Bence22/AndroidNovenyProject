package com.example.gyogynovenykisokos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {
    private Context context;
    private List<PlantData> plantDataList;


    public PlantAdapter(Context context, List<PlantData> plantDataList) {
        this.context = context;
        this.plantDataList = plantDataList;

    }
    public void updateData(List<PlantData> newPlantDataList) {
        plantDataList.clear();
        plantDataList.addAll(newPlantDataList);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_plant, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        PlantData plantData = plantDataList.get(position);

        holder.commonNameTextView.setText(plantData.getCommon_name());
        holder.scientificNameTextView.setText(plantData.getScientific_name().toString());
    }

    @Override
    public int getItemCount() {
        return plantDataList.size();
    }

    static class PlantViewHolder extends RecyclerView.ViewHolder {
        TextView commonNameTextView;
        TextView scientificNameTextView;

        PlantViewHolder(View itemView) {
            super(itemView);
            commonNameTextView = itemView.findViewById(R.id.commonNameTextView);
            scientificNameTextView = itemView.findViewById(R.id.scientificNameTextView);
        }
    }
}
