package com.example.adapterapp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {
    private String name;
    private String hometown;

    // Constructor to convert JSON object into a Java class instance
    public User(JSONObject object) {
        try {
            this.name = object.getString("name");
            this.hometown = object.getString("hometown");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public User(String name, String hometown) {
        this.name = name;
        this.hometown = hometown;
    }

    public String getName() {
        return name;
    }

    public String getHomeTown() {
        return hometown;
    }

    // Factory method to convert an array of JSON objects into a list of objects
    public static ArrayList<User> fromJson(JSONArray jsonObjects) {
        ArrayList<User> users = new ArrayList<User>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                users.add(new User(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}
