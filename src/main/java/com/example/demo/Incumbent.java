package com.example.demo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Incumbent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String incumbentName;
    private String state;
    private long congressDistrict;
    private long party;
    private String homePrecinctName;
    private long precinctId;

    protected Incumbent(){}

    public Incumbent(String incumbentName, String state, long congressDistrict, long party, String homePrecinctName, long precinctId) {
        this.incumbentName = incumbentName;
        this.state = state;
        this.congressDistrict = congressDistrict;
        this.party = party;
        this.homePrecinctName = homePrecinctName;
        this.precinctId = precinctId;
    }

    public String getIncumbentName() {
        return incumbentName;
    }

    public void setIncumbentName(String incumbentName) {
        this.incumbentName = incumbentName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getCongressDistrict() {
        return congressDistrict;
    }

    public void setCongressDistrict(long congressDistrict) {
        this.congressDistrict = congressDistrict;
    }

    public long getParty() {
        return party;
    }

    public void setParty(long party) {
        this.party = party;
    }

    public String getHomePrecinctName() {
        return homePrecinctName;
    }

    public void setHomePrecinctName(String homePrecinctName) {
        this.homePrecinctName = homePrecinctName;
    }

    public long getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(long precinctId) {
        this.precinctId = precinctId;
    }
}
