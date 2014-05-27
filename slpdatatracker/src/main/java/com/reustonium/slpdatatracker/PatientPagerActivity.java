package com.reustonium.slpdatatracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.reustonium.slpdatatracker.fragments.PatientFragment;
import com.reustonium.slpdatatracker.models.Patient;
import com.reustonium.slpdatatracker.models.PatientFactory;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Andrew on 5/27/2014.
 */
public class PatientPagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Patient> mPatients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mPatients = PatientFactory.get(this).getPatients();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Patient p = mPatients.get(position);
                return PatientFragment.newInstance(p.getId());
            }

            @Override
            public int getCount() {
                return mPatients.size();
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Patient p = mPatients.get(position);
                if (p.getName() != null) {
                    setTitle(p.getName());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        UUID patientID = (UUID)getIntent().getSerializableExtra(PatientFragment.EXTRA_PATIENT_ID);
        for (int i=0; i< mPatients.size(); i++){
            if (mPatients.get(i).getId().equals(patientID)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
