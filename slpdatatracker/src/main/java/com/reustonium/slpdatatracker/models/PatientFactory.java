package com.reustonium.slpdatatracker.models;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;
import android.content.Context;
import android.net.Uri;

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

    public void deletePatient(Patient p){
        mPatients.remove(p);
    }

    public boolean savePatients(){
        try{
            mSerializer.savePatients(mPatients);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    public String generateEmailBody(Date date){
        String msg = new String();
        for (Patient patient : mPatients) {
            msg = msg + (patient.getName() + System.getProperty("line.separator"));
            for(int i=0; i< patient.getGoals().size(); i++){
                Goal goal = patient.getGoals().get(i);
                if(sameDate(date, goal.getDate())){
                    msg = msg + (goal.getGoalName() + ": " + goal.getIndependentPercentage() + "% independently" +
                            ", " + goal.getCuePercentage() + "% with cues." + System.getProperty("line.separator"));
                }
                if (i==patient.getGoals().size()-1){
                    msg = msg + System.getProperty("line.separator");
                }
            }
        }
        String uriText = "mailto:" + Uri.encode("lisa_ruestow@urmc.rochester.edu") +
                "?subject=" + Uri.encode(String.format("Patient Summary: %s", date.toString())) +
                "&body=" + Uri.encode(msg);
        return uriText;
    }

    private boolean sameDate(Date date1, Date date2){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}
