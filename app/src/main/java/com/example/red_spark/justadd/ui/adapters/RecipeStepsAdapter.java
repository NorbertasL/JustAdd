package com.example.red_spark.justadd.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.data.gson.RecipeData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Red_Spark on 10-Jul-17.
 */

public class RecipeStepsAdapter
        extends RecyclerView.Adapter<RecipeStepsAdapter.AdapterViewHolder>{
    private AdapterOnClickHandler mClickHandler;
    private Context mContext;
    private ArrayList<RecipeData.Steps> mSteps;

    //Interface for onClick
    public interface AdapterOnClickHandler {
        void onClick(RecipeData.Steps step);
    }
    public RecipeStepsAdapter(AdapterOnClickHandler clickHandler, ArrayList<RecipeData.Steps> steps ){
        mSteps = steps;
        mClickHandler =  clickHandler;
    }
    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int layoutID = R.layout.recipe_step_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutID, parent, shouldAttachToParentImmediately);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeStepsAdapter.AdapterViewHolder holder, int position) {
        holder.mListItem.setText(mSteps.get(position).shortDescription);
    }

    @Override
    public int getItemCount() {
        if(mSteps == null || mSteps.isEmpty()){
            return 0;
        }
        Log.v("Test", ""+mSteps.size());
        return mSteps.size();
    }
    public class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mListItem;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            mListItem = (TextView) itemView.findViewById(R.id.step_disc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(mSteps.get(adapterPosition));

        }
    }
}
