package com.example.demo;

import org.json.JSONObject;
import java.io.Serializable;
import java.util.*;

public class State implements Serializable{
    private String name;
    private long population;
    private long area;
    private long minorityPopulation;
    private double minorityPercentage;
    private long numDistricts;
    private List<Minority> minorityGroups;
    private org.json.simple.JSONObject historicalVoting;
    private List<org.json.simple.JSONObject> jobs;
    private Job currentJob;
    private List<Incumbent> incumbents;
    private List<Map<String, Long>> enactedDistricting;
    private Districting averageDistricting;
    private long tVAP;

    public State(String name, long population, long area, long minorityPopulation, double minorityPercentage, long numDistricts, List<Minority> minorityGroups, List<Incumbent> incumbents, org.json.simple.JSONObject historicalVoting, List<org.json.simple.JSONObject> jobs, List<Map<String, Long>> enactedDistricting, long tVAP) {
        this.name = name;
        this.population = population;
        this.area = area;
        this.minorityPopulation = minorityPopulation;
        this.minorityPercentage = minorityPercentage;
        this.numDistricts = numDistricts;
        this.minorityGroups = minorityGroups;
        this.historicalVoting = historicalVoting;
        this.incumbents = incumbents;
        this.jobs = jobs;
        this.enactedDistricting = enactedDistricting;
        this.tVAP = tVAP;
    }

    //getters
    public String getName() {
        return name;
    }
    public long getPopulation() {
        return population;
    }
    public long getArea() {
        return area;
    }
    public long getMinorityPopulation() {
        return minorityPopulation;
    }
    public double getMinorityPercentage() {
        return minorityPercentage;
    }
    public long getNumDistricts() {
        return numDistricts;
    }
    public List<Minority> getMinorityGroups() {
        return minorityGroups;
    }
    public org.json.simple.JSONObject getHistoricalVoting() {
        return historicalVoting;
    }
    public List<org.json.simple.JSONObject> getJobs() {
        return jobs;
    }
    public Job getCurrentJob() {
        return currentJob;
    }
    public List<Incumbent> getIncumbents(){ return incumbents; }
    public List<Map<String, Long>> getEnactedDistricting(){
        return enactedDistricting;
    }
    public Districting getAverageDistricting() {
        return averageDistricting;
    }
    public long gettVAP() {
        return tVAP;
    }
//public static State getStateInfo(String stateName) { return State;} returns State object after accessing database

    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setPopulation(long population) {
        this.population = population;
    }
    public void setArea(long area) {
        this.area = area;
    }
    public void setMinorityPopulation(long minorityPopulation) {
        this.minorityPopulation = minorityPopulation;
    }
    public void setMinorityPercentage(double minorityPercentage) {
        this.minorityPercentage = minorityPercentage;
    }
    public void setNumDistricts(long numDistricts) {
        this.numDistricts = numDistricts;
    }
    public void setMinorityGroups(List<Minority> minorityGroups) { this.minorityGroups = minorityGroups; }
    public void setHistoricalVoting(org.json.simple.JSONObject historicalVoting) {this.historicalVoting = historicalVoting; }
    public void setJobs(List<org.json.simple.JSONObject> jobs) {
        this.jobs = jobs;
    }
    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
    }
    public void setAverageDistricting(Districting averageDistricting) {
        this.averageDistricting = averageDistricting;
    }
}
