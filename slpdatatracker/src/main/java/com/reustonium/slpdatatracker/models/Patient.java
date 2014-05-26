package com.reustonium.slpdatatracker.models;

import java.util.UUID;
import java.util.ArrayList;

/**
 * Created by Andrew on 5/15/2014.
 */
public class Patient {
    UUID mId;
    String name;
    ArrayList<Session> sessions;

    public Patient(){
        sessions = new ArrayList<Session>();
        mId = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    public UUID getId() {
        return mId;
    }

    @Override
    public String toString() {
        return name;
    }
}
