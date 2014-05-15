package com.reustonium.slpdatatracker;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.reustonium.slpdatatracker.models.Patient;
import com.reustonium.slpdatatracker.models.Session;

import java.util.ArrayList;

/**
 * Created by Andrew on 5/15/2014.
 */
public class SessionListFragment extends Fragment {
    ArrayList<Session> sessions = new ArrayList<Session>();
    ListView sessionListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO populate sessions data
        sessions.add(new Session(new Patient("andy")));
        sessions.add(new Session(new Patient("jimmy")));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  super.onCreateView(inflater, container, savedInstanceState);

        sessionListView = (ListView) rootView.findViewById(R.id.sessionList);
        sessionListView.setAdapter(new SessionArrayAdapter(getActivity().getApplicationContext(), R.layout.session_row_layout, sessions));
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        //TODO Save sessions data
    }

}
