package com.example.red_spark.justadd.data;

import com.example.red_spark.justadd.data.gson.RecipeData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Red_Spark on 23-Jun-17.
 * Part of Retrofit implementation
 */

public interface GetRecipeInterface {
    /**n
     *default link is "/topher/2017/May/59121517_baking/baking.json"
     *but using @Path for future reference and to test it out
     * Make sure to wrap the return List in a Call class
     * This makes it do network requests asynchronously
     * If you don't do this it will make your app freeze while the request is being possessed
     * and in later android version it will crash the app
    */
    @GET("/topher/{year}/{month}/59121517_baking/baking.json")
    Call<ArrayList<RecipeData>> recipeList(@Path("year")String year, @Path("month") String month);

}
