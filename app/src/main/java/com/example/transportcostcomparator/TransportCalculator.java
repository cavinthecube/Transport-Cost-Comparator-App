package com.example.transportcostcomparator;

public class TransportCalculator {

    double dailyCost;
    double monthlyCost;
    double monthlyDistance;

    Transport transport;
    public TransportCalculator(Transport transport) {
        this.transport = transport;
    }
    public double calcDailyTransportCost(){
        dailyCost = transport.distance * transport.costPerKm;
        return dailyCost;
    }
    public double calcMonthlyTransportCost(){
        monthlyCost = dailyCost * transport.travelDays;
        return monthlyCost;
    }
    public double calcMonthlyTravelDist(){
        monthlyDistance = transport.distance * transport.travelDays;
        return monthlyDistance;
    }

}

