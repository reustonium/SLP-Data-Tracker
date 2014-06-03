package com.reustonium.slpdatatracker.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.reustonium.slpdatatracker.GoalActivity;
import com.reustonium.slpdatatracker.R;
import com.reustonium.slpdatatracker.adapters.GoalListAdapter;
import com.reustonium.slpdatatracker.models.Goal;
import com.reustonium.slpdatatracker.models.Patient;
import com.reustonium.slpdatatracker.models.PatientFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Andrew on 5/25/2014.
 */
public class PatientFragment extends Fragment {
    public static final String EXTRA_PATIENT_ID = "com.reustonium.slptracker.patient_id";
    public static final int REQUEST_GOAL = 1;
    public static final String EXTRA_GOAL = "com.reustonium.slptracker.goal";

    private Patient mPatient;
    private EditText mEditText;
    private TextView mTextView;
    private ListView mListView;
    private GoalListAdapter mGoalListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID patientID = (UUID)getArguments().getSerializable(EXTRA_PATIENT_ID);
        mPatient = PatientFactory.get(getActivity()).getPatient(patientID);
        mGoalListAdapter = new GoalListAdapter(getActivity(), android.R.layout.simple_list_item_1, mPatient.getGoals());
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

        mTextView = (TextView)v.findViewById(R.id.patient_updatedAtTextView);
        updateDate();

        mListView = (ListView)v.findViewById(R.id.patient_goal_listView);

        mListView.setAdapter(mGoalListAdapter);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_patient, menu);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if(requestCode==REQUEST_GOAL){
            Goal mGoal = (Goal)data.getSerializableExtra(EXTRA_GOAL);
            mPatient.setUpdatedAt(new Date());
            updateDate();
            mPatient.addGoal(mGoal);
            mGoalListAdapter.notifyDataSetChanged();
        }
    }

    private void updateDate(){
        mTextView.setText(mPatient.getPrettyUpdatedAt());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null) {
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            case R.id.menu_item_new_goal:
                Goal mGoal = new Goal();
                mPatient.addGoal(mGoal);
                Intent i = new Intent(getActivity(), GoalActivity.class);
                startActivityForResult(i, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //If a name isn't assigned, pop it out of the Patient List
        if (mPatient.getName() == null) {
            ArrayList<Patient> patients = PatientFactory.get(getActivity()).getPatients();
            patients.remove(mPatient);
        }
        PatientFactory.get(getActivity()).savePatients();
    }
}
