package com.example.red_spark.justadd.ui.fragments;




import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.data.Constants;
import com.example.red_spark.justadd.data.gson.JsonConverter;
import com.example.red_spark.justadd.data.gson.RecipeData;
import com.example.red_spark.justadd.ui.adapters.RecipeStepsAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment that contains the list of all the steps
 * and other information
 */
public class RecipeStepsFragment extends Fragment
        implements RecipeStepsAdapter.AdapterOnClickHandler {




    //instance save keys
    private final static String LAYOUT_KEY = "layout_key";
    private final static String DATA_KEY = "data_key";

    private RecyclerView.LayoutManager layoutManager;
    private RecipeStepsAdapter adapter;
    private RecipeData mRecipeData;

    String mJsonStrings;

    List<String> stepList;

    //Used by butterknife to set views to null
    private Unbinder unbinder;
    @BindView(R.id.recyclerView_step_list)
    RecyclerView recyclerView;
    @BindView(R.id.error_message_display)
    TextView textView;


    //interface used to talk with the activity
    OnRecipeItemSelectedListener mCallback;
    public interface OnRecipeItemSelectedListener{
        void onItemSelected(String jsonString);
        void onItemSelected(ArrayList<String> jsonString);
    }
    //Making sure the interface id implemented
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnRecipeItemSelectedListener){
            mCallback = (OnRecipeItemSelectedListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    + "must implement OnRecipeItemSelectedListener");
        }
    }

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

        if(savedInstanceState != null){
            //retrieving the layoutManager that was saved
            layoutManager.onRestoreInstanceState(savedInstanceState.getParcelable(LAYOUT_KEY));

            //retrieving the saved json string
            mJsonStrings = savedInstanceState.getString(DATA_KEY);
        }else {
            //Retrieve the RecipeData class jason that we passed in as a argument from the activity
            mJsonStrings = getArguments().getString(Constants.RECIPE_DATA_BUNDLE_KEY);
        }

        //converting the jason back into a class
        mRecipeData = JsonConverter.jsonStringToObject(mJsonStrings, new Gson());

        //creating a list of steps that will be displayed
        stepList = makeStepList(mRecipeData);

        //passing in the steps into the adapter
        adapter = new RecipeStepsAdapter(this, stepList);

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
    public void onClick(int index) {
        //index of = means it is the ingredient list
        if(index == 0){
           ArrayList<String> jsonString = new ArrayList<>();
            //converting the object into a json
            for(RecipeData.Ingredients ingredient: mRecipeData.ingredients){
                jsonString.add(JsonConverter.classToJsonString(ingredient, new Gson()));
            }

            mCallback.onItemSelected(jsonString);


        }else{
            String jsonString;
            //converting object into jason string
            //do index -1 to align the indexing
            //remember index 0 was ingredients
            jsonString = JsonConverter.classToJsonString(mRecipeData.steps.get(index -1), new Gson());

            mCallback.onItemSelected(jsonString);

        }
    }


    //Method to generate a list
    public List<String> makeStepList(RecipeData data){
        List<String> stepString = new ArrayList<>();

        //first step is always the ingredients
        //have to use getResources() because we need the actual string and not just a ref to it.
        stepString.add(getResources().getString(R.string.ingredients));


        //convert the rest of the steps into strings
        for (RecipeData.Steps step: data.steps){
            stepString.add(step.shortDescription);
        }

        return stepString;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //saving the layout manager state
        outState.putParcelable(LAYOUT_KEY, layoutManager.onSaveInstanceState());
        //saving the json string too
        outState.putString(DATA_KEY, mJsonStrings);

    }
}
