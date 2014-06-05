package com.reustonium.slpdatatracker.fragments;

import java.util.ArrayList;
import java.util.Date;

import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.reustonium.slpdatatracker.PatientActivity;
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
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        ListView listView = (ListView)v.findViewById(android.R.id.list);

        getActivity().getActionBar().setSubtitle("Yeah! Speech Therapist!");
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.patient_list_item_context, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.menu_item_delete_patient:
                        PatientAdapter adapter = (PatientAdapter)getListAdapter();
                        PatientFactory patientFactory = PatientFactory.get(getActivity());
                        for(int i=adapter.getCount()-1; i >=0; i--){
                            if(getListView().isItemChecked(i)){
                                patientFactory.deletePatient(adapter.getItem(i));
                            }
                        }
                        actionMode.finish();
                        adapter.notifyDataSetChanged();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_patient_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.patient_list_item_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position = info.position;
        PatientAdapter adapter = (PatientAdapter)getListAdapter();
        Patient patient = adapter.getItem(position);
        switch (item.getItemId()) {
            case R.id.menu_item_delete_patient:
                PatientFactory.get(getActivity()).deletePatient(patient);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((PatientAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Patient p = (Patient)getListAdapter().getItem(position);

        Intent i = new Intent(getActivity(), PatientActivity.class);
        i.putExtra(PatientFragment.EXTRA_PATIENT_ID, p.getId());
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_new_patient:
                Patient patient = new Patient();
                PatientFactory.get(getActivity()).addPatient(patient);
                Intent i = new Intent(getActivity(), PatientActivity.class);
                i.putExtra(PatientFragment.EXTRA_PATIENT_ID, patient.getId());
                startActivity(i);
                return true;

            case R.id.menu_item_email:
                Intent send = new Intent(Intent.ACTION_SENDTO);
                Uri uri = Uri.parse(PatientFactory.get(getActivity()).generateEmailBody(new Date()));
                send.setData(uri);
                startActivity(Intent.createChooser(send, "Send goals..."));
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
            TextView numGoalsTextView = (TextView)convertView.findViewById(R.id.patient_list_item_numGoals);

            nameTextView.setText(p.getName());
            updatedTextView.setText(p.getPrettyUpdatedAt().toString());
            numGoalsTextView.setText(String.format("%d goals", p.getGoals().size()));

            return convertView;
        }
    }
}
