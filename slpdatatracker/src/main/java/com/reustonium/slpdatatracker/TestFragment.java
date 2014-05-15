package com.reustonium.slpdatatracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TestFragment extends Fragment {

    int numQuestions;
    int numCorrect;
    int numCue;

    TextView tv_success;
    TextView tv_successData;
    TextView tv_cue;
    TextView tv_cueData;

    Button btn_correct;
    Button btn_cue;
    Button btn_wrong;

    public TestFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tracker, container, false);

        numQuestions = 0;
        numCorrect = 0;
        numCue = 0;

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
    public void onResume() {
        super.onResume();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("x", "menu item selected!");
        int id = item.getItemId();
        switch(id){
            case R.id.action_reset:
                resetCounter();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetCounter() {
        numQuestions = 0;
        numCorrect = 0;
        numCue = 0;
        updateTextViews();
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
