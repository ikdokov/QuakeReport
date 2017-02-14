package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by ivandokov on 2/9/17.
 */

public class Earthquake {
    private double magnitude;
    private String location;
    private long time;
    private String url;

    public Earthquake(double magnitude, String location, Long time, String url) {
        this.magnitude = magnitude;
        this.location = location;
        this.time = time;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

}
