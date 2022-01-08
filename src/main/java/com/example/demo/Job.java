package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.Math;

public class Job implements Serializable {
    private long id;
    private String state;
    private org.json.simple.JSONObject mgggParams;
    private long numDistrictings;
    private String tableName;
    private List<Districting> districtings;
    private List<Districting> filteredDistrictings;
    private List<Districting> ofDistrictings;
    private List<Districting> closestToEnactedDistrictings;
    private List<Districting> closestToAverageDistrictings;
    private List<Districting> areaPairDeviationDistrictings;
    private List<DistrictingSummary> districtingSummaries;
    private ConstraintsFilter constraints;
    private ObjectiveFunctionFilter objectiveFunction;
    private Boxplot jobBoxplot;
    private Districting averageDistricting;
    private Districting currentDistricting;
    private State stateObj;
    private List<Integer> avgDistrictingMinorityPop;

    public Job(long id, String state, org.json.simple.JSONObject mgggParams, long numDistrictings, String tableName){
        this.id = id;
        this.state = state;
        this.mgggParams = mgggParams;
        this.numDistrictings = numDistrictings;
        this.tableName = tableName;
    }

    public long getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public org.json.simple.JSONObject getMgggParams() {
        return mgggParams;
    }

    public String getTableName() {
        return tableName;
    }

    public List<Districting> getDistrictings() {
        return districtings;
    }

    public List<Districting> getFilteredDistrictings() {
        return filteredDistrictings;
    }

    public List<Districting> getOfDistrictings() {
        return ofDistrictings;
    }

    public ConstraintsFilter getConstraints() {
        return constraints;
    }

    public ObjectiveFunctionFilter getObjectiveFunction() {
        return objectiveFunction;
    }

    public Boxplot getJobBoxplot() {
        return jobBoxplot;
    }

    public Districting getAverageDistricting() {
        return averageDistricting;
    }

    public Districting getCurrentDistricting() {
        return currentDistricting;
    }


