package com.example.red_spark.justadd.ui.adapters;

import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.data.gson.RecipeData;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Red_Spark on 23-Jun-17.
 * Adapter for the recycle view
 *
 * Why is it so messy to implement onClick to a RecyclerView?
 */

public class MainRecipeListFragmentAdapter
        extends RecyclerView.Adapter<MainRecipeListFragmentAdapter.AdapterViewHolder> {

    private AdapterOnClickHandler clickHandler;
    private List<RecipeData> recipeData;

    //Interface for onClick
    public interface AdapterOnClickHandler {
        void onClick(RecipeData recipeData);
    }

    public MainRecipeListFragmentAdapter(AdapterOnClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
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
            listItem = (TextView)  itemView.findViewById(R.id.recipe_list_item);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            clickHandler.onClick(recipeData.get(adapterPosition));
        }
    }
    public void setRecipeData(List<RecipeData> recipeData) {
        this.recipeData = recipeData;
        notifyDataSetChanged();
    }

}
