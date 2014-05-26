package com.reustonium.slpdatatracker.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

import com.reustonium.slpdatatracker.models.Patient;
import com.reustonium.slpdatatracker.models.PatientFactory;

/**
 * Created by Andrew on 5/15/2014.
 */
public class PatientListFragment extends ListFragment {
    private ArrayList<Patient> mPatients = new ArrayList<Patient>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Patients");
        mPatients = PatientFactory.get(getActivity()).getPatients();
        ArrayAdapter<Patient> mAdapter = new ArrayAdapter<Patient>(getActivity(), android.R.layout.simple_list_item_1, mPatients);
        setListAdapter(mAdapter);
    }
}
