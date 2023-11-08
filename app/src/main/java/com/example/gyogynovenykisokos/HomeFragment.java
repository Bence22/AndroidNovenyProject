package com.example.gyogynovenykisokos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private static final String NAME_PARAM = "name";
    private String mName;


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String name) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(NAME_PARAM, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(NAME_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        TextView profileNameTextView = rootView.findViewById(R.id.fragment_home);
        if(mName != null) {
            profileNameTextView.setText(mName);
        }

        return rootView;
    }
}