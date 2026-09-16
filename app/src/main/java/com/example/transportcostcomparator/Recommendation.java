package com.example.transportcostcomparator;

public class Recommendation {


    Transport transport;
    TransportCalculator calculator;

    String rating;

    public Recommendation(Transport transport) {

        this.transport = transport;

        this.calculator = new TransportCalculator(transport);

        calculator.calcDailyTransportCost();
        calculator.calcMonthlyTransportCost();
        calculator.calcMonthlyTravelDist();


    }



    public String costCategory() {
        String costCat = "";

        double cost = calculator.monthlyCost;

        if (cost >= 5000) {
            costCat = "Excessive";
        } else if (cost >= 3000) {
            costCat = "Very High";
        } else if (cost >= 2000) {
            costCat = "High";
        } else if (cost >= 1000) {
            costCat = "Moderate";
        } else if (cost >= 0) {
            costCat = "Low Cost";
        }

        return costCat;
    }
    public String savingRec(){
        String costCat = costCategory();
        String recom = "";

        if (costCat == "Excessive") {
            recom = "Immediate action is recommended to reduce transport costs.";
        } else if (costCat == "Very High") {
            recom = "Consider public transport for regular commuting.";
        } else if (costCat == "High") {
            recom = "Reduce unnecessary trips and combine errands.";
        } else if (costCat == "Moderate") {
            recom = "Consider carpooling where possible.";
        } else if (costCat == "Low Cost") {
            recom = "Continue using your current transport method.";
        }

        return recom;
    }
}
