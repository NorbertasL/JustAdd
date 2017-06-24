package com.example.red_spark.justadd.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.data.Constants;
import com.example.red_spark.justadd.data.GetRecipeInterface;
import com.example.red_spark.justadd.data.gson.RecipeData;
import com.example.red_spark.justadd.ui.fragments.MainRecipeListFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private MainRecipeListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating fragment instance to display the recipe list
        listFragment =  new MainRecipeListFragment();

        //Creating an instance of a Fragment Manager
        //Fragment Manager lets you add/remove/update fragment in the activity
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Fragment transaction
        fragmentManager.beginTransaction()
                //adding the fragment into the container of the activity
                .add(R.id.list_container, listFragment)
                //Have to commit everything at the end to apply changes
                .commit();

        //Creating retrofit builder instance
        Retrofit.Builder builder =  new Retrofit.Builder()
                //adding a base url that will be quarried
                .baseUrl(Constants.BASE_URL)
                //specifying what converter we will use
                //since we are getting data in json format well use GSON
                .addConverterFactory(GsonConverterFactory.create());

        //Creating the actual retrofit object
        Retrofit retrofit = builder.build();

        //Instance of the retrofit interface
        GetRecipeInterface inter = retrofit.create(GetRecipeInterface.class);

        //Calling the action on the interface(we only have one and it's a @GET request)
        //We also specify the variables of the url
        Call<List<RecipeData>> call = inter.recipeList(Constants.URL_YEAR, Constants.URL_MONTH);

        //executing the request asynchronously
        call.enqueue(new Callback<List<RecipeData>>() {

            //this is called if the request is successful
            @Override
            public void onResponse(Call<List<RecipeData>> call, Response<List<RecipeData>> response) {
                List<RecipeData> recipeData = response.body();
                //sending data to the adapter;
                listFragment.setRecipeData(recipeData);

            }

            //this is called if the request failed(no internet connection)
            @Override
            public void onFailure(Call<List<RecipeData>> call, Throwable t) {
                //TODO handle the error properly
                Toast.makeText(MainActivity.this, "UPS Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
