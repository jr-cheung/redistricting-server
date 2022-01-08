package com.example.demo;

import java.util.Map;

public class JobSummary {
    private long jobId;
    private String state;
    private long numberOfDistrictings;
    private Map<String, Long> mgggConstraints;

    public JobSummary(long jobId, String state, long numberOfDistrictings, Map<String, Long> mgggConstraints){
        this.jobId = jobId;
        this.state = state;
        this.numberOfDistrictings = numberOfDistrictings;
        this.mgggConstraints = mgggConstraints;
    }

    public long getJobId() {
        return jobId;
    }

    public String getState() {
        return state;
    }

    public long getNumberOfDistrictings() {
        return numberOfDistrictings;
    }

    public Map<String, Long> getMGGGConstraints() {
        return mgggConstraints;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setNumberOfDistrictings(long numberOfDistrictings) {
        this.numberOfDistrictings = numberOfDistrictings;
    }

    public void setMgggConstraints(Map<String, Long> mgggConstraints) {
        this.mgggConstraints = mgggConstraints;
    }
}
