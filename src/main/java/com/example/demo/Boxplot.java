package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Boxplot {
    private Minority minority;
    private ArrayList<ArrayList<Double>> boxCreationValues;

    public Boxplot(Minority minority, ArrayList<ArrayList<Double>> boxCreationValues) {
        this.minority = minority;
        this.boxCreationValues = boxCreationValues;
    }

    //getters
    public Minority getMinority() {
        return minority;
    }

    public ArrayList<ArrayList<Double>> getBoxCreationValues() {
        return boxCreationValues;
    }


    //setters
    public void setMinority(Minority minority) {
        this.minority = minority;
    }

    public void setBoxCreationValues(ArrayList<ArrayList<Double>> boxCreationValues) {
        this.boxCreationValues = boxCreationValues;
    }


    /* need implementation
    public double calculateAveragePercent(long district_num){
        return 50f;
    }
    public double calculateMedian(long district_num){
        return 50f;
    }
    public double calculateMin(long district_num){
        return 50f;
    }
    public double calculateMax(long district_num){
        return 50f;
    }
    public double calculateQ1(long district_num){
        return 50f;
    }
    public double calculateQ3(long district_num){
        return 50f;
    }
    public Collection<String> orderByIncreasingPercentage(Collection<String> districts){
        return new ArrayList<String>(Arrays.asList("District 1", "District 2")) {
        };
    }
     */
}