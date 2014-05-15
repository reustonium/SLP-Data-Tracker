package com.reustonium.slpdatatracker.models;

/**
 * Created by Andrew on 5/15/2014.
 */
public class TestResult {
    public String testName;
    public int numQuestion;
    public int numCorrect;
    public int numCue;

    public TestResult(){

    }

    public TestResult(String testName, int numQuestion, int numCorrect, int numCue){
        this.testName = testName;
        this.numQuestion = numQuestion;
        this.numCorrect = numCorrect;
        this.numCue = numCue;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getNumQuestion() {
        return numQuestion;
    }

    public void setNumQuestion(int numQuestion) {
        this.numQuestion = numQuestion;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public void setNumCorrect(int numCorrect) {
        this.numCorrect = numCorrect;
    }

    public int getNumCue() {
        return numCue;
    }

    public void setNumCue(int numCue) {
        this.numCue = numCue;
    }
}
