package com.reustonium.slpdatatracker.models;

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
    ArrayList<Session> sessions;

    public Patient(){
        sessions = new ArrayList<Session>();
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

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void addSession(Session session) {
        this.sessions.add(session);
        updateDate();
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
}
