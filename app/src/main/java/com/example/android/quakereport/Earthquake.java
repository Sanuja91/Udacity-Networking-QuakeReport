package com.example.android.quakereport;

import java.util.Date;

public class Earthquake {
    private String mQuakeName;
    private Long mQuakeDate;
    private double mMagnitude;
    private String mURL;

    public Earthquake(String quakeName, Long quakeDate, double magnitude, String url){
        mQuakeName = quakeName;
        mQuakeDate = quakeDate;
        mMagnitude = magnitude;
        mURL = url;
    }

    public String getQuakeName(){
        return mQuakeName;
    }

    public Long getQuakeDate(){
        return mQuakeDate;
    }

    public double getMagnitude(){
        return mMagnitude;
    }

    public String getURL(){ return mURL;}

}
