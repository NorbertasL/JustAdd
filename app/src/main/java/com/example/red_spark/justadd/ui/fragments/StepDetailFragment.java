package com.example.red_spark.justadd.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.red_spark.justadd.R;

/**
 *
 *Fragment that contain a detain description of the step
 * That could include a video and/or a text description
 */
public class StepDetailFragment extends Fragment {

    public StepDetailFragment() {
        // Required empty public constructor
    }

//TODO set up StepDetailsFragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_step_detail, container, false);
    }

}
