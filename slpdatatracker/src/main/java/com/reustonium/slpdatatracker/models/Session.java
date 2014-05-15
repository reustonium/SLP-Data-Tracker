package com.reustonium.slpdatatracker.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrew on 5/15/2014.
 */
public class Session {
    Patient patient;
    Date date;
    List<TestResult> results;

    public Session(){
        date = new Date();
    }

    public Session(Patient patient){
        this.patient = patient;
        date = new Date();
        this.results = new ArrayList<TestResult>();
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void addTestResult(TestResult result){
        results.add(result);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
