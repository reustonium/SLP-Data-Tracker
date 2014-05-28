package com.reustonium.slpdatatracker.fragments;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private boolean mSubtitleVisable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Patients");
        mPatients = PatientFactory.get(getActivity()).getPatients();
        setListAdapter(new PatientAdapter(mPatients));
        setHasOptionsMenu(true);
        setRetainInstance(true);
        mSubtitleVisable = false;
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            getActivity().getActionBar().setSubtitle("Your Patients are #1");
        }

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_patient_list, menu);
        MenuItem showSubtitle = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisable && showSubtitle != null) {
            showSubtitle.setTitle("Hide Subtitle");
        }
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

    @TargetApi(11)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_new_patient:
                Patient patient = new Patient();
                PatientFactory.get(getActivity()).addPatient(patient);
                Intent i = new Intent(getActivity(), PatientPagerActivity.class);
                i.putExtra(PatientFragment.EXTRA_PATIENT_ID, patient.getId());
                startActivityForResult(i, 0);
                return true;
            case R.id.menu_item_show_subtitle:
                if (getActivity().getActionBar().getSubtitle() == null) {
                    getActivity().getActionBar().setSubtitle("Your Patients are #1");
                    item.setTitle("Hide Subtitle");
                    mSubtitleVisable = true;
                } else {
                    getActivity().getActionBar().setSubtitle(null);
                    item.setTitle("Show Subtitle");
                    mSubtitleVisable = false;
                }

            default:
                return super.onOptionsItemSelected(item);
        }
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
