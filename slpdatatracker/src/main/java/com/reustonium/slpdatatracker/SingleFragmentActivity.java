package com.reustonium.slpdatatracker;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public abstract class SingleFragmentActivity extends Activity {

    protected abstract Fragment getFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, getFragment())
                    .commit();
        }
    }
}
