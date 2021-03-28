package com.malik.urfan.microservice.domain;

public class Result {

    private String longitude;
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Result{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}