package com.example.rotation_sensors;

import com.google.gson.annotations.SerializedName;

public class DataObject {
    @SerializedName("x")
    private float x;

    @SerializedName("y")
    private float y;

    public DataObject(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
