package com.reustonium.slpdatatracker;

import android.support.v4.app.Fragment;

import com.reustonium.slpdatatracker.fragments.PatientFragment;

/**
 * Created by Andrew on 5/25/2014.
 */
public class PatientActivity extends SingleFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return new PatientFragment();
    }
}
