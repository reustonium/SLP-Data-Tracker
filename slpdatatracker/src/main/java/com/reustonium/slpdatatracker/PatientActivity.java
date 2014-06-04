package com.reustonium.slpdatatracker;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.reustonium.slpdatatracker.fragments.GoalFragment;
import com.reustonium.slpdatatracker.fragments.PatientFragment;
import com.reustonium.slpdatatracker.models.Goal;
import com.reustonium.slpdatatracker.models.Patient;
import com.reustonium.slpdatatracker.models.PatientFactory;

import java.util.UUID;

/**
 * Created by Andrew on 5/27/2014.
 */
public class PatientActivity extends Activity implements GoalFragment.OnSaveListener, PatientFragment.OnNewGoalListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getFragmentManager();

        UUID patientID = (UUID)getIntent().getSerializableExtra(PatientFragment.EXTRA_PATIENT_ID);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.container, PatientFragment.newInstance(patientID));
        transaction.commit();
    }

    @Override
    public void onGoalSaved(Goal mGoal) {
        UUID patientID = (UUID)getIntent().getSerializableExtra(PatientFragment.EXTRA_PATIENT_ID);

        Patient p = PatientFactory.get(this).getPatient(patientID);
        p.addGoal(mGoal);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container, PatientFragment.newInstance(patientID));
        transaction.commit();
    }

    @Override
    public void onNoGoalSaved() {
        UUID patientID = (UUID)getIntent().getSerializableExtra(PatientFragment.EXTRA_PATIENT_ID);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container, PatientFragment.newInstance(patientID));
        transaction.commit();
    }

    @Override
    public void OnAddGoal() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container, new GoalFragment());
        transaction.commit();
    }
}
