package com.reustonium.slpdatatracker.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        Gson gson = new Gson();
        String json = gson.toJson(patients);

        Writer writer = null;
        try{
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(json);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<Patient> loadPatients() throws JSONException, IOException {
        ArrayList<Patient> patients = new ArrayList<Patient>();
        BufferedReader reader = null;
        Gson gson = new Gson();

        try{
            InputStream in = mContext.openFileInput(mFilename);
            int size = in.available();
            byte[] buffer = new byte[size];

            in.read(buffer);
            in.close();

            String json = new String(buffer, "UTF-8");
            Type collectionType = new TypeToken<ArrayList<Patient>>(){}.getType();
            patients = gson.fromJson(json, collectionType);

        } catch (FileNotFoundException ex) {

        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return patients;
    }
}
