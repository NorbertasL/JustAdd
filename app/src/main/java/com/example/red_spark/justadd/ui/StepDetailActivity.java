package com.example.red_spark.justadd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.ui.fragments.StepDetailFragment;
import java.util.ArrayList;


/**
 * Created by Red_Spark on 11-Jul-17.
 */

public class StepDetailActivity extends AppCompatActivity {
    private StepDetailFragment mStepDetailFragment;
    private FragmentManager mFragmentManager;

    private ArrayList<String> stringList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String mJsonString;

        setContentView(R.layout.activity_step_detail);
//TODO set up this class
        Intent parentActivity = getIntent();
        if (parentActivity != null) {
            Bundle bundle = new Bundle();


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
