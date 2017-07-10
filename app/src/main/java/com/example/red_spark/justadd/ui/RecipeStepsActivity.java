package com.example.red_spark.justadd.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.data.Constants;
import com.example.red_spark.justadd.data.gson.JsonConverter;
import com.example.red_spark.justadd.data.gson.RecipeData;
import com.example.red_spark.justadd.ui.adapters.RecipeStepsAdapter;
import com.example.red_spark.justadd.ui.fragments.RecipeStepsFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RecipeStepsActivity extends AppCompatActivity {

    private RecipeData mRecipeData;
    private RecipeStepsFragment mStepsFragment;
    private FragmentManager mFragmentManager;

    private Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String jsonString;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        mGson = new Gson();

        Intent parentActivity = getIntent();
        if (parentActivity != null) {
            if (parentActivity.hasExtra(Constants.RECIPE_DATA_BUNDLE_KEY)) {
                //TODO ingrediasnt retun null(it was not in the main activity i chjecked.
                jsonString = parentActivity.getStringExtra(Constants.RECIPE_DATA_BUNDLE_KEY);

                mRecipeData = JsonConverter.jsonStringToObject(jsonString, mGson);

                Bundle bundle = new Bundle();
                bundle.putString(Constants.RECIPE_DATA_BUNDLE_KEY, jsonString);
                mFragmentManager = getSupportFragmentManager();
                mStepsFragment = new RecipeStepsFragment();
                mStepsFragment.setArguments(bundle);

                //Fragment transaction
                mFragmentManager.beginTransaction()
                        //adding the fragment into the container of the activity
                        .add(R.id.steps_container, mStepsFragment)
                        //Have to commit everything at the end to apply changes
                        .commit();
            }
        }else {
            //TODO Error here
        }






    }
}
