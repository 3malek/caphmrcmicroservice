package com.malik.urfan.microservice.domain;

public class OutcomeStatus {

    private String category;
    private String date;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OutcomeStatus{" +
                "category='" + category + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}