package com.example.transportcostcomparator;

public class Transport {

    public String transChoice;
    public String getTransChoice() {
        return transChoice;
    }
    public void setTransChoice(String transChoice){
        this.transChoice = transChoice;
    }
    public String mode;
    public String getMode() {
        return mode;
    }
    public void setMode(String mode){
        this.mode = mode;
    }

    public double distance;
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    public double travelDays;
    public double getTravelDays() {
        return travelDays;
    }

    public void setTravelDays(double travelDays) {
        this.travelDays = travelDays;
    }

    public double costPerKm;

    public double getCostPerKm() {
        return costPerKm;
    }

    public void setCostPerKm(double costPerKm) {
        this.costPerKm = costPerKm;
    }

    public Transport(String transChoice, String mode, double distance, double travelDays, double costPerKm) {
        this.transChoice = transChoice;
        this.mode = mode;
        this.distance = distance;
        this.travelDays = travelDays;
        this.costPerKm = costPerKm;
    }
}

