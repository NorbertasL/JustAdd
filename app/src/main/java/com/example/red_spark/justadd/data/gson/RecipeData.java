package com.example.red_spark.justadd.data.gson;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Red_Spark on 23-Jun-17.
 * JSON data converted into a java class via GSON
 */

/*
Making all the variable public for now,
I might encapsulate them in the future and
make getters/setters for them
 */

public class RecipeData {
    public String id;
    public String name;

    public List<Ingredients> ingredients = new ArrayList<>();
    public class Ingredients {
        public String quantity;
        public String measure;
        public String ingredient;
    }

    public ArrayList<Steps> steps = new ArrayList<>();
    public class Steps {
        public String id;
        public String shortDescription;
        public String description;
        public String videoURL;
        public String thumbnailURL;
    }

    public int servings;
    public String image;
}
