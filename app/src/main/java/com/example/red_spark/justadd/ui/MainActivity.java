package com.example.red_spark.justadd.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.ui.fragments.MainRecipeListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating fragment instance to display the recipe list
        MainRecipeListFragment listFragment =  new MainRecipeListFragment();

        //Creating an instance of a Fragment Manager
        //Fragment Manager lets you add/remove/update fragment in the activity
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Fragment transaction
        fragmentManager.beginTransaction()
                //adding the fragment into the container of the activity
                .add(R.id.list_container, listFragment)
                //Have to commit everything at the end to apply changes
                .commit();
    }
}
