package com.example.red_spark.justadd.data.gson;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Red_Spark on 23-Jun-17.
 * JSON data converted into a java class via GSON
 * Using Android Parcelable code generator by Michal Charmas plugin
 * to generating the boiler-plate code for making the class Parcelable
 */

/*
Making all the variable public for now,
I might encapsulate them in the future and
make getters/setters for them
 */

//we are making this class parcelable so we can save it in a bundle
public class RecipeData implements Parcelable {
    public String id;
    public String name;
    public List<Ingredients> ingredients = new ArrayList<>();
    public class Ingredients implements Parcelable {
        public String quantity;
        public String measure;
        public String ingredient;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.quantity);
            dest.writeString(this.measure);
            dest.writeString(this.ingredient);
        }

        public Ingredients() {
        }

        protected Ingredients(Parcel in) {
            this.quantity = in.readString();
            this.measure = in.readString();
            this.ingredient = in.readString();
        }

        public  final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
            @Override
            public Ingredients createFromParcel(Parcel source) {
                return new Ingredients(source);
            }

            @Override
            public Ingredients[] newArray(int size) {
                return new Ingredients[size];
            }
        };
    }
    List<Steps> steps = new ArrayList<>();
    public class Steps implements Parcelable {
        public String id;
        public String shortDescription;
        public String description;
        public String videoURL;
        public String thumbnailURL;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.shortDescription);
            dest.writeString(this.description);
            dest.writeString(this.videoURL);
            dest.writeString(this.thumbnailURL);
        }

        public Steps() {
        }

        protected Steps(Parcel in) {
            this.id = in.readString();
            this.shortDescription = in.readString();
            this.description = in.readString();
            this.videoURL = in.readString();
            this.thumbnailURL = in.readString();
        }

        public  final Creator<Steps> CREATOR = new Creator<Steps>() {
            @Override
            public Steps createFromParcel(Parcel source) {
                return new Steps(source);
            }

            @Override
            public Steps[] newArray(int size) {
                return new Steps[size];
            }
        };
    }
    public int servings = 0;
    public String image;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeList(this.ingredients);
        dest.writeList(this.steps);
        dest.writeInt(this.servings);
        dest.writeString(this.image);
    }

    public RecipeData() {
    }

    protected RecipeData(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.ingredients = new ArrayList<Ingredients>();
        in.readList(this.ingredients, Ingredients.class.getClassLoader());
        this.steps = new ArrayList<Steps>();
        in.readList(this.steps, Steps.class.getClassLoader());
        this.servings = in.readInt();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<RecipeData> CREATOR = new Parcelable.Creator<RecipeData>() {
        @Override
        public RecipeData createFromParcel(Parcel source) {
            return new RecipeData(source);
        }

        @Override
        public RecipeData[] newArray(int size) {
            return new RecipeData[size];
        }
    };
}
