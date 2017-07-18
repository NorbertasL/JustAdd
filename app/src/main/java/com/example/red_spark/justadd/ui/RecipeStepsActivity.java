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
import com.example.red_spark.justadd.ui.fragments.StepDetailFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RecipeStepsActivity extends AppCompatActivity {

    private RecipeStepsFragment mStepsFragment;
    private StepDetailFragment mStepDetailFragment;
    private FragmentManager mFragmentManager;
    private boolean mBigScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String jsonString;
        setContentView(R.layout.activity_recipe_steps);

        //checking if we are on a big screen(Tablet)
        if(findViewById(R.id.big_screen_layout) != null){
            mBigScreen = true;
        }else{
            mBigScreen = false;
        }

        //getting intent data
        Intent parentActivity = getIntent();
        if (parentActivity != null && savedInstanceState == null) {
            if (parentActivity.hasExtra(Constants.RECIPE_DATA_BUNDLE_KEY)) {
                //getting the json string
                jsonString = parentActivity.getStringExtra(Constants.RECIPE_DATA_BUNDLE_KEY);

                //putting the json into a bundle
                Bundle bundle = new Bundle();
                bundle.putString(Constants.RECIPE_DATA_BUNDLE_KEY, jsonString);
                mFragmentManager = getSupportFragmentManager();
                mStepsFragment = new RecipeStepsFragment();


                //passing the json data into the fragment as a argument
                mStepsFragment.setArguments(bundle);
                if(mBigScreen){
                    mStepDetailFragment = new StepDetailFragment();
                    //Fragment transaction
                    mFragmentManager.beginTransaction()
                            //adding the fragment into the container of the activity
                            .add(R.id.steps_container, mStepsFragment)
                            .add(R.id.step_detail_container, mStepDetailFragment)
                            //Have to commit everything at the end to apply changes
                            .commit();
                }else{
                    //Fragment transaction
                    mFragmentManager.beginTransaction()
                            //adding the fragment into the container of the activity
                            .add(R.id.steps_container, mStepsFragment)
                            //Have to commit everything at the end to apply changes
                            .commit();
                }




            }
        }else {
            //TODO Error here
        }






    }
}
