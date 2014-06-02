package com.reustonium.slpdatatracker.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.UUID;
import java.util.ArrayList;

/**
 * Created by Andrew on 5/15/2014.
 */
public class Patient {

    UUID mId;
    Date updatedAt;
    String name;
    ArrayList<Goal> mGoals;

    public Patient(){
        mGoals = new ArrayList<Goal>();
        mId = UUID.randomUUID();
        updateDate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateDate();
    }

    public ArrayList<Goal> getGoals() {
        return mGoals;
    }

    public void setGoals(ArrayList<Goal> goals) {
        mGoals = goals;
    }

    public UUID getId() {
        return mId;
    }

    @Override
    public String toString() {
        return name;
    }

    private void updateDate(){
        updatedAt = new Date();
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getPrettyUpdatedAt(){
        DateFormat formatter = new SimpleDateFormat("MMMM d, yyyy hh:mm a");
        return formatter.format(updatedAt);
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void addGoal(Goal mGoal) {
        mGoals.add(mGoal);
    }
}
