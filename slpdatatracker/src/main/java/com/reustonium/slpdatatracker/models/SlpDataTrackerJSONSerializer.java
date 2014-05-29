package com.reustonium.slpdatatracker.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

/**
 * Created by Andrew on 5/28/2014.
 */
public class SlpDataTrackerJSONSerializer {
    private Context mContext;
    private String mFilename;

    public SlpDataTrackerJSONSerializer(Context c, String f){
        mContext = c;
        mFilename = f;
    }

    public void savePatients(ArrayList<Patient> patients) throws JSONException, IOException{
        JSONArray array = new JSONArray();
        for (Patient p : patients){
            array.put(p.toJSON());
        }

        Writer writer = null;
        try{
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<Patient> loadPatients() throws JSONException, IOException {
        ArrayList<Patient> patients = new ArrayList<Patient>();
        BufferedReader reader = null;

        try{
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null){
                jsonString.append(line);
            }
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for(int i=0; i<array.length(); i++){
                patients.add(new Patient(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException ex) {

        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return patients;
    }
}
