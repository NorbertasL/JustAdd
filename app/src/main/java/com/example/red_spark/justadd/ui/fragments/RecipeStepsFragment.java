package com.example.red_spark.justadd.ui.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.data.Constants;
import com.example.red_spark.justadd.data.gson.JsonConverter;
import com.example.red_spark.justadd.data.gson.RecipeData;
import com.example.red_spark.justadd.ui.adapters.RecipeStepsAdapter;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepsFragment extends Fragment
        implements RecipeStepsAdapter.AdapterOnClickHandler {

    private RecyclerView.LayoutManager layoutManager;
    private RecipeStepsAdapter adapter;
    private RecipeData mRecipeData;

    //Used by butterknife to set views to null
    private Unbinder unbinder;
    @BindView(R.id.recyclerView_step_list)
    RecyclerView recyclerView;
    @BindView(R.id.error_message_display)
    TextView textView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
        //butterknife set up
        unbinder = ButterKnife.bind(this, rootView);

        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        String jsonStrings = getArguments().getString(Constants.RECIPE_DATA_BUNDLE_KEY);

        mRecipeData = JsonConverter.jsonStringToObject(jsonStrings, new Gson());
        //Creating adapater and passing in a list of steps
        adapter = new RecipeStepsAdapter(this, mRecipeData.steps);

        //binding adapter to recyclerView
        recyclerView.setAdapter(adapter);

        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //setting the view to null, we have to do it for fragments.
        //this is requested by butterknife
        unbinder.unbind();
    }

    @Override
    public void onClick(RecipeData.Steps step) {
    //TODO handle clicking
        Toast.makeText(getActivity(), "Clicked on"+step.shortDescription, Toast.LENGTH_SHORT).show();
    }
}
