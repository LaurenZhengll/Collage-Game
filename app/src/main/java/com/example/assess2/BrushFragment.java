package com.example.assess2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BrushFragment extends BottomSheetDialogFragment {
    static BrushFragment instance;

    public static BrushFragment getInstance(){
        if(instance == null){
            instance = new BrushFragment();
        }
        return instance;
    }

    public BrushFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_brush, container, false);
    }

}
