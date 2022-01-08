package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Districting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double compactness;
    @Convert(converter = MapObjectConverter.class)
    private Map<String, Integer> demographicPopulation;
    private double equalPopulation;
    private double efficiencyGap;
    private double equalVap;

    @Transient
    private double ofScore;
    @Transient
    private double splitCounties;
    @Transient
    private double deviationFromEnactedArea;
    @Transient
    private double deviationFromEnactedPop;
    @Transient
    private double deviationFromAvg;
    @Transient
    private double deviationFromIdealPop;
    @Transient
    private double splitCountiesNormalized;
    @Transient
    private double deviationFromEnactedAreaNormalized;
    @Transient
    private double deviationFromEnactedPopNormalized;
    @Transient
    private double deviationFromAvgNormalized;
    @Transient
    private double deviationFromIdealPopNormalized;
    @Transient
    private double compactnessNormalized;
    @Transient
    private double efficiencyGapNormalized;
    @Transient
    private double equalPopulationNormalized;
    @Transient
    private double equalVapNormalized;

    @Transient
    private List<District> districts;
    @Transient
    private Boxplot boxplot;
    @Transient
    private DistrictingSummary summary;
    @Transient
    private List<Incumbent> incumbents;
    @Transient
    private double deviationFromMajMin;
    @Transient
    private long numMajMinDistricts;

    protected Districting(){
        this.districts = new ArrayList<>();
    }

    public Districting(long id, double ofScore, double compactness, double equalPopulation, double splitCounties, double deviationFromEnactedArea, double deviationFromEnactedPop, double deviationFromAvg, List<District> districts, Boxplot boxplot, DistrictingSummary summary) {
        this.id = id;
        this.ofScore = ofScore;
        this.compactness = compactness;
        this.equalPopulation = equalPopulation;
        this.splitCounties = splitCounties;
        this.deviationFromEnactedArea = deviationFromEnactedArea;
        this.deviationFromEnactedPop = deviationFromEnactedPop;
        this.deviationFromAvg = deviationFromAvg;
        this.districts = districts;
        this.boxplot = boxplot;
        this.summary = summary;
        this.deviationFromMajMin = deviationFromMajMin;
        this.numMajMinDistricts = numMajMinDistricts;
    }

    //getters
    public long getId() {
        return id;
    }

    public double getOfScore() {
        return ofScore;
    }

    public double getCompactness() {
        return compactness;
    }

    public double getEqualPopulation() {
        return equalPopulation;
    }

    public double getEqualVap(){
        return equalVap;
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

    public double getDeviationFromAvg() {
        return deviationFromAvg;
    }

    public double getEfficiencyGap(){
        return efficiencyGap;
    }

    public double getDeviationFromEnactedAreaNormalized(){
        return deviationFromEnactedAreaNormalized;
    }

    public double getDeviationFromEnactedPopNormalized(){
        return deviationFromEnactedPopNormalized;
    }

    public double getDeviationFromAvgNormalized(){
        return deviationFromAvgNormalized;
    }

    public double getDeviationFromIdealPop(){
        return deviationFromIdealPop;
    }

    public double getCompactnessNormalized(){
        return compactnessNormalized;
    }

    public double getEfficiencyGapNormalized(){
        return efficiencyGapNormalized;
    }

    public double getEqualPopulationNormalized(){
        return equalPopulationNormalized;
    }

    public double getEqualVapNormalized(){
        return equalVapNormalized;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public Boxplot getBoxplot() {
        return boxplot;
    }

    public DistrictingSummary getSummary() {
        return summary;
    }

    public List<Incumbent> getIncumbents() {
        return incumbents;
    }

    public double getDeviationFromMajMin(){
        return deviationFromMajMin;
    }

    public long getNumMajMinDistricts(){
        return numMajMinDistricts;
    }

    //calculation getters
    public List<District> getSortedDistrictsByRace(String race) {
        return districts;
    }

    public List<Long> getDistrictBoxplotValues() {
        return new ArrayList<Long>();
    }

    public double getDeviationFromIdealPopNormalized() {
        return deviationFromIdealPopNormalized;
    }

    //setters
    public void setId(long id) {
        this.id = id;
    }

    public void setOfScore(double ofScore) {
        this.ofScore = ofScore;
    }

    public void setCompactness(double compactness) {
        this.compactness = compactness;
    }

    public void setEqualPopulation(double equalPopulation) {
        this.equalPopulation = equalPopulation;
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

    public void setDeviationFromAvg(double deviationFromAvg) {
        this.deviationFromAvg = deviationFromAvg;
    }

    public void setDeviationFromEnactedAreaNormalized(double deviationFromEnactedAreaNormalized){
        this.deviationFromEnactedAreaNormalized = deviationFromEnactedAreaNormalized;
    }

    public void setDeviationFromEnactedPopNormalized(double deviationFromEnactedPopNormalized){
        this.deviationFromEnactedPopNormalized = deviationFromEnactedPopNormalized;
    }

    public void setDeviationFromAvgNormalized(double deviationFromAvgNormalized){
        this.deviationFromAvgNormalized = deviationFromAvgNormalized;
    }

    public void setDeviationFromIdealPop(double deviationFromIdealPop){
        this.deviationFromIdealPop = deviationFromIdealPop;
    }

    public void setCompactnessNormalized(double compactnessNormalized){
        this.compactnessNormalized = compactnessNormalized;
    }

    public void setEfficiencyGapNormalized(double efficiencyGapNormalized){
        this.efficiencyGapNormalized = efficiencyGapNormalized;
    }

    public void setEqualPopulationNormalized(double equalPopulationNormalized){
        this.equalPopulationNormalized = equalPopulationNormalized;
    }

    public void setEqualVapNormalized(double equalVapNormalized){
        this.equalVapNormalized = equalVapNormalized;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public void setBoxplot(Boxplot boxplot) {
        this.boxplot = boxplot;
    }

    public void createSummary(String popType) {
        List<DistrictDetails> districtDetails = new ArrayList<>();
        for (District d: districts) {
            districtDetails.add(d.getDistrictDetails());
        }
        if (popType.equals("TPOP")) {
            this.summary = new DistrictingSummary(id, ofScore, equalPopulationNormalized, splitCountiesNormalized, deviationFromEnactedAreaNormalized, deviationFromEnactedPopNormalized, deviationFromAvgNormalized, deviationFromIdealPopNormalized, deviationFromMajMin, compactnessNormalized, efficiencyGapNormalized, equalPopulation, splitCounties, deviationFromEnactedArea, deviationFromEnactedPop, deviationFromAvg, deviationFromIdealPop, compactness, efficiencyGap, numMajMinDistricts, districtDetails);
        }
        else {
            this.summary = new DistrictingSummary(id, ofScore, equalVapNormalized, splitCountiesNormalized, deviationFromEnactedAreaNormalized, deviationFromEnactedPopNormalized, deviationFromAvgNormalized, deviationFromIdealPopNormalized, deviationFromMajMin, compactnessNormalized, efficiencyGapNormalized, equalVap, splitCounties, deviationFromEnactedArea, deviationFromEnactedPop, deviationFromAvg, deviationFromIdealPop, compactness, efficiencyGap, numMajMinDistricts, districtDetails);
        }


    }

    public void setIncumbents(List<Incumbent> incumbents) {
        this.incumbents = incumbents;
    }

    public void addDistrict(District dist) {
        this.districts.add(dist);
    }

    public void setEfficiencyGap(double efficiencyGap) {
        this.efficiencyGap = efficiencyGap;
    }

    public void setEqualVap(double equalVap) {
        this.equalVap = equalVap;
    }

    public void setDeviationFromIdealPopNormalized(double deviationFromIdealPopNormalized) {
        this.deviationFromIdealPopNormalized = deviationFromIdealPopNormalized;
    }
    
    public void setDeviationFromMajMin(double deviationFromMajMin){
        this.deviationFromMajMin = deviationFromMajMin;
    }

    public void setNumMajMinDistricts(long numMajMinDistricts){
        this.numMajMinDistricts = numMajMinDistricts;
    }
}
