package com.reustonium.slpdatatracker.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;
import java.util.ArrayList;

/**
 * Created by Andrew on 5/15/2014.
 */
public class Patient {
    public static final String JSON_ID = "id";
    public static final String JSON_UPDATEDAT = "updatedAt";
    public static final String JSON_NAME = "name";

    UUID mId;
    Date updatedAt;
    String name;
    ArrayList<Session> sessions;

    public Patient(){
        sessions = new ArrayList<Session>();
        mId = UUID.randomUUID();
        updateDate();
    }

    public Patient(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        updatedAt = new Date(json.getLong(JSON_UPDATEDAT));
        name = json.getString(JSON_NAME);
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

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_UPDATEDAT, updatedAt.getTime());
        json.put(JSON_NAME, name);

        return json;
    }
}
