package com.example.red_spark.justadd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.data.Constants;
import com.example.red_spark.justadd.ui.fragments.StepDetailFragment;


/**
 * Created by Red_Spark on 11-Jul-17.
 */

public class StepDetailActivity extends AppCompatActivity {

    private StepDetailFragment mStepDetailFragment;
    private FragmentManager mFragmentManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_step_detail);
        Intent parentActivity = getIntent();
        //passing on the data as a bundle to the fragment
        if (parentActivity != null && savedInstanceState == null) {
            Bundle bundle = new Bundle();
            if(parentActivity.hasExtra(Constants.RECIPE_INGREDIANT_KEY)){
                bundle.putStringArrayList(Constants.RECIPE_INGREDIANT_KEY,
                        parentActivity.getStringArrayListExtra(Constants.RECIPE_INGREDIANT_KEY));
            }else if(parentActivity.hasExtra(Constants.RECIPE_STEP_KEY)){
                bundle.putString(Constants.RECIPE_STEP_KEY,
                        parentActivity.getStringExtra(Constants.RECIPE_STEP_KEY));
            }else{
                //TODO handle error here
            }


            mFragmentManager = getSupportFragmentManager();
            mStepDetailFragment = new StepDetailFragment();
            mStepDetailFragment.setArguments(bundle);

            mFragmentManager.beginTransaction()
                    .add(R.id.step_detail_container, mStepDetailFragment)
                    .commit();

        }else{
            //TODO error here
        }
    }
}
