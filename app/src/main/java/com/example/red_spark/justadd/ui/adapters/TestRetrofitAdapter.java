package com.example.red_spark.justadd.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.red_spark.justadd.R;
import com.example.red_spark.justadd.data.gson.RecipeData;

import java.util.List;


/**
 * Created by norman on 12/26/16.
 * This is a temporary adapter to test out Retrofit and GSON
 */

public class TestRetrofitAdapter extends ArrayAdapter<RecipeData> {

    private Context context;
    private List<RecipeData> values;

    public TestRetrofitAdapter(Context context, List<RecipeData> values) {
        super(context, R.layout.list_item_retrofit_test, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_retrofit_test, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.list_item_pagination_text);

        RecipeData item = values.get(position);
        String message = item.name;
        textView.setText(message);

        return row;
    }
}