package com.malik.urfan.microservice.domain;

public class Location {

    private String latitude;
    private String longitude;
    private String postcode;

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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "com.malik.urfan.microservice.Location{" +
                "postcode=" + postcode +
                '}';
    }
}