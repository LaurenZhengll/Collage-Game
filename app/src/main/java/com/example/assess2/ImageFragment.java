package com.example.assess2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

public class ImageFragment extends Fragment {
    GridView imageGrid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        imageGrid = view.findViewById(R.id.imageGrid);
        imageGrid.setAdapter(new ImageAdapter());


        // Inflate the layout for this fragment
        return view;

    }

}
