package com.reustonium.slpdatatracker.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.reustonium.slpdatatracker.R;
import com.reustonium.slpdatatracker.models.Goal;

public class GoalFragment extends Fragment {

    String goalName;

    int numQuestions;
    int numCorrect;
    int numCue;

    Spinner goalName_Spinner;
    TextView tv_success;
    TextView tv_successData;
    TextView tv_cue;
    TextView tv_cueData;

    Button btn_correct;
    Button btn_cue;
    Button btn_wrong;

    Goal mGoal;
    OnSaveListener mSaveListener;

    public interface OnSaveListener{
        public void onGoalSaved(Goal mGoal);
        public void onNoGoalSaved();
    }

    public GoalFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mSaveListener = (OnSaveListener)activity;
        } catch (ClassCastException ex){
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_goal, container, false);

        numQuestions = 0;
        numCorrect = 0;
        numCue = 0;

        goalName_Spinner = (Spinner)rootView.findViewById(R.id.fragment_goal_nameSpinner);
        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.goal_names, android.R.layout.simple_spinner_dropdown_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        goalName_Spinner.setAdapter(mAdapter);
        goalName_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                goalName = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tv_success = (TextView)rootView.findViewById(R.id.tracker_txt_success);
        tv_successData = (TextView)rootView.findViewById(R.id.tracker_txt_success_data);
        tv_cue = (TextView)rootView.findViewById(R.id.tracker_txt_cue);
        tv_cueData = (TextView)rootView.findViewById(R.id.tracker_txt_cue_data);

        updateTextViews();

        btn_correct = (Button)rootView.findViewById(R.id.tracker_btn_correct);
        btn_cue = (Button)rootView.findViewById(R.id.tracker_btn_cue);
        btn_wrong = (Button)rootView.findViewById(R.id.tracker_btn_wrong);

        btn_correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numQuestions++;
                numCorrect++;
                updateTextViews();
            }
        });

        btn_cue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numQuestions++;
                numCue++;
                updateTextViews();
            }
        });

        btn_wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numQuestions++;
                updateTextViews();
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_goal, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.menu_item_save_goal:
                mGoal = new Goal();
                mGoal.setGoalName(goalName);
                mGoal.setNumQuestion(numQuestions);
                mGoal.setNumIndependent(numCorrect);
                mGoal.setNumCue(numCue);
                mSaveListener.onGoalSaved(mGoal);
            case R.id.menu_item_delete_goal:
                mSaveListener.onNoGoalSaved();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateTextViews(){
        if(numQuestions == 0){
            tv_success.setText("0%");
            tv_successData.setText("0/0");
            tv_cue.setText("0%");
            tv_cueData.setText("0/0");
        } else {

            tv_success.setText(String.format("%d%%", Math.round(numCorrect * 100/numQuestions)));
            tv_successData.setText(String.format("%d / %d", numCorrect, numQuestions));
            tv_cue.setText(String.format("%d%%", Math.round((numCorrect+numCue)* 100 /numQuestions)));
            tv_cueData.setText(String.format("%d / %d", (numCorrect+numCue), numQuestions));
        }
    }

}
