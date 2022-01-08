package com.example.demo;

public class ObjectiveFunctionFilter {
    private double compactness;
    private double populationEquality;
    private double splitCounties;
    private double deviationFromEnactedArea;
    private double deviationFromEnactedPop;
    private double deviationFromAverage;
    private double politicalFairness;

    public ObjectiveFunctionFilter(double compactness, double populationEquality, double splitCounties, double deviationFromEnactedArea, double deviationFromEnactedPop, double deviationFromAverage, double politicalFairness) {
        this.compactness = compactness;
        this.populationEquality = populationEquality;
        this.splitCounties = splitCounties;
        this.deviationFromEnactedArea = deviationFromEnactedArea;
        this.deviationFromEnactedPop = deviationFromEnactedPop;
        this.deviationFromAverage = deviationFromAverage;
        this.politicalFairness = politicalFairness;
    }

    public double getCompactness() {
        return compactness;
    }

    public double getPopulationEquality() {
        return populationEquality;
    }

    public double getSplitCounties() {
        return splitCounties;
    }

    public double getDeviationFromEnactedArea() {
        return deviationFromEnactedArea;
    }

    public double getDeviationFromEnactedPop() {
        return deviationFromEnactedPop;
    }

    public double getDeviationFromAverage() {
        return deviationFromAverage;
    }

    public double getPoliticalFairness() {
        return politicalFairness;
    }

    public void setCompactness(double compactness) {
        this.compactness = compactness;
    }

    public void setPopulationEquality(double populationEquality) {
        this.populationEquality = populationEquality;
    }

    public void setSplitCounties(double splitCounties) {
        this.splitCounties = splitCounties;
    }

    public void setDeviationFromEnactedArea(double deviationFromEnactedArea) {
        this.deviationFromEnactedArea = deviationFromEnactedArea;
    }

    public void setDeviationFromEnactedPop(double deviationFromEnactedPop) {
        this.deviationFromEnactedPop = deviationFromEnactedPop;
    }

    public void setDeviationFromAverage(double deviationFromAverage) {
        this.deviationFromAverage = deviationFromAverage;
    }

    public void setPoliticalFairness(double politicalFairness) {
        this.politicalFairness = politicalFairness;
    }
}
