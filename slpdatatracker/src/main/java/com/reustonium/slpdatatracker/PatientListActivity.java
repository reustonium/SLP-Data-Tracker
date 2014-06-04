package com.reustonium.slpdatatracker;


import android.app.Fragment;

import com.reustonium.slpdatatracker.fragments.PatientListFragment;

/**
 * Created by Andrew on 5/25/2014.
 */
public class PatientListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return new PatientListFragment();
    }
}
