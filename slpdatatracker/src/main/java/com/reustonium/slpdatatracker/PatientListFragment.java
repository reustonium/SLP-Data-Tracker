package com.reustonium.slpdatatracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
        //TODO populate sessions data
        patients.add(new Patient("ADR"));
        patients.add(new Patient("LCR"));
        patients.add(new Patient("AER"));
        patients.add(new Patient("WCR"));
        patients.add(new Patient("ARS"));
        patients.add(new Patient("MEK"));
        patients.add(new Patient("PWL"));
        patients.add(new Patient("JII"));
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
        return rootView;
    }



    @Override
    public void onPause() {
        super.onPause();
        //TODO Save sessions data
    }

}
