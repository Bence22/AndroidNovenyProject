package com.example.gyogynovenykisokos;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class ProfileFragment extends Fragment {

    private TextView commonNameTextView;
    private TextView scientificNameTextView;
    private TextView otherNameTextView;
    private TextView cycleTextView;
    private TextView wateringTextView;
    private TextView sunlightTextView;
    private ImageView originalImageView;

    public ProfileFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        originalImageView = view.findViewById(R.id.originalImageView);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        Bundle args = getArguments();
        if(args != null){
            PlantData plant = (PlantData) args.getSerializable("plant");
            commonNameTextView = view.findViewById(R.id.commonNameTextView);
            scientificNameTextView = view.findViewById(R.id.scientificNameTextView);
            otherNameTextView = view.findViewById(R.id.otherNameTextView);
            cycleTextView = view.findViewById(R.id.cycleTextView);
            wateringTextView = view.findViewById(R.id.wateringTextView);
            sunlightTextView = view.findViewById(R.id.sunlightTextView);
            originalImageView = view.findViewById(R.id.originalImageView);

            commonNameTextView.setText("Common Name: " + plant.getCommon_name());
            scientificNameTextView.setText("Scientific Name: " + plant.getScientific_name());
            otherNameTextView.setText("Other Name: " + plant.getOther_name());
            cycleTextView.setText("Cycle: " + plant.getCycle());
            wateringTextView.setText("Watering: " + plant.getWatering());
            sunlightTextView.setText("Sunlight: " + plant.getSunlight().toString());

            if (plant.getDefault_image() != null && plant.getDefault_image().getOriginal_url() != null) {
                Glide.with(requireContext()).load(plant.getDefault_image().getOriginal_url()).into(originalImageView);
            } else {
                originalImageView.setImageDrawable(null);
            }
        }
        return view;
    }


}