    public Districting getDistrictingById(long id) {
        for (Districting d: ofDistrictings) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    public long getNumDistrictings() {
        return numDistrictings;
    }

    public List<Districting> getClosestToAverageDistrictings() {
        return closestToAverageDistrictings;
    }

    public void setNumDistrictings(long numDistrictings) {
        this.numDistrictings = numDistrictings;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Districting> getClosestToEnactedDistrictings() {
        return closestToEnactedDistrictings;
    }

    public void setClosestToEnactedDistrictings(List<Districting> closestToEnactedDistrictings) {
        this.closestToEnactedDistrictings = closestToEnactedDistrictings;
    }

    public List<Districting> getAreaPairDeviationDistrictings() {
        return areaPairDeviationDistrictings;
    }

    public void setAreaPairDeviationDistrictings(List<Districting> areaPairDeviationDistrictings) {
        this.areaPairDeviationDistrictings = areaPairDeviationDistrictings;
    }

    public List<DistrictingSummary> getDistrictingSummaries() {
        return districtingSummaries;
    }

    public void setDistrictingSummaries(List<DistrictingSummary> districtingSummaries) {
        this.districtingSummaries = districtingSummaries;
    }

    public State getStateObj() {
        return stateObj;
    }

    /* unfinished getter still needs implementation */
    public String getAlgorithm() {
        return "unfinished";
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setMgggParams(org.json.simple.JSONObject mggg) {
        this.mgggParams = mggg;
    }

    public void setConstraints(ConstraintsFilter constraints) {
        this.constraints = constraints;
    }

    public void setObjectiveFunction(ObjectiveFunctionFilter objectiveFunction) {
        this.objectiveFunction = objectiveFunction;
    }
    public void setDistrictings(List<Districting> districtings) {
        this.districtings = districtings;
    }

    public void setFilteredDistrictings(List<Districting> filteredDistrictings) {
        this.filteredDistrictings = filteredDistrictings;
    }

    public void setOfDistrictings(List<Districting> ofDistrictings) {
        this.ofDistrictings = ofDistrictings;
    }

    public void setJobBoxplot(Boxplot jobBoxplot) {
        this.jobBoxplot = jobBoxplot;
    }

    public void setAverageDistricting(Districting averageDistricting) {
        this.averageDistricting = averageDistricting;
    }

    public void setCurrentDistricting(Districting currentDistricting) {
        this.currentDistricting = currentDistricting;
    }

    public void setStateObj(State stateObj){
        this.stateObj = stateObj;
    }

    public void setClosestToAverageDistrictings(List<Districting> closestToAverageDistrictings) {
        this.closestToAverageDistrictings = closestToAverageDistrictings;
    }
    
    public void setAvgDistrictingMinorityPop(List<Integer> avgDistrictingMinorityPop){
        this.avgDistrictingMinorityPop = avgDistrictingMinorityPop;
    }

    public Districting calculateAverageDistricting() {
        List<String> popType = new ArrayList<>();
        if (constraints.getPopEqualityType().equals("TPOP")) {
            popType.add("totalPop");
            popType.add("blackPop");
            popType.add("hispanicPop");
            popType.add("asianPop");
            popType.add("nativeAmericanPop");
        }
        else if (constraints.getPopEqualityType().equals("TVAP")) {
            popType.add("totalVap");
            popType.add("blackVap");
            popType.add("hispanicVap");
            popType.add("asianVap");
            popType.add("nativeAmericanVap");
        }
        Minority minority = constraints.getBoxplotMinority();
        ArrayList<Integer> districtAverages = new ArrayList<>(Collections.nCopies((int) stateObj.getNumDistricts(), 0));

        // Sort selected minority pop from smallest to largest from each district
        for (int i=0; i<filteredDistrictings.size(); i++){
            ArrayList<District> districts = new ArrayList<>(filteredDistrictings.get(i).getDistricts());
            ArrayList<Integer> minorityPopSorted = new ArrayList<>();

            for (int j=0; j<districts.size();j++){
                Map<String, Integer> demographics = districts.get(j).getDemographicPopulation();
                if (minority == Minority.AFRICAN_AMERICAN){
                    minorityPopSorted.add(demographics.get(popType.get(1))); //black
                }
                else if (minority == Minority.HISPANIC){
                    minorityPopSorted.add(demographics.get(popType.get(2))); //hispanic
                }
                else if (minority == Minority.ASIAN)
                    minorityPopSorted.add(demographics.get(popType.get(3))); //asian
                else{
                    minorityPopSorted.add(demographics.get(popType.get(4))); //nativeAmerican
                }
            }
            Collections.sort(minorityPopSorted);

            // Add district pop to averages list
            for (int k=0; k<districtAverages.size(); k++){
                Integer num = districtAverages.get(k);
                num += minorityPopSorted.get(k);
                districtAverages.set(k, num);
            }
        }
        // Calculate the average
        for (int i=0; i<districtAverages.size(); i++){
            Integer num = districtAverages.get(i);
            num /= this.filteredDistrictings.size();
            districtAverages.set(i, num);
        }
        setAvgDistrictingMinorityPop(districtAverages);

        // Find the index of districting that has its districts closest to the average
        ArrayList<Integer> differences = new ArrayList<>();
        for (int i=0; i<this.filteredDistrictings.size(); i++) {
            ArrayList<District> districts = new ArrayList<>(this.filteredDistrictings.get(i).getDistricts());
            ArrayList<Integer> minorityPopSorted = new ArrayList<>();

            for (int j = 0; j < districts.size(); j++) {
                Map<String, Integer> demographics = districts.get(j).getDemographicPopulation();
                if (minority == Minority.AFRICAN_AMERICAN) {
                    minorityPopSorted.add(demographics.get(popType.get(1))); //black
                } else if (minority == Minority.HISPANIC) {
                    minorityPopSorted.add(demographics.get(popType.get(2))); //hispanic
                } else if (minority == Minority.ASIAN)
                    minorityPopSorted.add(demographics.get(popType.get(3))); //asian
                else {
                    minorityPopSorted.add(demographics.get(popType.get(4))); //nativeAmerican
                }
            }
            Collections.sort(minorityPopSorted);

            // Calculate the difference between district and average, sum the differences, and store into differences list
            int total_difference = 0;
            for (int k=0; k<districtAverages.size(); k++){
                int difference = districtAverages.get(k) - minorityPopSorted.get(k);
                if (difference < 0){
                    difference *= -1;
                }
                total_difference += difference;
            }
            differences.add(total_difference);
        }
        // Find index with the min difference
        int resultIndex = 0;
        int min = differences.get(resultIndex);
        for (int i=0; i<differences.size(); i++){
            if (differences.get(i) < min){
                resultIndex = i;
                min = differences.get(i);
            }
        }
        this.stateObj.setAverageDistricting(filteredDistrictings.get(resultIndex));
        return filteredDistrictings.get(resultIndex);
    }

    /* Boxplot calculation methods still needs implementation */
    public List<Double> calculateMedian() { return new ArrayList<Double>(); }
    public List<Double> calculateMin() { return new ArrayList<Double>(); }
    public List<Double> calculateMax() { return new ArrayList<Double>(); }
    public List<Double> calculateQ1() { return new ArrayList<Double>(); }
    public List<Double> calculateQ3() { return new ArrayList<Double>(); }
    public List<Double> calculateBoxplotData(Minority race) { return new ArrayList<Double>(); }

    public void applyConstraintsPart1(Minority chosenMinority, List<String> summary, HttpServletRequest request) {
        int unconstraintedNum;
        int constraintedNum;
        unconstraintedNum = districtings.size();
        List<Districting> filteredDist = districtings.stream().filter(districting -> districting.getCompactness() > constraints.getCompactness()
        ).collect(Collectors.toList());
        constraintedNum = filteredDist.size();
        int compactnessNumFiltered = unconstraintedNum - constraintedNum;
        unconstraintedNum = filteredDist.size();
        if (constraints.getPopEqualityType().equals("TPOP")) {
            filteredDist = filteredDist.stream().filter(districting ->
                    districting.getEqualPopulation() < constraints.getPopEqualityThreshold()
            ).collect(Collectors.toList());
        }
        else if (constraints.getPopEqualityType().equals("TVAP")) {
            filteredDist = filteredDist.stream().filter(districting ->
                    districting.getEqualVap() < constraints.getPopEqualityThreshold()
            ).collect(Collectors.toList());
        }
        constraintedNum = filteredDist.size();
        int popEqualNumFiltered = unconstraintedNum - constraintedNum;
        summary.add(String.valueOf(compactnessNumFiltered));
        summary.add(String.valueOf(popEqualNumFiltered));
        this.setFilteredDistrictings(filteredDist);
        System.out.println("filtered Districting Part 1:" + filteredDistrictings.size());
    }

    /* Logic methods still needs implementation */
    public List<String> applyConstraintsPart2(Minority chosenMinority, List<String> summary, HttpServletRequest request) {
        //string list for either TPOP or TVAP
        List<String> popType = new ArrayList<>();
        if (constraints.getPopEqualityType().equals("TPOP")) {
            popType.add("totalPop");
            popType.add("blackPop");
            popType.add("hispanicPop");
            popType.add("asianPop");
            popType.add("nativeAmericanPop");
        }
        else if (constraints.getPopEqualityType().equals("TVAP")) {
            popType.add("totalVap");
            popType.add("blackVap");
            popType.add("hispanicVap");
            popType.add("asianVap");
            popType.add("nativeAmericanVap");
        }
        int unconstraintedNum;
        int constraintedNum;
        unconstraintedNum = filteredDistrictings.size();
        List<Districting> filteredDist = filteredDistrictings.stream().filter(districting ->
                               calculateMajMinDistricts(districting, constraints.getBoxplotMinority(), constraints.getMajMinThreshold(), popType) >= constraints.getMajMinDistricts()
                ).collect(Collectors.toList());
        constraintedNum = filteredDist.size();
        int majMinDistNumFiltered = unconstraintedNum - constraintedNum;
        unconstraintedNum = filteredDist.size();
        filteredDist = filteredDist.stream().filter(districting ->
                areIncumbentsProtected(constraints.getIncumbentsProtected(), districting, request)
        ).collect(Collectors.toList());
        constraintedNum = filteredDist.size();
        int incumbentNumFiltered = unconstraintedNum - constraintedNum;
        summary.add(String.valueOf(majMinDistNumFiltered));
        summary.add(String.valueOf(incumbentNumFiltered));

        ArrayList<Double> majMinDistPerDistricting = new ArrayList<Double>();
        ArrayList<Double> popPerDist = new ArrayList<Double>();
        calcuMajMinDistPerDistrictingAndTotPopPerDis(majMinDistPerDistricting, popPerDist, popType);

        //List<String> summary = new ArrayList<String>();
        summary.add(String.valueOf(districtings.size()));
        summary.add(String.valueOf(filteredDist.size()));
        this.setFilteredDistrictings(filteredDist);
        summary.add(String.valueOf(medianValue(majMinDistPerDistricting)));
        summary.add(String.valueOf(medianValue(popPerDist)));
        summary.add(String.valueOf(medianCompValue()));
        if (constraints.getPopEqualityType().equals("TPOP")) {
            summary.add(String.valueOf(medianPopEquValue()));
        }
        else {
            summary.add(String.valueOf(medianPopVAPValue()));
        }

        /*Create box plot for job */
        ArrayList<ArrayList<Double>> allMinorityPercents = new ArrayList<>();
        ArrayList<ArrayList<Double>> minorityPercentsByDistrict = new ArrayList<>();
        filteredDist.stream().forEach(districting -> {
            allMinorityPercents.add(calculateBoxPlotValues(districting, constraints.getBoxplotMinority()));
        });
        State currentState = (State) request.getSession().getAttribute("activeState");
        int numDistricts = (int) currentState.getNumDistricts();

        for (int j = 0; j < numDistricts; j++) {
            ArrayList<Double> percentOfDist = new ArrayList<>();
            for (int i = 0; i < allMinorityPercents.size(); i++) {
                percentOfDist.add(allMinorityPercents.get(i).get(j));
            }
            Collections.sort(percentOfDist);
            minorityPercentsByDistrict.add(percentOfDist);
        }

        this.setJobBoxplot(new Boxplot(constraints.getBoxplotMinority(), minorityPercentsByDistrict));
        return summary;
    }


    /* This method needs testing */
    public boolean areIncumbentsProtected(List<String> protectedIncumbents, Districting districting, HttpServletRequest request) {
        List<Long> userIncumbentsPrecIds = new ArrayList<>();
        State currentState = (State) request.getSession().getAttribute("activeState");
        List<Incumbent> currentIncumbents = currentState.getIncumbents();

        for(int i = 0; i < protectedIncumbents.size(); i++){
            for(int j = 0; j < currentIncumbents.size(); j++){
                if(protectedIncumbents.get(i).equals(currentIncumbents.get(j).getIncumbentName())){
                    userIncumbentsPrecIds.add(currentIncumbents.get(j).getPrecinctId());
                    //System.out.println("Protected incumbent for districting " + districting.getId() + " : " + currentIncumbents.get(j).getPrecinctId());
                    break;
                }
            }
        }
        List<District> districts = new ArrayList<>(districting.getDistricts());
        for(int i = 0; i < districts.size(); i++) {
            ArrayList<Long> districtPrecincts = new ArrayList<>();
            String districtPrecinctString = districts.get(i).getPrecincts();
            districtPrecinctString = districtPrecinctString.replace("[", "").replace("]", "").replace(" ", "");
            String[] numbers = districtPrecinctString.split(",");
            for (int j = 0; j < numbers.length; j++) {
                districtPrecincts.add(Long.parseLong(numbers[j]));
            }

            //List<Precinct> precincts = new ArrayList<Precinct>(districts.get(i).getPrecincts());
            int incumbentCounter = 0;
            for (int j = 0; j < districtPrecincts.size(); j++) {
                if (userIncumbentsPrecIds.contains(districtPrecincts.get(j))) {
                    incumbentCounter++;
                }
                if (incumbentCounter > 1) {
                    return false;
                }
            }

            districts.get(i).setIncumbent(currentIncumbents.get(i));
        }
        return true;
    }

    public ArrayList<Double> calculateBoxPlotValues(Districting districting, Minority minority) {
        //ADD IN FOR WHETHER ITS TPOP OR TVAP
        List<String> popType = new ArrayList<>();
        if (constraints.getPopEqualityType().equals("TPOP")) {
            popType.add("totalPop");
            popType.add("blackPop");
            popType.add("hispanicPop");
            popType.add("asianPop");
            popType.add("nativeAmericanPop");
        }
        else if (constraints.getPopEqualityType().equals("TVAP")) {
            popType.add("totalVap");
            popType.add("blackVap");
            popType.add("hispanicVap");
            popType.add("asianVap");
            popType.add("nativeAmericanVap");
        }
        ArrayList<District> districts = new ArrayList<>(districting.getDistricts());
        ArrayList<Double> minorityPercents = new ArrayList<Double>();
        for (int i=0; i<districts.size(); i++) {
            Map<String, Integer> demographics = districts.get(i).getDemographicPopulation();
            long minorityPopulation = 0;
            long totalDistrictPopulation = (long) demographics.get(popType.get(0)); //totalPop or totalVap

            if (minority == Minority.AFRICAN_AMERICAN) {
                minorityPopulation = (long) demographics.get(popType.get(1)); //blackPop or blackVap
            } else if (minority == Minority.HISPANIC) {
                minorityPopulation = (long) demographics.get(popType.get(2)); //hispanicPop or hispanicVap
            } else if (minority == Minority.ASIAN)
                minorityPopulation = (long) demographics.get(popType.get(3)); //asianPop or asianVap
            else {
                minorityPopulation = (long) demographics.get(popType.get(4)); //nativeAmericanPop or nativeAmericanVap
            }
            double percentage = (double) minorityPopulation / (double) totalDistrictPopulation;
            minorityPercents.add(percentage);
        }
        Collections.sort(minorityPercents);
        return minorityPercents;
    }

    /* This method needs testing */
    public long calculateMajMinDistricts(Districting districting, Minority minority, double majMinThreshold, List<String> popType) {
        ArrayList<District> districts = new ArrayList<>(districting.getDistricts());
        long majMinCount = 0;

        for (int i=0; i<districts.size(); i++){
            Map<String, Integer> demographics = districts.get(i).getDemographicPopulation();
            long minorityPopulation = 0;
            long totalDistrictPopulation = (long) demographics.get(popType.get(0)); //totalPop or totalVap

            if (minority == Minority.AFRICAN_AMERICAN){
                minorityPopulation = (long) demographics.get(popType.get(1)); //blackPop or blackVap
            }
            else if (minority == Minority.HISPANIC){
                minorityPopulation = (long) demographics.get(popType.get(2)); //hispanicPop or hispanicVap
            }
            else if (minority == Minority.ASIAN)
                minorityPopulation = (long) demographics.get(popType.get(3)); //asianPop or asianVap
            else{
                minorityPopulation = (long) demographics.get(popType.get(4)); //nativeAmericanPop or nativeAmericanVap
            }
            double percentage = (double) minorityPopulation / (double) totalDistrictPopulation;
            if (percentage >= majMinThreshold){
                districts.get(i).setMajorityMinority(true);
                majMinCount++;
            } else {
                districts.get(i).setMajorityMinority(false);
            }
        }
        districting.setNumMajMinDistricts(majMinCount);
        return majMinCount;
    }

    public double medianValue(ArrayList<Double> arraylist) {
        Collections.sort(arraylist);
        if(arraylist.size() == 0){
            return 0.0;
        }
        if (arraylist.size() % 2 == 1){
            long medInd = (arraylist.size() - 1) / 2;
            return arraylist.get((int)medInd);
        }
        else {
            long medIndUpper = (arraylist.size() - 1) / 2;
            long medIndLower = (arraylist.size() - 1) / 2;
            return (arraylist.get((int)medIndUpper) + arraylist.get((int)medIndLower)) / 2.0;
        }
    }

    public double medianCompValue() {
        ArrayList<Double> districtingsCompact = new ArrayList<Double>();
        ArrayList<Districting> filteredDist = new ArrayList<Districting>(filteredDistrictings);
        for (long i = 0; i < filteredDist.size(); i++){
            districtingsCompact.add(filteredDist.get((int)i).getCompactness());
        }
        return medianValue(districtingsCompact);
    }

    public double medianPopEquValue() {
        ArrayList<Double> districtingsPopEqu = new ArrayList<Double>();
        ArrayList<Districting> filteredDist = new ArrayList<Districting>(filteredDistrictings);
        for (long i = 0; i < filteredDist.size(); i++){
            districtingsPopEqu.add(filteredDist.get((int)i).getEqualPopulation());
        }
        return medianValue(districtingsPopEqu);
    }

    public double medianPopVAPValue() {
        ArrayList<Double> districtingsVapEqu = new ArrayList<Double>();
        ArrayList<Districting> filteredDist = new ArrayList<Districting>(filteredDistrictings);
        for (long i = 0; i < filteredDist.size(); i++){
            districtingsVapEqu.add(filteredDist.get((int)i).getEqualVap());
        }
        return medianValue(districtingsVapEqu);
    }

    /* Adds majMin count and totPop of district to arraylists for median calculation for the summary */
    public void calcuMajMinDistPerDistrictingAndTotPopPerDis(ArrayList<Double> majMinDistPerDistricting, ArrayList<Double> popPerDist, List<String> popType) {
        Minority minority = constraints.getBoxplotMinority();
        String selectedMinority;
        if (minority == Minority.AFRICAN_AMERICAN){
            selectedMinority = popType.get(1); //black
        }
        else if (minority == Minority.HISPANIC){
            selectedMinority = popType.get(2); //hispanic
        }
        else if (minority == Minority.ASIAN) {
            selectedMinority = popType.get(3); //asian
        }
        else {
            selectedMinority = popType.get(4); //nativeAmerican
        }

        ArrayList<Districting> filteredDists = new ArrayList<Districting>(filteredDistrictings);
        for (int i = 0; i < filteredDists.size(); i++){
            ArrayList<District> districts = new ArrayList<District>(filteredDists.get(i).getDistricts());
            long majMinCount = 0;

            for (int j = 0; j < districts.size(); j++){
                Map<String, Integer> demographics = districts.get(j).getDemographicPopulation();
                long minorityPopulation = demographics.get(selectedMinority);
                long totalDistrictPopulation = demographics.get(popType.get(0)); //total

                double percentage = (double) minorityPopulation / (double) totalDistrictPopulation;

                if (percentage >= constraints.getMajMinThreshold()){
                    majMinCount++;
                }
                popPerDist.add((double)totalDistrictPopulation);
            }
            majMinDistPerDistricting.add((double)majMinCount);
        }
    }

    public String minorityToString(Minority minority, List<String> popType) {
        String selectedMinority;
        if (minority == Minority.AFRICAN_AMERICAN){
            selectedMinority = popType.get(1); //black
        }
        else if (minority == Minority.HISPANIC){
            selectedMinority = popType.get(2); //hispanic
        }
        else if (minority == Minority.ASIAN) {
            selectedMinority = popType.get(3); //asian
        }
        else {
            selectedMinority = popType.get(4); //nativeAmerican
        }
        return  selectedMinority;
    }

    private double calculateOfScore(Districting d, List<Double> OfScoreList) {
        double equalPopTypeScore = 0.0;
        if (constraints.getPopEqualityType().equals("TPOP")) {
            equalPopTypeScore = d.getEqualPopulationNormalized() * objectiveFunction.getPopulationEquality();
        }
        else {
            equalPopTypeScore = d.getEqualVapNormalized() * objectiveFunction.getPopulationEquality();
        }
        double ofScore = d.getCompactnessNormalized() * objectiveFunction.getCompactness() +
                equalPopTypeScore +
                d.getEfficiencyGapNormalized() * objectiveFunction.getPoliticalFairness() +
                d.getDeviationFromEnactedAreaNormalized() * objectiveFunction.getDeviationFromEnactedArea() +
                d.getDeviationFromEnactedPopNormalized() * objectiveFunction.getDeviationFromEnactedPop() +
                d.getDeviationFromAvgNormalized() * objectiveFunction.getDeviationFromAverage();
        OfScoreList.add(ofScore);
        /*double denoWeights = objectiveFunction.getPopulationEquality() +
                objectiveFunction.getCompactness() +
                objectiveFunction.getPoliticalFairness() +
                objectiveFunction.getDeviationFromEnactedArea() +
                objectiveFunction.getDeviationFromEnactedPop() +
                objectiveFunction.getDeviationFromAverage();
         */
        d.setOfScore(ofScore);

        return ofScore;
    }

    private double calculateDevFromEnacted(Districting d, List<Double> devFromEnactedAreaListMin, List<Double> devFromEnactedAreaListMax,
                                           List<Double> devFromEnactedPopListMin, List<Double> devFromEnactedPopListMax) {
        List<Map<String, Long>> enactedDistricting = stateObj.getEnactedDistricting();

        List<String> popType = new ArrayList<>();
        if (constraints.getPopEqualityType().equals("TPOP")) {
            popType.add("totalPop");
            popType.add("blackPop");
            popType.add("hispanicPop");
            popType.add("asianPop");
            popType.add("nativeAmericanPop");
        }
        else if (constraints.getPopEqualityType().equals("TVAP")) {
            popType.add("totalVap");
            popType.add("blackVap");
            popType.add("hispanicVap");
            popType.add("asianVap");
            popType.add("nativeAmericanVap");
        }

        double sumArea = 0;
        double sumPop = 0;
        for (int i = 0; i < enactedDistricting.size(); i++) {
            Map<String, Long> enactedDistrict = enactedDistricting.get(i);
            District currentDistrict = d.getDistricts().get(i);

            // Sum of Squares by District
            //String currentMinority= minorityToString(constraints.getBoxplotMinority());
            long enactedDistArea = enactedDistrict.get("area");
            long currDistArea = (long) currentDistrict.getArea();
            double areaDeviation = currDistArea - enactedDistArea;
            double squaredDeviationArea = Math.pow((areaDeviation), 2);
            sumArea = sumArea + squaredDeviationArea;

            String currentMinority= minorityToString(constraints.getBoxplotMinority(), popType);
            long enactedDistPop = enactedDistrict.get(currentMinority);
            long currDistPop = currentDistrict.getDemographicPopulation().get(currentMinority);
            double popDeviation = currDistPop - enactedDistPop;
            double squaredDeviationPop = Math.pow((popDeviation), 2);
            sumPop = sumPop + squaredDeviationPop;

            //set non-normalized deviation from enacted to district for district detail
            d.getDistricts().get(i).setDeviationFromEnactedArea(Math.abs(areaDeviation));
            d.getDistricts().get(i).setDeviationFromEnactedPop(Math.abs(popDeviation));

            //add sum of square deviation from enacted value to deviation list for min and max calculations
            if (areaDeviation < devFromEnactedAreaListMin.get(i)) {
                devFromEnactedAreaListMin.set(i, areaDeviation);
            }
            if (areaDeviation > devFromEnactedAreaListMax.get(i)) {
                devFromEnactedAreaListMax.set(i, areaDeviation);
            }
            if (popDeviation < devFromEnactedPopListMin.get(i)) {
                devFromEnactedPopListMin.set(i, popDeviation);
            }
            if (popDeviation > devFromEnactedPopListMax.get(i)) {
                devFromEnactedPopListMax.set(i, popDeviation);
            }
        }

        //set non-normalized deviation from enacted to districting for districting detail
        d.setDeviationFromEnactedArea(sumArea);
        d.setDeviationFromEnactedPop(sumPop);

        //add deviation from enacted value to deviation list for min and max calculations for districting
        int numDistricts = d.getDistricts().size();
        if (sumArea < devFromEnactedAreaListMin.get(numDistricts)) {
            devFromEnactedAreaListMin.set(numDistricts, sumArea);
        }
        if (sumArea > devFromEnactedAreaListMax.get(numDistricts)) {
            devFromEnactedAreaListMax.set(numDistricts, sumArea);
        }
        if (sumPop < devFromEnactedPopListMin.get(numDistricts)) {
            devFromEnactedPopListMin.set(numDistricts, sumPop);
        }
        if (sumPop > devFromEnactedPopListMax.get(numDistricts)) {
            devFromEnactedPopListMax.set(numDistricts, sumPop);
        }

        return sumArea;
    }

    private double calculateDevFromAverage(Districting d, List<Double> devFromAverageListMin, List<Double> devFromAverageListMax) {
        Districting averageDistricting = stateObj.getAverageDistricting();

        List<String> popType = new ArrayList<>();
        if (constraints.getPopEqualityType().equals("TPOP")) {
            popType.add("totalPop");
            popType.add("blackPop");
            popType.add("hispanicPop");
            popType.add("asianPop");
            popType.add("nativeAmericanPop");
        }
        else if (constraints.getPopEqualityType().equals("TVAP")) {
            popType.add("totalVap");
            popType.add("blackVap");
            popType.add("hispanicVap");
            popType.add("asianVap");
            popType.add("nativeAmericanVap");
        }

        double sum = 0;
        for (int i = 0; i < averageDistricting.getDistricts().size(); i++) {
            District averageDistrict = averageDistricting.getDistricts().get(i);
            District currentDistrict = d.getDistricts().get(i);

            // Sum of Squares by District
            String currentMinority = minorityToString(constraints.getBoxplotMinority(), popType);
            long averageDistRacePop = averageDistrict.getDemographicPopulation().get(currentMinority);
            long currDistRacePop = currentDistrict.getDemographicPopulation().get(currentMinority);
            double popDeviation = currDistRacePop - averageDistRacePop;
            double squaredDeviation = Math.pow((popDeviation), 2);
            sum = sum + squaredDeviation;

            //set non-normalized deviation from average to district for district detail
            d.getDistricts().get(i).setDeviationFromAverage(Math.abs(popDeviation));

            //add sum of square deviation from average value to deviation list for min and max calculations
            if (popDeviation < devFromAverageListMin.get(i)) {
                devFromAverageListMin.set(i, popDeviation);
            }
            if (popDeviation > devFromAverageListMax.get(i)) {
                devFromAverageListMax.set(i, popDeviation);
            }
        }

        //set non-normalized deviation from average to districting for districting detail
        d.setDeviationFromAvg(sum);

        //add deviation from average value to deviation list for min and max calculations for districting
        int numDistricts = d.getDistricts().size();
        if (sum < devFromAverageListMin.get(numDistricts)) {
            devFromAverageListMin.set(numDistricts, sum);
        }
        if (sum > devFromAverageListMax.get(numDistricts)) {
            devFromAverageListMax.set(numDistricts, sum);
        }

        return sum;
    }

    public void calculateDevFromIdeal(Districting d){
        int numDistricts = d.getDistricts().size();
        double idealPop = 0.0;
        List<String> popType = new ArrayList<>();
        if (constraints.getPopEqualityType().equals("TPOP")) {
            popType.add("totalPop");
            idealPop = stateObj.getPopulation() / numDistricts;
        }
        else if (constraints.getPopEqualityType().equals("TVAP")) {
            popType.add("totalVap");
            idealPop = stateObj.gettVAP() / numDistricts;
        }

        double sum = 0;
        for (int i = 0; i < numDistricts; i++) {
            District currentDistrict = d.getDistricts().get(i);
            double distPop = currentDistrict.getDemographicPopulation().get(popType.get(0));
            double popDeviation = distPop - idealPop;
            double squaredDeviation = Math.pow((popDeviation), 2);
            sum = sum + squaredDeviation;

            d.getDistricts().get(i).setDeviationFromIdealPop(Math.abs(popDeviation));
        }

        d.setDeviationFromIdealPop(sum);

    }

    public void normalizeDistrictdeviationsArea(Districting d, List<Double> devFromEnactedAreaListMin, List<Double> devFromEnactedAreaListMax,
                                                List<Double> devFromEnactedPopListMin, List<Double> devFromEnactedPopListMax) {
        //normalize districts
        int numDistricts = d.getDistricts().size();
        /*
        for (int i = 0; i < numDistricts; i++) {
            double minValue = devFromEnactedAreaListMin.get(i);
            double maxValue = devFromEnactedAreaListMax.get(i);
            if (maxValue == 0.0) {
                d.getDistricts().get(i).setDeviationFromEnactedArea(0.0);
            }
            else if (minValue == maxValue) {
                d.getDistricts().get(i).setDeviationFromEnactedArea(1.0);
            }
            else {
                double devFromEnactDistAreaValue = d.getDistricts().get(i).getDeviationFromEnactedArea();
                double normalizedValue = (devFromEnactDistAreaValue - minValue) / (maxValue - minValue);
                d.getDistricts().get(i).setDeviationFromEnactedArea(normalizedValue);
            }

            minValue = devFromEnactedPopListMin.get(i);
            maxValue = devFromEnactedPopListMax.get(i);
            if(maxValue == 0.0) {
                d.getDistricts().get(i).setDeviationFromEnactedPop(0.0);
            }
            else if (minValue == maxValue) {
                d.getDistricts().get(i).setDeviationFromEnactedPop(1.0);
            }
            else {
                double devFromEnactDistPopValue = d.getDistricts().get(i).getDeviationFromEnactedPop();
                double normalizedValue = (devFromEnactDistPopValue - minValue) / (maxValue - minValue);
                d.getDistricts().get(i).setDeviationFromEnactedPop(normalizedValue);
            }
        }

         */
        //normalize districting
        double minValue = devFromEnactedAreaListMin.get(numDistricts);
        double maxValue = devFromEnactedAreaListMax.get(numDistricts);
        if(maxValue == 0.0) {
            d.setDeviationFromEnactedAreaNormalized(0.0);
        }
        else if (minValue == maxValue) {
            d.setDeviationFromEnactedAreaNormalized(1.0);
        }
        else {
            double devFromEnactDistrictingAreaValue = d.getDeviationFromEnactedArea();
            double normalizedValue = (devFromEnactDistrictingAreaValue - minValue) / (maxValue - minValue);
            d.setDeviationFromEnactedAreaNormalized(normalizedValue);
        }

        minValue = devFromEnactedPopListMin.get(numDistricts);
        maxValue = devFromEnactedPopListMax.get(numDistricts);
        if(maxValue == 0.0) {
            d.setDeviationFromEnactedPopNormalized(0.0);
        }
        else if (minValue == maxValue) {
            d.setDeviationFromEnactedPopNormalized(1.0);
        }
        else {
            double devFromEnactDistrictingPopValue = d.getDeviationFromEnactedPop();
            double normalizedValue = (devFromEnactDistrictingPopValue - minValue) / (maxValue - minValue);
            d.setDeviationFromEnactedPopNormalized(normalizedValue);
        }
    }

    public void normalizeDistrictAverageDeviations(Districting d, List<Double> devFromAverageListMin, List<Double> devFromAverageListMax) {
        //normalize districts
        int numDistricts = d.getDistricts().size();
        /*
        for (int i = 0; i < numDistricts; i++) {
            double minValue = devFromAverageListMin.get(i);
            double maxValue = devFromAverageListMax.get(i);
            if (maxValue == 0.0) {
                d.getDistricts().get(i).setDeviationFromAverage(0.0);
            }
            else if (minValue == maxValue) {
                d.getDistricts().get(i).setDeviationFromAverage(1.0);
            }
            else {
                double devFromAverDistValue = d.getDistricts().get(i).getDeviationFromAverage();
                double normalizedValue = (devFromAverDistValue - minValue) / (maxValue - minValue);
                d.getDistricts().get(i).setDeviationFromAverage(normalizedValue);
            }
        }

         */
        //normalize districting
        double minValue = devFromAverageListMin.get(numDistricts);
        double maxValue = devFromAverageListMax.get(numDistricts);
        if (maxValue == 0.0) {
            d.setDeviationFromAvgNormalized(0.0);
        }
        else if (minValue == maxValue) {
            d.setDeviationFromAvgNormalized(1.0);
        }
        else {
            double devFromAverDistrictingValue = d.getDeviationFromAvg();
            double normalizedValue = (devFromAverDistrictingValue - minValue) / (maxValue - minValue);
            d.setDeviationFromAvgNormalized(normalizedValue);
        }
    }

    //normalize equalPop, compactness, and efficiencyGap to 0.0 - 1.0
    public void normalizeData() {
        List<Double> valueListEqualTVAP = new ArrayList<>();
        List<Double> valueListEqualPop = new ArrayList<>();
        List<Double> valueListComp = new ArrayList<>();
        List<Double> valueListEffGap = new ArrayList<>();
        List<Double> valueDevFromIdeal = new ArrayList<>();
        filteredDistrictings.forEach(d -> {
            valueListEqualTVAP.add(d.getEqualVap());
            valueListEqualPop.add(d.getEqualPopulation());
            valueListComp.add(d.getCompactness());
            valueListEffGap.add(d.getEfficiencyGap());
            valueDevFromIdeal.add(d.getDeviationFromIdealPop());
        });

        double minValueEqualTVAP = Collections.min(valueListEqualTVAP);
        double maxValueEqualTVAP = Collections.max(valueListEqualTVAP);
        double minValueEqualPop = Collections.min(valueListEqualPop);
        double maxValueEqualPop = Collections.max(valueListEqualPop);
        double minValueComp = Collections.min(valueListComp);
        double maxValueComp = Collections.max(valueListComp);
        double minValueEffGap = Collections.min(valueListEffGap);
        double maxValueEffGap = Collections.max(valueListEffGap);
        double maxValueideal = Collections.max(valueDevFromIdeal);

        filteredDistrictings.forEach(d -> {
            if(maxValueEqualTVAP == 0.0) {
                d.setEqualVapNormalized(0.0);
            }
            else if (minValueEqualTVAP == maxValueEqualTVAP) {
                d.setEqualVapNormalized(1.0);
            }
            else {
                double currentValue = d.getEqualVap();
                double normalizedValue = (currentValue - minValueEqualTVAP) / (maxValueEqualTVAP - minValueEqualTVAP);
                d.setEqualVapNormalized(normalizedValue);
            }

            if(maxValueEqualPop == 0.0) {
                d.setEqualPopulationNormalized(0.0);
            }
            else if (minValueEqualPop == maxValueEqualPop) {
                d.setEqualPopulationNormalized(1.0);
            }
            else {
                double currentValue = d.getEqualPopulation();
                double normalizedValue = (currentValue - minValueEqualPop) / (maxValueEqualPop - minValueEqualPop);
                d.setEqualPopulationNormalized(normalizedValue);
            }

            if(maxValueComp == 0.0) {
                d.setCompactnessNormalized(0.0);
            }
            else if (minValueComp == maxValueComp) {
                d.setCompactnessNormalized(1.0);
            }
            else {
                double currentValue = d.getCompactness();
                double normalizedValue = (currentValue - minValueComp) / (maxValueComp - minValueComp);
                d.setCompactnessNormalized(normalizedValue);
            }

            if(maxValueEffGap == 0.0) {
                d.setEfficiencyGapNormalized(0.0);
            }
            else if (minValueEffGap == maxValueEffGap) {
                d.setEfficiencyGapNormalized(1.0);
            }
            else {
                double currentValue = d.getEfficiencyGap();
                double normalizedValue = (currentValue - minValueEffGap) / (maxValueEffGap - minValueEffGap);
                d.setEfficiencyGapNormalized(normalizedValue);
            }

            if(maxValueideal == 0.0) {
                d.setDeviationFromIdealPopNormalized(0.0);
            }
            else {
                double currentValue = d.getDeviationFromIdealPop();
                double normalizedValue = currentValue  / maxValueideal;
                d.setDeviationFromIdealPopNormalized(normalizedValue);
            }

        });
    }

    public void calculateAreaPairDeviationDistrictings(){
        List<Districting> sorted = new ArrayList<>(ofDistrictings);
        sorted.sort(Comparator.comparing(Districting::getDeviationFromAvg));

        areaPairDeviationDistrictings = new ArrayList<>();
        areaPairDeviationDistrictings.add(sorted.get(0));
        areaPairDeviationDistrictings.add(sorted.get(sorted.size() / 6));
        areaPairDeviationDistrictings.add(sorted.get(sorted.size() / 6 * 2));
        areaPairDeviationDistrictings.add(sorted.get(sorted.size() / 6 * 3));
        areaPairDeviationDistrictings.add(sorted.get(sorted.size() / 6 * 4));
        areaPairDeviationDistrictings.add(sorted.get(sorted.size() / 6 * 5));
        areaPairDeviationDistrictings.add(sorted.get(sorted.size() - 1));
    }

    public void calculateClosestToEnactedDistrictings(){
        closestToEnactedDistrictings = filteredDistrictings.stream()
                                                           .sorted(Comparator.comparing(Districting::getOfScore).reversed())
                                                           .limit(100)
                                                           .sorted(Comparator.comparing(Districting::getDeviationFromEnactedArea).reversed())
                                                           .limit(10).collect(Collectors.toList());
    }

    public void calculateClosestToAverageDistrictings(){
        closestToAverageDistrictings = filteredDistrictings.stream()
                .sorted(Comparator.comparing(Districting::getOfScore).reversed())
                .limit(100)
                .sorted(Comparator.comparing(Districting::getDeviationFromIdealPopNormalized).reversed())
                .limit(5).collect(Collectors.toList());
    }

    public List<DistrictingSummary> applyMeasures() {
        //list of  min and max of non-normalized deviations values
        //each index represent one district and last index represent districting
        List<Double> devFromEnactedAreaListMin = new ArrayList<>();
        List<Double> devFromEnactedAreaListMax = new ArrayList<>();
        List<Double> devFromEnactedPopListMin = new ArrayList<>();
        List<Double> devFromEnactedPopListMax = new ArrayList<>();
        List<Double> devFromAverageListMin = new ArrayList<>();
        List<Double> devFromAverageListMax = new ArrayList<>();

        if (filteredDistrictings.size() != 0){
            int numDistricts = filteredDistrictings.get(0).getDistricts().size();
            for (int i = 0; i < numDistricts + 1; i++) {
                devFromEnactedAreaListMin.add(Double.POSITIVE_INFINITY);
                devFromEnactedAreaListMax.add(0.0);
                devFromEnactedPopListMin.add(Double.POSITIVE_INFINITY);
                devFromEnactedPopListMax.add(0.0);
                devFromAverageListMin.add(Double.POSITIVE_INFINITY);
                devFromAverageListMax.add(0.0);
            }
        }

        //calculating deviation using sum of square
        filteredDistrictings.forEach(d -> {
            calculateDevFromEnacted(d, devFromEnactedAreaListMin, devFromEnactedAreaListMax, devFromEnactedPopListMin, devFromEnactedPopListMax);
            calculateDevFromAverage(d, devFromAverageListMin, devFromAverageListMax);
            calculateDevFromIdeal(d);
        });

        //normalizing data for deviation from enacted and average to 0.0 - 1.0
        normalizeData();
        List<Double> OfScoreList = new ArrayList<>();
        filteredDistrictings.forEach(d -> {
            normalizeDistrictdeviationsArea(d, devFromEnactedAreaListMin, devFromEnactedAreaListMax, devFromEnactedPopListMin, devFromEnactedPopListMax);
            normalizeDistrictAverageDeviations(d, devFromAverageListMin, devFromAverageListMax);
            calculateOfScore(d, OfScoreList);
        });
        double minValue = Collections.min(OfScoreList);
        double maxValue = Collections.max(OfScoreList);
        filteredDistrictings.forEach(d -> {
            if (maxValue == 0.0) {
                d.setOfScore(0.0);
            }
            else if (minValue == maxValue) {
                d.setOfScore(1.0);
            }
            else {
                double OFValue = d.getOfScore();
                double normalizedValue = (OFValue - minValue) / (maxValue - minValue);
                d.setOfScore(normalizedValue);
            }
        });



        ofDistrictings = new ArrayList<>(filteredDistrictings);
        ofDistrictings.sort(Comparator.comparing(Districting::getOfScore));
        ofDistrictings = ofDistrictings.subList(ofDistrictings.size()-10, ofDistrictings.size());


        calculateAreaPairDeviationDistrictings();
        calculateClosestToEnactedDistrictings();
        calculateClosestToAverageDistrictings();

        ofDistrictings.addAll(closestToEnactedDistrictings);
        ofDistrictings.addAll(areaPairDeviationDistrictings);
        ofDistrictings.addAll(closestToAverageDistrictings);

        districtingSummaries = ofDistrictings.stream().map(districting -> {
            districting.createSummary(constraints.getPopEqualityType());
            return districting.getSummary();
        }).collect(Collectors.toList());

        return districtingSummaries;
    }

    public void calculateDevMajMinDistricting(){
        List<String> popType = new ArrayList<>();
        Minority minority = constraints.getBoxplotMinority();
        ArrayList<Integer> districtPops = new ArrayList<>();

        if (constraints.getPopEqualityType().equals("TPOP")) {
            popType.add("totalPop");
            popType.add("blackPop");
            popType.add("hispanicPop");
            popType.add("asianPop");
            popType.add("nativeAmericanPop");
        }
        else if (constraints.getPopEqualityType().equals("TVAP")) {
            popType.add("totalVap");
            popType.add("blackVap");
            popType.add("hispanicVap");
            popType.add("asianVap");
            popType.add("nativeAmericanVap");
        }

        ArrayList<Double> sumOfSquaresDistrictingList = new ArrayList<>();
        // Sort selected minority pop from smallest to largest from each district
        for (int i=0; i<filteredDistrictings.size(); i++){
            ArrayList<District> districts = new ArrayList<>(filteredDistrictings.get(i).getDistricts());
            ArrayList<Integer> minorityPopSorted = new ArrayList<>();

            for (int j=0; j<districts.size();j++){
                Map<String, Integer> demographics = districts.get(j).getDemographicPopulation();
                if (minority == Minority.AFRICAN_AMERICAN){
                    minorityPopSorted.add(demographics.get(popType.get(1))); //black
                }
                else if (minority == Minority.HISPANIC){
                    minorityPopSorted.add(demographics.get(popType.get(2))); //hispanic
                }
                else if (minority == Minority.ASIAN)
                    minorityPopSorted.add(demographics.get(popType.get(3))); //asian
                else{
                    minorityPopSorted.add(demographics.get(popType.get(4))); //nativeAmerican
                }
            }
            Collections.sort(minorityPopSorted);

            double tss = 0;
            for (int k=0; k<minorityPopSorted.size(); k++){
                tss += Math.pow((double)minorityPopSorted.get(k)-(double)avgDistrictingMinorityPop.get(k), 2);
            }
            sumOfSquaresDistrictingList.add(tss);
        }
        // Normalize sumOfSquaresDistrictingList and set deviation for each districting
        double min = Collections.min(sumOfSquaresDistrictingList);
        double max = Collections.max(sumOfSquaresDistrictingList);
        for (int i=0; i<filteredDistrictings.size(); i++) {
            double result = (sumOfSquaresDistrictingList.get(i) - min) / (max-min);
            filteredDistrictings.get(i).setDeviationFromMajMin(result);
        }
    }
}
