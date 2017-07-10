package com.example.red_spark.justadd.ui.adapters;

import com.bumptech.glide.Glide;

import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.data.gson.RecipeData;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Red_Spark on 23-Jun-17.
 * Adapter for the recycle view
 *
 */

public class MainRecipeListFragmentAdapter
        extends RecyclerView.Adapter<MainRecipeListFragmentAdapter.AdapterViewHolder> {

    private AdapterOnClickHandler clickHandler;

    //stores all the recepie objects
    private List<RecipeData> recipeData;

    //used to keep track of what sub-view we have expanded
    private List<View> lastExpandedViews = new ArrayList<>();
    private View curretExpandedView;

    Context context;

    //Interface for onClick
    public interface AdapterOnClickHandler {
        void onClick(RecipeData recipeData);
    }

    public MainRecipeListFragmentAdapter(AdapterOnClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutID = R.layout.recipe_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutID, parent, shouldAttachToParentImmediately);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        //setting the data to the text view
        holder.listItem.setText(recipeData.get(position).name);
    }

    @Override
    public int getItemCount() {
        if (recipeData == null || recipeData.isEmpty()) {
            return 0;
        }
        return recipeData.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView listItem;


        public AdapterViewHolder(View itemView) {
            super(itemView);
            listItem = (TextView)  itemView.findViewById(R.id.recipe_list_item_name);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            if(curretExpandedView == v){
                //clicked on a expanded view so we open up a new activity with all the data
                clickHandler.onClick(recipeData.get(adapterPosition));
            }else{
                expandView(v, recipeData.get(adapterPosition));
            }


        }
    }
    public void setRecipeData(List<RecipeData> recipeData) {
        this.recipeData = recipeData;
        notifyDataSetChanged();
    }
    public void expandView(View v,RecipeData data){
        //we get the reference to the view that need to be shown
        ImageView img = (ImageView) v.findViewById(R.id.iv_thumbnail);
        TextView servings = (TextView) v.findViewById(R.id.recipe_list_item_servings);
        //check of we have expanded anything before
        if(!lastExpandedViews.isEmpty()) {
            //if so we remove them
            for(View view : lastExpandedViews){
                view.setVisibility(View.GONE);
            }
            //we also clean the list
            lastExpandedViews.clear();
        }
        String imgURL = data.image;
        //checking if we have an image url
        //if url is empty, we just use a spot-holder image
        if(imgURL.isEmpty()){
            img.setImageResource(R.drawable.no_image);
        }else{
            //Using Glide to handle the image downloading
            //TODO this image was for a test, check the json for actual image link
            Glide.with(context).load("http://goo.gl/gEgYUd").into(img);



        }
        //have to use getResources() because we need the actual string and not just a ref to it.
        servings.setText(context.getResources().getString(R.string.servings_count)+data.servings);

        //we add all the views into the list to keep track of them for next time
        lastExpandedViews.add(img);
        lastExpandedViews.add(servings);
        //make the views visible
        for (View view: lastExpandedViews){
            view.setVisibility(View.VISIBLE);
        }

        //we keep track on the view so we know when we clicked on it again
        curretExpandedView = v;
    }

}
