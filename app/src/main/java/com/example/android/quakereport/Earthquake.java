package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by ivandokov on 2/9/17.
 */

public class Earthquake {
    private double magnitude;
    private String location;
    private long time;

    public Earthquake(double magnitude, String location, Long time) {
        this.magnitude = magnitude;
        this.location = location;
        this.time = time;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getTime() {
        return time;
    }

}
