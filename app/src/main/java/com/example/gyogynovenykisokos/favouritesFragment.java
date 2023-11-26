package com.example.gyogynovenykisokos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class favouritesFragment extends Fragment {

    private RecyclerView recyclerview_favourite;
    public favouritesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourites, container, false);
        recyclerview_favourite = rootView.findViewById(R.id.recyclerview_favourite);
        recyclerview_favourite.setLayoutManager(new LinearLayoutManager(getContext()));
        return rootView;
    }
}
