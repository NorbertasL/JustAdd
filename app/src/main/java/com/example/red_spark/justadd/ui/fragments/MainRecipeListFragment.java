package com.example.red_spark.justadd.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.data.Constants;
import com.example.red_spark.justadd.data.GetRecipeInterface;
import com.example.red_spark.justadd.ui.adapters.TestRetrofitAdapter;
import com.example.red_spark.justadd.data.gson.RecipeData;

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

public class MainRecipeListFragment extends Fragment {

    //Used by butterknife to set views to null
    private Unbinder unbinder;

    //Using butterknife to bind views
    //temp list view, will be replace with a recycleView
    @BindView(R.id.fragment_list_view) ListView listView;

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

                listView.setAdapter(new TestRetrofitAdapter(getActivity(), recipeData));
            }

            //this is called if the request failed(no internet connection)
            @Override
            public void onFailure(Call<List<RecipeData>> call, Throwable t) {
                //TODO handle the error properly
                Toast.makeText(getActivity(), "UPS Erro!", Toast.LENGTH_SHORT).show();
            }
        });



        //returning main/root view
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //setting the view to null, we have to do it for fragments.
        unbinder.unbind();
    }
}
