package com.reustonium.slpdatatracker.fragments;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.reustonium.slpdatatracker.PatientPagerActivity;
import com.reustonium.slpdatatracker.R;
import com.reustonium.slpdatatracker.models.Patient;
import com.reustonium.slpdatatracker.models.PatientFactory;

/**
 * Created by Andrew on 5/15/2014.
 */
public class PatientListFragment extends ListFragment {
    private ArrayList<Patient> mPatients = new ArrayList<Patient>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Patients");
        mPatients = PatientFactory.get(getActivity()).getPatients();
        setListAdapter(new PatientAdapter(mPatients));
    }

    @Override
    public void onResume() {
        super.onResume();
        ((PatientAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Patient p = (Patient)getListAdapter().getItem(position);

        Intent i = new Intent(getActivity(), PatientPagerActivity.class);
        i.putExtra(PatientFragment.EXTRA_PATIENT_ID, p.getId());
        startActivity(i);
    }

    private class PatientAdapter extends ArrayAdapter<Patient>{

        public PatientAdapter(ArrayList<Patient> patients) {
            super(getActivity(), 0, patients);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_patients, null);
            }

            Patient p = getItem(position);

            TextView nameTextView = (TextView)convertView.findViewById(R.id.patient_list_item_nameTextView);
            TextView updatedTextView = (TextView)convertView.findViewById(R.id.patient_list_item_updatedTextView);

            nameTextView.setText(p.getName());
            updatedTextView.setText(p.getUpdatedAt().toString());

            return convertView;
        }
    }
}
