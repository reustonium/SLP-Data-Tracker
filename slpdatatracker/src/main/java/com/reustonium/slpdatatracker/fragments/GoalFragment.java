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

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class GoalFragment extends Fragment {

    String goalName;

    int numQuestions, numCorrect, numCue;

    @InjectView(R.id.fragment_goal_nameSpinner) Spinner goalName_Spinner;
    @OnItemSelected(R.id.fragment_goal_nameSpinner) void onItemSelected(AdapterView<?> adapterView, int i){
        goalName = adapterView.getItemAtPosition(i).toString();
    }
    @InjectView(R.id.tracker_txt_success) TextView tv_success;
    @InjectView(R.id.tracker_txt_success_data) TextView tv_successData;
    @InjectView(R.id.tracker_txt_cue) TextView tv_cue;
    @InjectView(R.id.tracker_txt_cue_data) TextView tv_cueData;
    @InjectView(R.id.tracker_btn_correct) Button btn_correct;
    @InjectView(R.id.tracker_btn_cue) Button btn_cue;
    @InjectView(R.id.tracker_btn_wrong) Button btn_wrong;
    @OnClick(R.id.tracker_btn_correct) void onCorrectClick(){
        numQuestions++;
        numCorrect++;
        updateTextViews();
    }
    @OnClick(R.id.tracker_btn_cue) void onCueClick(){
        numQuestions++;
        numCue++;
        updateTextViews();
    }
    @OnClick(R.id.tracker_btn_wrong) void onWrongClick(){
        numQuestions++;
        updateTextViews();
    }

    Goal mGoal;
    OnSaveListener mSaveListener;

    public interface OnSaveListener{
        public void onGoalSaved(Goal mGoal);
        public void onGoalNotSaved();
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

        numQuestions = 0;
        numCorrect = 0;
        numCue = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_goal, container, false);
        ButterKnife.inject(this, rootView);

        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.goal_names, android.R.layout.simple_spinner_dropdown_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        goalName_Spinner.setAdapter(mAdapter);

        updateTextViews();
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
                mSaveListener.onGoalNotSaved();
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
