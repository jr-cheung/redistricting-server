package com.example.demo;

import java.io.Serializable;
import java.util.Map;

public class DistrictDetails implements Serializable{
    private long id;
    private boolean isMajorityMinority;
    private Party politicalParty;
    private Incumbent incumbent;
    private double area;
    private double perimeter;
    private double deviationFromEnactedArea;
    private double deviationFromEnactedPop;
    private double deviationFromAverage;
    private double deviationFromIdealPop;
    private Map<String, Integer> demographicPopulation;
    private double compactness;

    public DistrictDetails(long id, boolean isMajorityMinority, Party politicalParty, Incumbent incumbent, double area, double perimeter, double deviationFromEnactedArea, double deviationFromEnactedPop, double deviationFromAverage, double deviationFromIdealPop, Map<String, Integer> demographicPopulation, double compactness) {
        this.id = id;
        this.isMajorityMinority = isMajorityMinority;
        this.politicalParty = politicalParty;
        this.incumbent = incumbent;
        this.area = area;
        this.deviationFromEnactedArea = deviationFromEnactedArea;
        this.deviationFromEnactedPop = deviationFromEnactedPop;
        this.deviationFromAverage = deviationFromAverage;
        this.deviationFromIdealPop = deviationFromIdealPop;
        this.demographicPopulation = demographicPopulation;
        this.compactness = compactness;
    }

    //getters
    public long getId() {
        return id;
    }

    public boolean isMajorityMinority() {
        return isMajorityMinority;
    }

    public Party getPoliticalParty() {
        return politicalParty;
    }

    public Incumbent getIncumbent() {
        return incumbent;
    }

    public double getArea() {
        return area;
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

    public double getDeviationFromIdealPop() {
        return deviationFromIdealPop;
    }

    public Map<String, Integer> getDemographicPopulation() {
        return demographicPopulation;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public double getCompactness() {
        return compactness;
    }

    public void setCompactness(double compactness) {
        this.compactness = compactness;
    }

    //setters
    public void setId(long id) {
        this.id = id;
    }

    public void setMajorityMinority(boolean majorityMinority) {
        this.isMajorityMinority = majorityMinority;
    }

    public void setPoliticalParty(Party politicalParty) {
        this.politicalParty = politicalParty;
    }

    public void setIncumbent(Incumbent incumbent) {
        this.incumbent = incumbent;
    }

    public void setArea(double area) {
        this.area = area;
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

    public void setDemographicPopulation(Map<String, Integer> demographicPopulation) {
        this.demographicPopulation = demographicPopulation;
    }

    public void setDeviationFromIdealPop(double deviationFromIdealPop) {
        this.deviationFromIdealPop = deviationFromIdealPop;
    }
}
