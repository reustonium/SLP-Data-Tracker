package com.reustonium.slpdatatracker.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.reustonium.slpdatatracker.PatientArrayAdapter;
import com.reustonium.slpdatatracker.R;
import com.reustonium.slpdatatracker.models.Patient;

import java.util.ArrayList;

/**
 * Created by Andrew on 5/15/2014.
 */
public class PatientListFragment extends Fragment {
    ArrayList<Patient> patients = new ArrayList<Patient>();
    ListView patientListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        //TODO populate sessions data
        patients.add(new Patient("ADR"));
        patients.add(new Patient("LCR"));
        patients.add(new Patient("AER"));
        patients.add(new Patient("WCR"));
        patients.add(new Patient("ARS"));
        patients.add(new Patient("MEK"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_session_list, container, false);

        patientListView = (ListView) rootView.findViewById(R.id.patientList);
        patientListView.setAdapter(new PatientArrayAdapter(getActivity().getApplicationContext(), R.layout.patient_row_layout, patients));
        patientListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                final int mPos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Delete Patient?")
                        .setCancelable(true)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                PatientArrayAdapter mAdapter = (PatientArrayAdapter) patientListView.getAdapter();
                                mAdapter.removeItemAt(mPos);
                                mAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();
                return true;
            }
        });
        patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.v("!", "boobs");
            }
        });
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.patient_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.ab_add_patient:
                final EditText input = new EditText(getActivity().getApplicationContext());
                input.setTextColor(getResources().getColor(R.color.abc_search_url_text_pressed));

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add a New Patient")
                        .setView(input)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                PatientArrayAdapter mAdapter = (PatientArrayAdapter)patientListView.getAdapter();
                                mAdapter.add(new Patient(input.getText().toString()));
                                mAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        //TODO Save sessions data
    }

}
