package com.reustonium.slpdatatracker;

import android.support.v4.app.Fragment;

import com.reustonium.slpdatatracker.fragments.GoalFragment;

/**
 * Created by Andrew on 6/1/2014.
 */
public class GoalActivity extends SingleFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return new GoalFragment();
    }
}
