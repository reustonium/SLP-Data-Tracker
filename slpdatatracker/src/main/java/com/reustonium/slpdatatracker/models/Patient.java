package com.reustonium.slpdatatracker.models;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Andrew on 5/15/2014.
 */
public class Patient {
    String name;
    List<Session> sessions;

    public Patient(){
        sessions = new ArrayList<Session>();
    }

    public Patient(String name){
        this.name = name;
        sessions = new ArrayList<Session>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }
}
