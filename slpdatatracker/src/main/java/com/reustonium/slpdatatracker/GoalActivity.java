package com.reustonium.slpdatatracker;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.reustonium.slpdatatracker.fragments.GoalFragment;
import com.reustonium.slpdatatracker.models.Goal;

/**
 * Created by Andrew on 6/1/2014.
 */
public class GoalActivity extends SingleFragmentActivity implements GoalFragment.OnSaveListener{
    @Override
    protected Fragment getFragment() {
        return new GoalFragment();
    }


    @Override
    public void onGoalSaved(Goal mGoal) {
        Log.v("!!!", "Goal: " + mGoal.getGoalName());
    }
}
