package com.example.demo;

import com.jayway.jsonpath.internal.function.numeric.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class FilterController {

    @Autowired
    private DistrictingRepository districtingRepository;
    @Autowired
    private DistrictRepository districtRepository;

    public Minority getMinorityValue(String parameter) {
        if (parameter.equals("0")) {
            return Minority.AFRICAN_AMERICAN;
        } else if (parameter.equals("1")) {
            return Minority.HISPANIC;
        } else if (parameter.equals("2")) {
            return Minority.ASIAN;
        } else if (parameter.equals("3")) {
            return Minority.NATIVE_AMERICAN;
        }
        return null;
    }

    @GetMapping("/filtersummary")
    public List<String> setConstraints( @RequestParam("popEqualityThreshold") String popEqualityThreshold, @RequestParam("popEqualityType") String popEqualityType, @RequestParam("majMinDistricts") String majMinDistricts, @RequestParam("majMinThreshold") String majMinThreshold, @RequestParam("compactness") String compactness, @RequestParam(required = false, defaultValue = "" , name="incumbents") String[] incumbents, @RequestParam("boxplotMin") String boxplotMin,  HttpServletRequest request) {
        double convPopEqThreshold = Double.parseDouble(popEqualityThreshold);
        int convMajMinDistricts = Integer.parseInt(majMinDistricts);
        double convMajMinThreshold = Double.parseDouble(majMinThreshold);
        double convCompactness = Double.parseDouble(compactness);
        List<String> convIncumCollection= new ArrayList<>(Arrays.asList(incumbents));
        boolean convIncumProtection = convIncumCollection.size() >= 1;
        Minority convBpMin = getMinorityValue(boxplotMin);
        State stateObj = (State) request.getSession().getAttribute("activeState");
        Job currentJob = (Job) request.getSession().getAttribute("currentJob");
        ConstraintsFilter constraint = new ConstraintsFilter(convPopEqThreshold, popEqualityType, convMajMinDistricts, convMajMinThreshold, convCompactness, convIncumProtection, convIncumCollection, convBpMin);
        //add change here depending on state
        //popEqualityType == popType
        List<Districting> districtings = new ArrayList<Districting>();
        if (stateObj.getName().equals("Maryland")) {
             districtings = districtingRepository.findMarylandDistrictings();
        } else if (stateObj.getName().equals("Virginia")) {
            districtings = districtingRepository.findVirginiaDistrictings();
        } else if (stateObj.getName().equals("Arizona")) {
            districtings = districtingRepository.findArizonaDistrictings();
        }

        currentJob.setDistrictings(districtings);
        currentJob.setConstraints(constraint);
        int stateNumDistricts = (int) stateObj.getNumDistricts();
        List<String> summary = new ArrayList<String>();
        currentJob.applyConstraintsPart1(convBpMin, summary, request);

        ArrayList<Long> validIDS = new ArrayList<>();
        for (int i = 0; i < currentJob.getFilteredDistrictings().size(); i++) {
            validIDS.add(currentJob.getFilteredDistrictings().get(i).getId());
        }

        List<District> districts = new ArrayList<District>();
        if (stateObj.getName().equals("Maryland")) {
            districts = districtRepository.findMarylandDistrictsbyDistrictingIds(validIDS);
        } else if (stateObj.getName().equals("Virginia")) {
            districts = districtRepository.findVirginiaDistrictsbyDistrictingIds(validIDS);
        } else if (stateObj.getName().equals("Arizona")) {
            districts = districtRepository.findArizonaDistrictsbyDistrictingIds(validIDS);
        }
        System.out.println("Adding districts to their districtings");
        for (int i = 0; i < districts.size(); i += stateNumDistricts) {
            for (int a = 0; a < currentJob.getFilteredDistrictings().size(); a++) {
                if (districts.get(i).getDistrictingId() == currentJob.getFilteredDistrictings().get(a).getId()) {
                    for (int z = 0; z < stateNumDistricts; z++) {
                        currentJob.getFilteredDistrictings().get(a).addDistrict(districts.get(i + z));
                    }
                }
            }
        }
        List<String> constraintSummary = currentJob.applyConstraintsPart2(convBpMin, summary, request);

        // Calculate avg districting. Check list size first to avoid out of bounds error
        if (currentJob.getFilteredDistrictings().size() > 0) {
            currentJob.calculateAverageDistricting();
            currentJob.calculateDevMajMinDistricting();
        }

        request.getSession().setAttribute("currentJob", currentJob);
        return constraintSummary;
    }
    @GetMapping("/bpPointsForDistricting")
    public ArrayList<Double> getBPDistrictingPoints(@RequestParam("districtingID") long districtID, HttpServletRequest request) {
        Job currentJob = (Job) request.getSession().getAttribute("currentJob");
        Districting currentDistricting = currentJob.getDistrictingById(districtID);
        List<String> popType = new ArrayList<>();
        ArrayList<Double> distBPValues = currentJob.calculateBoxPlotValues(currentDistricting, currentJob.getConstraints().getBoxplotMinority());
        return distBPValues;
    }
    @GetMapping("/bpPointsForJob")
    public Boxplot getBPJobPoints(HttpServletRequest request) {
        Job currentJob = (Job) request.getSession().getAttribute("currentJob");
        return currentJob.getJobBoxplot();
    }
    @GetMapping("/bpPointsForEnacted")
    public List<Map<String, Long>> getBPEnactedPoints(HttpServletRequest request) {
        State currentState = (State) request.getSession().getAttribute("activeState");
        List<Map<String, Long>> enactedDistricting = currentState.getEnactedDistricting();
        return enactedDistricting;
    }
}
