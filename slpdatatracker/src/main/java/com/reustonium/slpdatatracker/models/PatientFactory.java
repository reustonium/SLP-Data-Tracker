package com.reustonium.slpdatatracker.models;

import java.util.ArrayList;
import java.util.UUID;
import android.content.Context;

/**
 * Created by Andrew on 5/25/2014.
 */
public class PatientFactory {
    public static final String TAG = "PatientFactory";
    public static final String FILENAME = "patients.json";

    private ArrayList<Patient> mPatients;
    private SlpDataTrackerJSONSerializer mSerializer;

    private static PatientFactory sPatientFactory;
    private Context mAppContext;

    private PatientFactory(Context context){
        mAppContext = context;
        mPatients = new ArrayList<Patient>();
        mSerializer = new SlpDataTrackerJSONSerializer(mAppContext, FILENAME);

        try{
            mPatients = mSerializer.loadPatients();
        } catch(Exception ex){
            mPatients = new ArrayList<Patient>();
        }
    }

    public static PatientFactory get(Context c){
        if (sPatientFactory == null) {
            sPatientFactory = new PatientFactory(c.getApplicationContext());
        }
        return sPatientFactory;
    }

    public ArrayList<Patient> getPatients(){
        return mPatients;
    }

    public Patient getPatient(UUID uuid){
        for (Patient p : mPatients){
            if (p.getId().equals(uuid)) {
                return p;
            }
        }
        return null;
    }

    public void addPatient(Patient p){
        mPatients.add(p);
    }

    public boolean savePatients(){
        try{
            mSerializer.savePatients(mPatients);
            return true;
        } catch (Exception ex){
            return false;
        }
    }
}
