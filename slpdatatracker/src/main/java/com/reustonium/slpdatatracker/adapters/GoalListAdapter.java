package com.reustonium.slpdatatracker.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import com.reustonium.slpdatatracker.models.Goal;

/**
 * Created by Andrew on 5/30/2014.
 */
public class GoalListAdapter extends ArrayAdapter<Goal> {
    public GoalListAdapter(Context context, int resource, ArrayList<Goal> objects) {
        super(context, resource, objects);
    }
}
