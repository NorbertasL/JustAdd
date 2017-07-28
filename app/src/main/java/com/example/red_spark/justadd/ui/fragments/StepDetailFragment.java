package com.example.red_spark.justadd.ui.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.data.Constants;
import com.example.red_spark.justadd.data.gson.JsonConverter;
import com.example.red_spark.justadd.data.gson.RecipeData;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 *Fragment that contain a detain description of the step
 * That could include a video and/or a text description
 */
public class StepDetailFragment extends Fragment {

    private final static String TAG = StepDetailFragment.class.getSimpleName();

    private boolean havePlayer = false;

    private Unbinder unbinder;
    @BindView(R.id.error_message_display)
    TextView errotMessage;
    @BindView(R.id.ingredient_list_view)
    ListView ingredientListView;
    @BindView(R.id.video_view)
    SimpleExoPlayerView videoView;
    @BindView(R.id.disc_text_view)
    TextView discTextView;

    com.google.android.exoplayer2.SimpleExoPlayer mExoPlayer;

    public StepDetailFragment() {
        // Required empty public constructor
    }

//TODO set up StepDetailsFragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);
        //butterknife
        unbinder = ButterKnife.bind(this, rootView);

        //if getArguments() == null that means we are on a big screen
        if(getArguments() == null){
            videoView.setVisibility(View.GONE);

            //TODO move the string
            discTextView.setText("Select a step");


        } else if(getArguments().containsKey(Constants.RECIPE_INGREDIANT_KEY)) {
            //since this only contains a list of ingredients
            //we set the unneeded view visibility to GONE
            videoView.setVisibility(View.GONE);
            discTextView.setVisibility(View.GONE);
            ArrayList<RecipeData.Ingredients> ingredients = JsonConverter
                   .jsonIngToObjects(getArguments()
                           .getStringArrayList(Constants.RECIPE_INGREDIANT_KEY), new Gson());

            List<String> ingList = genList(ingredients);

            //using an array adapter to populate the list view
            ArrayAdapter<String> arrayAdapter =  new ArrayAdapter<>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    ingList);

            ingredientListView.setAdapter(arrayAdapter);


        }else if(getArguments().containsKey(Constants.RECIPE_STEP_KEY)) {
            //will not use the list view since that's used for the ingeriants
            ingredientListView.setVisibility(View.GONE);

            String temp = getArguments().getString(Constants.RECIPE_STEP_KEY);

            RecipeData.Steps step = JsonConverter.jsonIntoStepObject(temp, new Gson());

            //checking if there is a video url
            if(!step.videoURL.isEmpty()){
                //TODO check if url is valid, ending in .mp4
                Uri uri =Uri.parse(step.videoURL);
                initPlayer(uri);
                havePlayer = true;
                //videoView.setText(step.videoURL);
            }else{
                videoView.setVisibility(View.GONE);
            }
            //chaking if there is a discription of the step
            if(!step.description.isEmpty()){
                discTextView.setText(step.description);
            }



        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //setting the view to null, we have to do it for fragments.
        //this is requested by butterknife
        unbinder.unbind();

        //releaseing media resouces
        if(havePlayer)
            releasePlayer();
    }
    public List<String> genList(ArrayList<RecipeData.Ingredients> ing){
        List<String> returnList = new ArrayList<>();
        for(RecipeData.Ingredients ingredient: ing){
            //formatting the string
            returnList.add(ingredient.quantity
                    + " " + ingredient.measure
                    + " of " + ingredient.ingredient);
        }
        return returnList;
    }
    private void initPlayer(Uri mediaUri){
        if(mExoPlayer == null){

            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity()
                    , new DefaultTrackSelector());
            videoView.setPlayer(mExoPlayer);

            MediaSource mediaSource = new  ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(getActivity(), "Test"), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
            Log.v(TAG, ""+mediaUri);
        }
    }

    private void releasePlayer(){
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }
}
