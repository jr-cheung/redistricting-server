package com.example.demo;

import java.util.List;

public class ConstraintsFilter {
    private double popEqualityThreshold;
    private String popEqualityType;
    private long majMinDistricts;
    private double majMinThreshold;
    private double compactness;
    private boolean incumbentsProtection;
    private List<String> incumbentsProtected;
    private Minority boxplotMinority;

    public ConstraintsFilter(double popEqualityThreshold, String popEqualityType, long majMinDistricts, double majMinThreshold, double compactness, boolean incumbentsProtection, List<String> incumbentsProtected, Minority boxplotMinority) {
        this.popEqualityThreshold = popEqualityThreshold;
        this.popEqualityType = popEqualityType;
        this.majMinDistricts = majMinDistricts;
        this.majMinThreshold = majMinThreshold;
        this.compactness = compactness;
        this.incumbentsProtection = incumbentsProtection;
        this.incumbentsProtected = incumbentsProtected;
        this.boxplotMinority = boxplotMinority;
    }

    //getters
    public double getPopEqualityThreshold() {
        return popEqualityThreshold;
    }

    public String getPopEqualityType() {
        return popEqualityType;
    }

    public long getMajMinDistricts() {
        return majMinDistricts;
    }

    public double getMajMinThreshold() {
        return majMinThreshold;
    }

    public double getCompactness() {
        return compactness;
    }

    public boolean isIncumbentsProtection() {
        return incumbentsProtection;
    }

    public List<String> getIncumbentsProtected() {
        return incumbentsProtected;
    }

    public Minority getBoxplotMinority() {
        return boxplotMinority;
    }

    //setters
    public void setPopEqualityThreshold(double popEqualityThreshold) {
        this.popEqualityThreshold = popEqualityThreshold;
    }

    public void setPopEqualityType(String popEqualityType) {
        this.popEqualityType = popEqualityType;
    }

    public void setMajMinDistricts(long majMinDistricts) {
        this.majMinDistricts = majMinDistricts;
    }

    public void setMajMinThreshold(double majMinThreshold) {
        this.majMinThreshold = majMinThreshold;
    }

    public void setCompactness(double compactness) {
        this.compactness = compactness;
    }

    public void setIncumbentsProtection(boolean incumbentsProtection) {
        this.incumbentsProtection = incumbentsProtection;
    }

    public void setIncumbentsProtected(List<String> incumbentsProtected) {
        this.incumbentsProtected = incumbentsProtected;
    }

    public void setBoxplotMinority(Minority boxplotMinority) {
        this.boxplotMinority = boxplotMinority;
    }
}
