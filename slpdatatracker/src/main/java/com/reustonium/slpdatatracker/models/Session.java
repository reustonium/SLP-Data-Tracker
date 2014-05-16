package com.reustonium.slpdatatracker.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrew on 5/15/2014.
 */
public class Session {
    Date date;
    List<Goal> goals;

    public Session(){
        date = new Date();
        goals = new ArrayList<Goal>();
    }

    public Session(Patient patient){
        date = new Date();
        this.goals = new ArrayList<Goal>();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }
}
