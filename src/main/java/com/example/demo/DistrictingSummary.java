package com.example.demo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class DistrictingSummary {
    private long id;
    private double ofScore;
    private double popEqualityScore;
    private double splitCountiesScore;
    private double deviationFromEnactedAreaScore;
    private double deviationFromEnactedPopScore;
    private double deviationFromAvgScore;
    private double deviationFromIdealPopScore;
    private double deviationFromMajMin;
    private double compactnessScore;
    private double polFairnessScore;
    private double popEquality;
    private double splitCounties;
    private double deviationFromEnactedArea;
    private double deviationFromEnactedPop;
    private double deviationFromAvg;
    private double deviationFromIdealPop;
    private double compactness;
    private double polFairness;
    private long numMajMinDistricts;
    private List<DistrictDetails> districtDetails;

    public DistrictingSummary(long id, double ofScore, double popEqualityScore, double splitCountiesScore, double deviationFromEnactedAreaScore, double deviationFromEnactedPopScore, double deviationFromAvgScore, double deviationFromIdealPopScore, double deviationFromMajMin, double compactnessScore, double polFairnessScore, double popEquality, double splitCounties, double deviationFromEnactedArea, double deviationFromEnactedPop, double deviationFromAvg, double deviationFromIdealPop,  double compactness, double polFairness, long numMajMinDistricts, List<DistrictDetails> districtDetails){
        this.id = id;
        this.ofScore = ofScore;
        this.popEqualityScore = popEqualityScore;
        this.splitCountiesScore = splitCountiesScore;
        this.deviationFromEnactedAreaScore = deviationFromEnactedAreaScore;
        this.deviationFromEnactedPopScore = deviationFromEnactedPopScore;
        this.deviationFromAvgScore = deviationFromAvgScore;
        this.deviationFromIdealPopScore = deviationFromIdealPopScore;
        this.deviationFromMajMin = deviationFromMajMin;
        this.compactnessScore = compactnessScore;
        this.polFairnessScore = polFairnessScore;
        this.popEquality = popEquality;
        this.splitCounties = splitCounties;
        this.deviationFromEnactedArea = deviationFromEnactedArea;
        this.deviationFromEnactedPop = deviationFromEnactedPop;
        this.deviationFromAvg = deviationFromAvg;
        this.deviationFromIdealPop = deviationFromIdealPop;
        this.compactness = compactness;
        this.polFairness = polFairness;
        this.numMajMinDistricts = numMajMinDistricts;
        this.districtDetails = districtDetails;
    }

    //setters
    public void setId(long id){
        this.id = id;
    }

    public void setOfScore(double ofScore){
        this.ofScore = ofScore;
    }

    public void setPopEqualityScore(double popEqualityScore){
        this.popEqualityScore = popEqualityScore;
    }

    public void setSplitCountiesScore(double splitCountiesScore){
        this.splitCountiesScore = splitCountiesScore;
    }

    public double getDeviationFromEnactedAreaScore() {
        return deviationFromEnactedAreaScore;
    }

    public double getDeviationFromEnactedPopScore() {
        return deviationFromEnactedPopScore;
    }

    public void setDeviationFromAvgScore(double deviationFromAvgScore){
        this.deviationFromAvgScore = deviationFromAvgScore;
    }

    public void setCompactness(double compactness){
        this.compactness = compactness;
    }

    public void setPolFairnessScore(double polFairnessScore){
        this.polFairnessScore = polFairnessScore;
    }

    public void setDistrictDetails(List<DistrictDetails> districtDetails){
        this.districtDetails = districtDetails;
    }

    //getters
    public long getId() {
        return id;
    }

    public double getOfScore() {
        return ofScore;
    }

    public double getPopEqualityScore() {
        return popEqualityScore;
    }

    public double getSplitCountiesScore() {
        return splitCountiesScore;
    }

    public void setDeviationFromEnactedAreaScore(double deviationFromEnactedAreaScore) {
        this.deviationFromEnactedAreaScore = deviationFromEnactedAreaScore;
    }

    public void setDeviationFromEnactedPopScore(double deviationFromEnactedPopScore) {
        this.deviationFromEnactedPopScore = deviationFromEnactedPopScore;
    }

    public double getDeviationFromAvgScore() {
        return deviationFromAvgScore;
    }

    public double getCompactness() {
        return compactness;
    }

    public double getPolFairnessScore() {
        return polFairnessScore;
    }

    public List<DistrictDetails> getDistrictDetails() {
        return districtDetails;
    }

    public double getCompactnessScore() {
        return compactnessScore;
    }

    public void setCompactnessScore(double compactnessScore) {
        this.compactnessScore = compactnessScore;
    }

    public double getPopEquality() {
        return popEquality;
    }

    public void setPopEquality(double popEquality) {
        this.popEquality = popEquality;
    }

    public double getSplitCounties() {
        return splitCounties;
    }

    public void setSplitCounties(double splitCounties) {
        this.splitCounties = splitCounties;
    }

    public double getDeviationFromEnactedArea() {
        return deviationFromEnactedArea;
    }

    public void setDeviationFromEnactedArea(double deviationFromEnactedArea) {
        this.deviationFromEnactedArea = deviationFromEnactedArea;
    }

    public double getDeviationFromEnactedPop() {
        return deviationFromEnactedPop;
    }

    public void setDeviationFromEnactedPop(double deviationFromEnactedPop) {
        this.deviationFromEnactedPop = deviationFromEnactedPop;
    }

    public double getDeviationFromAvg() {
        return deviationFromAvg;
    }

    public void setDeviationFromAvg(double deviationFromAvg) {
        this.deviationFromAvg = deviationFromAvg;
    }

    public double getPolFairness() {
        return polFairness;
    }

    public void setPolFairness(double polFairness) {
        this.polFairness = polFairness;
    }

    public double getDeviationFromMajMin(){
        return deviationFromMajMin;
    }

    public long getNumMajMinDistricts(){
        return numMajMinDistricts;
    }

    public double getDeviationFromIdealPopScore() {
        return deviationFromIdealPopScore;
    }

    public double getDeviationFromIdealPop() {
        return deviationFromIdealPop;
    }

    public void setDeviationFromIdealPopScore(double deviationFromIdealPopScore) {
        this.deviationFromIdealPopScore = deviationFromIdealPopScore;
    }

    public void setDeviationFromMajMin(double deviationFromMajMin) {
        this.deviationFromMajMin = deviationFromMajMin;
    }

    public void setDeviationFromIdealPop(double deviationFromIdealPop) {
        this.deviationFromIdealPop = deviationFromIdealPop;
    }

    public void setNumMajMinDistricts(long numMajMinDistricts) {
        this.numMajMinDistricts = numMajMinDistricts;
    }
}
