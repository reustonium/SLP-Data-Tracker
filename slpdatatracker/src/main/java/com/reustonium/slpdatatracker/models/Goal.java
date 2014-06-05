package com.reustonium.slpdatatracker.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andrew on 5/15/2014.
 */
public class Goal implements Serializable{
    public String goalName;
    private Date mDate;
    public int numQuestion;
    public int numIndependent;
    public int numCue;

    public Goal(){
        setDate(new Date());
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public int getNumQuestion() {
        return numQuestion;
    }

    public void setNumQuestion(int numQuestion) {
        this.numQuestion = numQuestion;
    }

    public int getNumIndependent() {
        return numIndependent;
    }

    public int getIndependentPercentage(){
        if(numQuestion == 0){
            return 0;
        } else {
            return Math.round(numIndependent * 100/numQuestion);
        }
    }
    public int getCuePercentage(){
        if(numQuestion == 0){
            return 0;
        } else {
            return Math.round((numIndependent+numCue) * 100/numQuestion);
        }
    }

    public void setNumIndependent(int numIndependent) {
        this.numIndependent = numIndependent;
    }

    public int getNumCue() {
        return numCue;
    }

    public void setNumCue(int numCue) {
        this.numCue = numCue;
    }

    @Override
    public String toString() {
        return goalName;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getPrettyDate(){
        DateFormat formatter = new SimpleDateFormat("MMMM d, yyyy hh:mm a");
        return formatter.format(getDate());
    }
}
