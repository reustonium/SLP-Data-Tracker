package com.reustonium.slpdatatracker.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andrew on 5/22/2014.
 */
public class PatientDAO extends SQLiteOpenHelper {
    public static final String TABLE_PATIENTS = "patients";
    public static final String COLUMN_ID = "_id";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
