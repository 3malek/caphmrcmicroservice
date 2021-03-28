package com.malik.urfan.microservice.domain;

public class GlobalCoordinatePosition {

    private double status;
    private Result result;

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "com.malik.urfan.microservice.Location{" +
                "status=" + status +
                ", result=" + result +
                '}';
    }
}
