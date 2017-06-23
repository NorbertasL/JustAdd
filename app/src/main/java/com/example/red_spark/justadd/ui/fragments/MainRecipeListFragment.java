package com.example.red_spark.justadd.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.red_spark.justadd.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
This fragment will display a list of recipes in a recycle view
 Using butterknife to reduce boilerplate code;
 */

public class MainRecipeListFragment extends Fragment {

    //Used by butterknife to set views to null
    private Unbinder unbinder;

    // Required empty public constructor
    public MainRecipeListFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_recipe_list, container, false);

        //butterknife set up
        unbinder = ButterKnife.bind(this, rootView);

        //returning main/root view
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //setting the view to null, have to do it for fragments.
        unbinder.unbind();
    }
}
