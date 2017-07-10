package com.example.red_spark.justadd.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.data.Constants;
import com.example.red_spark.justadd.data.gson.JsonConverter;
import com.example.red_spark.justadd.ui.MainActivity;
import com.example.red_spark.justadd.ui.RecipeStepsActivity;
import com.example.red_spark.justadd.ui.adapters.MainRecipeListFragmentAdapter;
import com.example.red_spark.justadd.data.gson.RecipeData;
import com.google.gson.Gson;

import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 * This fragment will display a list of recipes in a recycle view
 * Using butterknife to reduce boilerplate code;
 * Using Retrofit for the network request
 * Using GSON to format the data
 */

public class MainRecipeListFragment extends Fragment
        implements MainRecipeListFragmentAdapter.AdapterOnClickHandler{

    //instance save keys
    private final static String LAYOUT_KEY = "layout_key";

    private RecyclerView.LayoutManager layoutManager;
    private MainRecipeListFragmentAdapter adapter;

    private Gson mGson;




    //Used by butterknife to set views to null
    private Unbinder unbinder;

    //Using butterknife to bind views
    @BindView(R.id.recyclerView_recipe_list) RecyclerView recyclerView;
    @BindView(R.id.error_message_display) TextView errorView;
    @BindView(R.id.loading_indicator) ProgressBar progressBarView;


    // Required empty public constructor
    public MainRecipeListFragment() {
        mGson = new Gson();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_recipe_list, container, false);
        //butterknife set up
        unbinder = ButterKnife.bind(this, rootView);

        layoutManager = new LinearLayoutManager(getActivity());

        if(savedInstanceState != null) {
            layoutManager.onRestoreInstanceState(savedInstanceState.getParcelable(LAYOUT_KEY));
        }




        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //Creating the adapter
        adapter =  new MainRecipeListFragmentAdapter(this);

        //binding adapter to recyclerView
        recyclerView.setAdapter(adapter);




        //this retrieves the data from the main activity is it is store there
        //used for restoring instance states
        ArrayList<RecipeData> mRecipeData = ((MainActivity)getActivity()).getData();
        if(mRecipeData != null && mRecipeData.size()>0){
            setRecipeData(mRecipeData);
        }



        //returning main/root view
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //saving the layout manager state
        outState.putParcelable(LAYOUT_KEY, layoutManager.onSaveInstanceState());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //setting the view to null, we have to do it for fragments.
        unbinder.unbind();

    }


    @Override
    public void onClick(RecipeData recipeData) {
        //Toast.makeText(getActivity(), "Clicked on"+recipeData.name, Toast.LENGTH_SHORT).show();

        String jsonString = JsonConverter.classToJsonString(recipeData, mGson);

        startActivity(new Intent(getActivity(), RecipeStepsActivity.class)
                .putExtra(Constants.RECIPE_DATA_BUNDLE_KEY, jsonString));
    }


    public void setRecipeData(ArrayList<RecipeData> recipeData){
        adapter.setRecipeData(recipeData);
    }


}

