package com.example.red_spark.justadd.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.data.Constants;
import com.example.red_spark.justadd.data.GetRecipeInterface;
import com.example.red_spark.justadd.ui.adapters.MainRecipeListFragmentAdapter;
import com.example.red_spark.justadd.data.gson.RecipeData;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This fragment will display a list of recipes in a recycle view
 * Using butterknife to reduce boilerplate code;
 * Using Retrofit for the network request
 * Using GSON to format the data
 */

public class MainRecipeListFragment extends Fragment
        implements MainRecipeListFragmentAdapter.AdapterOnClickHandler{

    private RecyclerView.LayoutManager layoutManager;
    private MainRecipeListFragmentAdapter adapter;

    //Used by butterknife to set views to null
    private Unbinder unbinder;

    //Using butterknife to bind views
    @BindView(R.id.recyclerView_recipe_list) RecyclerView recyclerView;
    @BindView(R.id.error_message_display) TextView errorView;
    @BindView(R.id.loading_indicator) ProgressBar progressBarView;


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


        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //Creating the adapter
        adapter =  new MainRecipeListFragmentAdapter(this);

        //binding adapter to recyclerView
        recyclerView.setAdapter(adapter);





        //returning main/root view
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //setting the view to null, we have to do it for fragments.
        unbinder.unbind();
    }

    @Override
    public void onClick(RecipeData recipeData) {
        Toast.makeText(getActivity(), "Clicked on"+recipeData.name, Toast.LENGTH_SHORT).show();
    }
    public void setRecipeData(List<RecipeData> recipeData){
        adapter.setRecipeData(recipeData);
    }
}
