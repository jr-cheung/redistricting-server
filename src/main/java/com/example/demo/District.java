package com.example.demo;

import org.json.JSONObject;

import javax.persistence.*;

import java.util.Map;

@Entity
public class District{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long districtingId;
    private long number;
    private double compactness;
    @Convert(converter = MapObjectConverter.class)
    private Map<String, Integer> demographicPopulation;
    private double perimeter;
    private double area;
    private String precincts;
    @Transient
    private boolean isMajorityMinority;
    @Transient
    private Party politicalParty;
    @Transient
    private Incumbent incumbent;
    @Transient
    private double deviationFromEnactedArea;
    @Transient
    private double deviationFromEnactedPop;
    @Transient
    private double deviationFromAverage;
    @Transient
    private double deviationFromIdealPop;
    @Transient
    private DistrictDetails districtDetails;
    @Transient
    private JSONObject geometry;

    protected District(){
    }

    //district geometry not in constructor(currently not added for testing)
    public District(long id, boolean isMajorityMinority, Party politicalParty, Incumbent incumbent, double area, double deviationFromEnactedArea, double deviationFromEnactedPop,  double deviationFromAverage, String precincts, DistrictDetails districtDetails){
        this.id = id;
        this.isMajorityMinority = isMajorityMinority;
        this.politicalParty = politicalParty;
        this.incumbent = incumbent;
        this.deviationFromEnactedArea = deviationFromEnactedArea;
        this.deviationFromEnactedPop = deviationFromEnactedPop;
        this.deviationFromAverage = deviationFromAverage;
        this.precincts = precincts;
        this.districtDetails = districtDetails;
    }

    //getters
    public long getId(){
        return id;
    }

    public boolean isMajorityMinority(){
        return isMajorityMinority;
    }

    public Party getPoliticalParty(){
        return politicalParty;
    }

    public Incumbent getIncumbent(){
        return incumbent;
    }

    public double getArea(){
        return area;
    }

    public double getDeviationFromEnactedArea() {
        return deviationFromEnactedArea;
    }

    public double getDeviationFromEnactedPop() {
        return deviationFromEnactedPop;
    }

    public double getDeviationFromAverage(){
        return deviationFromAverage;
    }

    public double getDeviationFromIdealPop(){
        return deviationFromIdealPop;
    }

    public String getPrecincts(){
        return precincts;
    }

    public JSONObject getGeometry(){
        return geometry;
    }

    public DistrictDetails getDistrictDetails(){
        return new DistrictDetails(id, isMajorityMinority, politicalParty, incumbent, area, perimeter, deviationFromEnactedArea, deviationFromEnactedPop, deviationFromAverage, deviationFromIdealPop, demographicPopulation, compactness);
    }

    public void setId(long id){
        this.id = id;
    }

    public long getDistrictingId(){
        return districtingId;
    }

    public void setDistrictingId(long districtingId){
        this.districtingId = districtingId;
    }

    public long getNumber(){
        return number;
    }

    public void setNumber(long number){
        this.number = number;
    }

    public double getCompactness(){
        return compactness;
    }

    public void setCompactness(double compactness){
        this.compactness = compactness;
    }

    public Map<String, Integer> getDemographicPopulation(){
        return demographicPopulation;
    }

    public void setDemographicPopulation(Map<String, Integer> demographicPopulation){
        this.demographicPopulation = demographicPopulation;
    }

    public double getPerimeter(){
        return perimeter;
    }

    public void setPerimeter(double perimeter){
        this.perimeter = perimeter;
    }

    public void setArea(double area){
        this.area = area;
    }

    //setters
    public void setMajorityMinority(boolean majorityMinority){
        this.isMajorityMinority = majorityMinority;
    }

    public void setPoliticalParty(Party politicalParty){
        this.politicalParty = politicalParty;
    }

    public void setIncumbent(Incumbent incumbent){
        this.incumbent = incumbent;
    }

    public void setArea(long area){
        this.area = area;
    }

    public void setDeviationFromEnactedArea(double deviationFromEnactedArea) {
        this.deviationFromEnactedArea = deviationFromEnactedArea;
    }

    public void setDeviationFromEnactedPop(double deviationFromEnactedPop) {
        this.deviationFromEnactedPop = deviationFromEnactedPop;
    }

    public void setDeviationFromAverage(double deviationFromAverage){
        this.deviationFromAverage = deviationFromAverage;
    }

    public void setDeviationFromIdealPop(double deviationFromIdealPop){
        this.deviationFromIdealPop = deviationFromIdealPop;
    }

    public void setPrecincts(String precincts){
        this.precincts = precincts;
    }

    public void setGeometry(JSONObject geometry){
        this.geometry = geometry;
    }
}
