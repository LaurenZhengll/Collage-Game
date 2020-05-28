package com.example.assess2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import static com.example.assess2.MainActivity.PERMISSION_INSERT_IMAGE;

public class ImageFragment extends BottomSheetDialogFragment {

    GridView imageGrid;

    static String EXTRA_POSITION;

    private ImageFragmentListener listener;

    /* use interface to transfer image from fragment to main avtivity*/
    public interface ImageFragmentListener{
        void onImageItemClick(int position); // the function is implemented in main activity
    }

    public ImageFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);

        imageGrid = view.findViewById(R.id.imageGrid);
        imageGrid.setAdapter(new ImageAdapter(getContext())); // set image grid to fragment

        imageGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {  // listen item click event
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onImageItemClick(position); // function call and transfer position

                /*//Use Intent to bind components or activities, here is starting another activity for result.
                Intent intent = new Intent(getContext(), MainActivity.class);
                *//*An intent carries key-value pairs called extras.*//*
                intent.putExtra(EXTRA_POSITION, position);
                *//*system delivers intent*//*
                startActivity(intent);*/

            }
        });

        return view;
    }

    /* context is main activity. attach fragment to main activity.*/
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        /* if main activity implement ImageFragmentListener. */
        if(context instanceof ImageFragmentListener){
            listener = (ImageFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement ImageFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
