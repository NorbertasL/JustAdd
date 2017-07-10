package com.example.red_spark.justadd.data.gson;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Red_Spark on 10-Jul-17.
 */

public class JsonConverter {

    //Converst and object into a json string
    public static String classToJsonString(Object classToConvert, Gson gsonObject){
        return gsonObject.toJson(classToConvert);
    }


    //Converts a object array list into ArrayList of Strings
    public static ArrayList<String> classToJsonStrings(ArrayList<RecipeData> objectArrayToConvert, Gson gsonObject){
        ArrayList<String> jsonStrings =  new ArrayList<>();
        for(RecipeData object: objectArrayToConvert){
            jsonStrings.add(classToJsonString(object, gsonObject));
        }
        return jsonStrings;
    }

    //Converts a jsonString into an object
    public static RecipeData jsonStringToObject(String jsonString, Gson gsonObject){
        return gsonObject.fromJson(jsonString, RecipeData.class);
    }

    //Comverts a ArrayList of jsonStrings into a ArrayList if type object
    public static ArrayList<RecipeData> jsonStringToObjects(ArrayList<String> jsonString, Gson gsonObject){
        ArrayList<RecipeData> object = new ArrayList<>();
        for(String string: jsonString){
            object.add(jsonStringToObject(string, gsonObject));
        }
        return object;
    }




}
