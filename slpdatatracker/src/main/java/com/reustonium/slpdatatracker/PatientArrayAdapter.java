package com.reustonium.slpdatatracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.reustonium.slpdatatracker.models.Patient;

import java.util.ArrayList;

/**
 * Created by Andrew on 5/15/2014.
 */
public class PatientArrayAdapter extends ArrayAdapter<Patient> {

    private ArrayList<Patient> patients = new ArrayList<Patient>();
    private Context context;

    public PatientArrayAdapter(Context context, int resource, ArrayList<Patient> objects) {
        super(context, resource, objects);
        this.context = context;
        this.patients = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.patient_row_layout, parent, false);

        TextView name = (TextView)rowView.findViewById(R.id.sessionRow_TV_name);

        name.setText(patients.get(position).getName());

        return rowView;
    }

    public void removeItemAt(int position) {
        patients.remove(position);
    }
}
