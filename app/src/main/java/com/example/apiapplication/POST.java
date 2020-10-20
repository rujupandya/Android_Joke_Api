package com.example.apiapplication;

import com.google.gson.annotations.SerializedName;

public class POST {

    private  int id;
    private  String type;
    private  String setup;

    @SerializedName("body")


    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSetup() {
        return setup;
    }
}
