package com.malik.urfan.microservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Crime {

    private String category;
    private Location location;
    private String context;
    private OutcomeStatus outcome_status;
    private String month;

    public Crime() {}

    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }

    public OutcomeStatus getOutcome_status() {
        return outcome_status;
    }
    public void setOutcome_status(OutcomeStatus outcomeStatus) {
        this.outcome_status = outcomeStatus;
    }

    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "Crime{" +
                "url='" + category + "'" +
                "}";
    }
}