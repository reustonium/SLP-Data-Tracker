package com.reustonium.slpdatatracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.reustonium.slpdatatracker.models.Session;

import java.util.ArrayList;

/**
 * Created by Andrew on 5/15/2014.
 */
public class SessionArrayAdapter extends ArrayAdapter<Session> {

    private ArrayList<Session> sessions = new ArrayList<Session>();
    private Context context;

    public SessionArrayAdapter(Context context, int resource, ArrayList<Session> objects) {
        super(context, resource, objects);
        this.context = context;
        this.sessions = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.session_row_layout, parent, false);

        ImageView profilePic = (ImageView)rowView.findViewById(R.id.sessionRow_photo);
        TextView name = (TextView)rowView.findViewById(R.id.sessionRow_TV_name);
        TextView date = (TextView)rowView.findViewById(R.id.sessionRow_TV_date);

        //TODO set profilePic
        name.setText(sessions.get(position).getPatient().getName());
        date.setText(sessions.get(position).getDate().toString());

        return rowView;
    }
}
