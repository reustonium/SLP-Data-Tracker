package com.reustonium.slpdatatracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.reustonium.slpdatatracker.R;
import com.reustonium.slpdatatracker.models.Patient;

/**
 * Created by Andrew on 5/25/2014.
 */
public class PatientFragment extends Fragment {
    private Patient mPatient;
    private EditText mEditText;
    private Button mButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient, container, false);
        mEditText = (EditText)v.findViewById(R.id.patient_nameEditText);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mButton = (Button)v.findViewById(R.id.patient_updatedAtButton);
        mButton.setText(mPatient.getUpdatedAt().toString());

        return v;
    }
}
