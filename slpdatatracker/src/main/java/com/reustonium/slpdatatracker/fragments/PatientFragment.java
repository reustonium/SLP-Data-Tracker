package com.reustonium.slpdatatracker.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.reustonium.slpdatatracker.R;
import com.reustonium.slpdatatracker.models.Patient;
import com.reustonium.slpdatatracker.models.PatientFactory;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Andrew on 5/25/2014.
 */
public class PatientFragment extends Fragment {
    public static final String EXTRA_PATIENT_ID = "com.reustonium.slptracker.patient_id";
    public static final String DATE_DIALOG = "com.reustonium.slptracker.date";
    public static final int REQUEST_DATE = 0;

    private Patient mPatient;
    private EditText mEditText;
    private Button mButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID patientID = (UUID)getArguments().getSerializable(EXTRA_PATIENT_ID);
        mPatient = PatientFactory.get(getActivity()).getPatient(patientID);
    }

    public static PatientFragment newInstance(UUID patientID){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_PATIENT_ID, patientID);

        PatientFragment patientFragment = new PatientFragment();
        patientFragment.setArguments(args);
        return patientFragment;
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (NavUtils.getParentActivityName(getActivity()) != null) {
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        mEditText = (EditText)v.findViewById(R.id.patient_nameEditText);
        mEditText.setText(mPatient.getName());
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                mPatient.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mButton = (Button)v.findViewById(R.id.patient_updatedAtButton);
        updateDate();
        mButton.setText(mPatient.getUpdatedAt().toString());
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mPatient.getUpdatedAt());
                dialog.setTargetFragment(PatientFragment.this, REQUEST_DATE);
                dialog.show(fm, DATE_DIALOG);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if(requestCode==REQUEST_DATE){
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mPatient.setUpdatedAt(date);
            updateDate();
        }
    }

    private void updateDate(){
        mButton.setText(mPatient.getUpdatedAt().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null) {
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
