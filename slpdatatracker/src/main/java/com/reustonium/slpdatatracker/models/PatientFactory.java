package com.reustonium.slpdatatracker.models;

import java.util.ArrayList;
import java.util.UUID;
import android.content.Context;

/**
 * Created by Andrew on 5/25/2014.
 */
public class PatientFactory {
    private ArrayList<Patient> mPatients;

    private static PatientFactory sPatientFactory;
    private Context mAppContext;

    private PatientFactory(Context context){
        mAppContext = context;
        mPatients = new ArrayList<Patient>();
        for(int i=0; i<8; i++){
            Patient p = new Patient();
            p.setName("Jimmy");
            mPatients.add(p);
        }
    }

    public static PatientFactory get(Context c){
        if (sPatientFactory == null) {
            return new PatientFactory(c.getApplicationContext());
        }
        return sPatientFactory;
    }

    public ArrayList<Patient> getPatients(){
        return mPatients;
    }

    public Patient getPatient(UUID uuid){
        for (Patient p : mPatients){
            if (p.getId()==uuid) {
                return p;
            }
        }
        return null;
    }
}
