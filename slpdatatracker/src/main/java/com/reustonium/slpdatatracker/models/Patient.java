package com.reustonium.slpdatatracker.models;

/**
 * Created by Andrew on 5/15/2014.
 */
public class Patient {
    String name;

    public Patient(){

    }

    public Patient(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
