package com.example.adapterapp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Item {

    private String title;
    private String description;

    // Constructor bình thường
    public Item(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Constructor từ JSON
    public Item(JSONObject object) {
        try {
            this.title = object.getString("title");
            this.description = object.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Convert JSONArray → List<Item>
    public static ArrayList<Item> fromJson(JSONArray jsonArray) {
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                items.add(new Item(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return items;
    }

    // Getter
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}