package com.reustonium.slpdatatracker.models;

/**
 * Created by Andrew on 5/15/2014.
 */
public class Goal {
    public String goalName;
    public int numQuestion;
    public int numIndependent;
    public int numCue;

    public Goal(){

    }

    public Goal(String goalName, int numQuestion, int numIndependent, int numCue){
        this.goalName = goalName;
        this.numQuestion = numQuestion;
        this.numIndependent = numIndependent;
        this.numCue = numCue;
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

    public void setNumIndependent(int numIndependent) {
        this.numIndependent = numIndependent;
    }

    public int getNumCue() {
        return numCue;
    }

    public void setNumCue(int numCue) {
        this.numCue = numCue;
    }
}
