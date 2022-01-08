package com.example.demo;

import java.util.Collection;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class OFController {
    @GetMapping("/ofsummary")
    public List<DistrictingSummary> applyObjectiveFunction(HttpSession session, @RequestParam double polsPopComp, @RequestParam double popEqual, @RequestParam double splitCounty, @RequestParam double devEnactedArea, @RequestParam double devEnactedpop, @RequestParam double devAverage, @RequestParam double polFair) {
        Job currentJob = (Job) session.getAttribute("currentJob");
        ObjectiveFunctionFilter ofFilter = new ObjectiveFunctionFilter(polsPopComp, popEqual, splitCounty, devEnactedArea, devEnactedpop, devAverage, polFair);
        currentJob.setObjectiveFunction(ofFilter);
        List<DistrictingSummary> districtingSummaries = currentJob.applyMeasures();

        session.setAttribute("currentJob", currentJob);
        return districtingSummaries;
    }

    @GetMapping("/ofTest")
    public List<DistrictingSummary> objectiveFunctionTest(HttpSession session) {
        Job currentJob = (Job) session.getAttribute("currentJob");
        ObjectiveFunctionFilter ofFilter = new ObjectiveFunctionFilter(1.0, 0.0, 0.0, 0.0, 0, 0.0, 0.0);

        //System.out.println(currentJob.getFilteredDistrictings().get(0).getId());
        //System.out.println(currentJob.getFilteredDistrictings().get(0).getDeviationFromEnacted());
        currentJob.setObjectiveFunction(ofFilter);
        List<DistrictingSummary> districtingSummaries = currentJob.applyMeasures();
        //System.out.println(currentJob.getFilteredDistrictings().get(0).getDeviationFromEnacted());
        session.setAttribute("currentJob", currentJob);

        return districtingSummaries;

    }
}
