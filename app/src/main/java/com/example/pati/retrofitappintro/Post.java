package com.example.pati.retrofitappintro;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pati on 22.11.2018.
 */

public class Post {

    private int userId;

    private int id;

    private  String title;

    @SerializedName("body")
    private String text;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
