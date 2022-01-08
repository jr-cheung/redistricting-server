package com.example.demo;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class StateController {

    @Autowired
    private IncumbentRepository incumbentRepository;

    @GetMapping("/statedetails")
    public org.json.simple.JSONObject getStateDetails(@RequestParam String stateName) {
        JSONParser parser = new JSONParser();
        String pathToStateData = "src/main/java/com/example/demo/statedata.json";
        try {
            Object obj = parser.parse(new FileReader(pathToStateData));
            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;
            if (stateName.equals("Maryland")) {
                return (org.json.simple.JSONObject) jsonObject.get("Maryland");
            } else if (stateName.equals("Virginia")) {
                return (org.json.simple.JSONObject) jsonObject.get("Virginia");
            } else {
                return (org.json.simple.JSONObject) jsonObject.get("Arizona");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/getIncumbentsByState")
    public List<Incumbent> getIncumbents(@RequestParam("stateName") String stateName, HttpServletRequest request){
        return incumbentRepository.findIncumbentsByState(stateName);
    }

    @GetMapping("/getJobsByState")
    public List<org.json.simple.JSONObject> getJobsByState(@RequestParam("stateName") String stateName, HttpServletRequest request){
        JSONParser parser = new JSONParser();
        List<org.json.simple.JSONObject> resultList = new ArrayList<>();
        String pathToStateData = "src/main/java/com/example/demo/assets/Jobs.json";
        try {
            Object obj = parser.parse(new FileReader(pathToStateData));
            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;
            long jobTotal = (long) jsonObject.get("TotalJobs");
            for (long i = 1; i < jobTotal+1; i++) {
                String jobKey = String.valueOf(i);
                org.json.simple.JSONObject job = (org.json.simple.JSONObject) jsonObject.get(jobKey);
                if (stateName.equals(job.get("state"))) {
                    resultList.add(job);
                }
            }
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/statePrecinctsGeo")
    public org.json.simple.JSONObject getStatePrecinctsGeo(@RequestParam String stateName) {
        JSONParser parser = new JSONParser();
        String pathToJSON = "src/main/java/com/example/demo/assets/";
        if (stateName.equals("Maryland")) {
            pathToJSON += "Maryland/mdPrecincts.json";
        } else if (stateName.equals("Virginia")) {
            pathToJSON += "Virginia/vaPrecincts.json";
        } else {
            pathToJSON += "Arizona/azPrecincts.json";
        }
        try {
            Object obj = parser.parse(new FileReader(pathToJSON));
            return (org.json.simple.JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/stateCountiesGeo")
    public org.json.simple.JSONObject getStateCountiesGeo(@RequestParam String stateName) {
        JSONParser parser = new JSONParser();
        String pathToJSON = "src/main/java/com/example/demo/assets/";
        if (stateName.equals("Maryland")) {
            pathToJSON += "Maryland/mdCounties.json";
        } else if (stateName.equals("Virginia")) {
            pathToJSON += "Virginia/vaCounties.json";
        } else {
            pathToJSON += "Arizona/azCounties.json";
        }
        try {
            Object obj = parser.parse(new FileReader(pathToJSON));
            return (org.json.simple.JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/enactedDistrictingGeo")
    public org.json.simple.JSONObject getEnactedDistrictingGeo(@RequestParam String stateName) {
        JSONParser parser = new JSONParser();
        String pathToJSON = "src/main/java/com/example/demo/assets/";
        if (stateName.equals("Maryland")) {
            pathToJSON += "Maryland/mdDistricts.json";
        } else if (stateName.equals("Virginia")) {
            pathToJSON += "Virginia/vaDistricts.json";
        } else {
            pathToJSON += "Arizona/azDistricts.json";
        }
        try {
            Object obj = parser.parse(new FileReader(pathToJSON));
            return (org.json.simple.JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public org.json.simple.JSONObject getJobByID(@RequestParam String jobId) {
        JSONParser parser = new JSONParser();
        long jobIdLong = Long.parseLong(jobId);
        List<org.json.simple.JSONObject> resultList = new ArrayList<>();
        String pathToStateData = "src/main/java/com/example/demo/assets/Jobs.json";
        try {
            Object obj = parser.parse(new FileReader(pathToStateData));
            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;
            long jobTotal = (long) jsonObject.get("TotalJobs");
            for (long i = 1; i < jobTotal+1; i++) {
                String jobKey = String.valueOf(i);
                org.json.simple.JSONObject job = (org.json.simple.JSONObject) jsonObject.get(jobKey);
                if (jobIdLong == (long) job.get("id")) {
                    System.out.println("GOT IT");
                    return job;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/setjob")
    public Job setJob(@RequestParam String jobId, HttpSession session) {
        org.json.simple.JSONObject selectedJob = getJobByID(jobId);
        long id = (long) selectedJob.get("id");
        String state = (String) selectedJob.get("state");
        org.json.simple.JSONObject mgggParams = (org.json.simple.JSONObject) selectedJob.get("mgggParams");
        long numDistrictings = (long) selectedJob.get("numDistrictings");
        String tableName = (String) selectedJob.get("districtingTableName");
        Job filledJob = new Job(id,  state, mgggParams, numDistrictings,  tableName);
        filledJob.setStateObj((State) session.getAttribute("activeState"));
        session.setAttribute("currentJob", filledJob);
        Job sessionInfo = (Job) session.getAttribute("currentJob");
        return sessionInfo;
    }

    @GetMapping("/setstate")
    public State setState(@RequestParam String stateName, HttpSession session) {
        JSONParser parser = new JSONParser();
        String pathToStateData = "src/main/java/com/example/demo/statedata.json";
        try {
            Object obj = parser.parse(new FileReader(pathToStateData));
            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;
            org.json.simple.JSONObject stateJSON = (org.json.simple.JSONObject) jsonObject.get(stateName);
            long pop = (long) stateJSON.get("totalPop");
            long area = (long) stateJSON.get("totalArea");
            long minorityPop = (long) stateJSON.get("minorityPopTotal");
            double minorityPercentage = (double) stateJSON.get("percentMinority");
            long numDistricts = (long) stateJSON.get("districtsTotal");
            long tVapPop = (long) stateJSON.get("totalVap");
            List<Minority> minorityGroups = (List<Minority>) stateJSON.get("topMinorities");
            List<Map<String, Long>> enactedDisricting = (List<Map<String, Long>>) stateJSON.get("enacted");

            org.json.simple.JSONObject historicalVoting = (org.json.simple.JSONObject) stateJSON.get("historicalVoting");
            List<org.json.simple.JSONObject> jobs = getJobsByState(stateName, null);
            List<Incumbent> incumbents = getIncumbents(stateName, null);
            State tempState = new State(stateName, pop, area, minorityPop, minorityPercentage, numDistricts, minorityGroups, incumbents, historicalVoting, jobs, enactedDisricting, tVapPop);
            session.setAttribute("activeState", tempState);
            State sessionInfo = (State) session.getAttribute("activeState");
            return sessionInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
