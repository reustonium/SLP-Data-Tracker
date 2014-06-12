package com.reustonium.slpdatatracker.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.reustonium.slpdatatracker.R;
import com.reustonium.slpdatatracker.models.Goal;
import com.reustonium.slpdatatracker.models.Patient;
import com.reustonium.slpdatatracker.models.PatientFactory;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnTextChanged;

/**
 * Created by Andrew on 5/25/2014.
 */
public class PatientFragment extends Fragment {
    public static final String EXTRA_PATIENT_ID = "com.reustonium.slptracker.patient_id";

    private Patient mPatient;
    private GoalListAdapter mGoalListAdapter;

    @InjectView(R.id.patient_nameEditText)
    EditText mEditText;

    @InjectView(R.id.patient_updatedAtTextView)
    TextView mTextView;

    @InjectView(R.id.patient_goal_listView)
    ListView mListView;

    private OnNewGoalListener goalListener;

    public interface OnNewGoalListener{
        public void OnAddGoal();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            goalListener = (OnNewGoalListener)activity;
        } catch (ClassCastException ex){
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID patientID;
        if (savedInstanceState != null) {
            patientID = (UUID)savedInstanceState.getSerializable(EXTRA_PATIENT_ID);
        } else {
            patientID = (UUID)getArguments().getSerializable(EXTRA_PATIENT_ID);
        }
        setHasOptionsMenu(true);
        mPatient = PatientFactory.get(getActivity()).getPatient(patientID);
        mGoalListAdapter = new GoalListAdapter(getActivity(), R.layout.list_item_goals, mPatient.getGoals());
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static PatientFragment newInstance(UUID patientID){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_PATIENT_ID, patientID);

        PatientFragment patientFragment = new PatientFragment();
        patientFragment.setArguments(args);
        return patientFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient, container, false);

        ButterKnife.inject(this, v);

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
        mListView.setAdapter(mGoalListAdapter);
        updateDate();

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_patient, menu);
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
                goalListener.OnAddGoal();
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(EXTRA_PATIENT_ID, mPatient.getId());
    }

    public class GoalListAdapter extends ArrayAdapter<Goal>{
        public GoalListAdapter(Context context, int resource, ArrayList<Goal> goals) {
            super(context, resource, goals);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_goals, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }

            Goal g = getItem(position);

            holder.goalName.setText(g.getGoalName());
            holder.goalDate.setText(g.getPrettyDate());

            //TODO move this logic to Goal.java
            if(g.getNumQuestion() == 0){
                holder.goalCorrect.setText("0%");
                holder.goalCue.setText("0%");
            } else {
                holder.goalCorrect.setText(String.format("%d%%", Math.round(g.getNumIndependent() * 100/g.getNumQuestion())));
                holder.goalCue.setText(String.format("%d%%", Math.round((g.getNumIndependent()+g.getNumCue())* 100 /g.getNumQuestion())));
            }

            return convertView;
        }
    }

    static class ViewHolder{
        @InjectView(R.id.list_item_goal_name) TextView goalName;
        @InjectView(R.id.list_item_goal_correct) TextView goalCorrect;
        @InjectView(R.id.list_item_goal_cue) TextView goalCue;
        @InjectView(R.id.list_item_goal_date) TextView goalDate;

        public ViewHolder(View v){
            ButterKnife.inject(this, v);
        }
    }
}