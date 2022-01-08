package com.example.demo;

import org.json.JSONObject;

import javax.persistence.*;

@Entity
public class Precinct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private Party votingHistory;
    private long population;
    private long countyId;
    @Convert(converter = JSONObjectConverter.class)
    private JSONObject demographicPopulation;
    @Convert(converter = JSONObjectConverter.class)
    private JSONObject geometry;

    protected Precinct(){}

    public Precinct(String name, long id, Party votingHistory, long population, long countyId, JSONObject demographicPopulation){
        this.name = name;
        this.id = id;
        this.votingHistory = votingHistory;
        this.population = population;
        this.countyId = countyId;
        this.demographicPopulation = demographicPopulation;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public Party getVotingHistory() {
        return votingHistory;
    }

    public long getPopulation() {
        return population;
    }

    public long getCountyId() {
        return countyId;
    }

    public JSONObject getDemographicPopulation() {
        return demographicPopulation;
    }

    public JSONObject getGeometry(){
        return geometry;
    }

    @Override
    public String toString(){
        return "Precinct{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", votingHistory=" + votingHistory +
                ", population=" + population +
                ", countyId=" + countyId +
                ", demographicPopulation=" + demographicPopulation +
                ", geometry=" + geometry +
                '}';
    }
}
