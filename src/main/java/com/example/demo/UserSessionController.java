package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSessionController {
    @Autowired
    private PrecinctRepository precinctRepository;
    @Autowired
    private CountyRepository countyRepository;
    @Autowired
    private IncumbentRepository incumbentRepository;
    @Autowired
    private DistrictingRepository districtingRepository;

    @GetMapping("/countiesDBTest")
    public long getCounties(@RequestParam("state") String state, HttpServletRequest request){
        return countyRepository.findMarylandCounties().size();
    }

    @GetMapping("/incumbentsDBTest")
    public long getIncumbents(@RequestParam("state") String state, HttpServletRequest request){
        return incumbentRepository.findIncumbents().size();
    }

    @GetMapping("/districtingsDBTest")
    public int getDistrictings(@RequestParam("tableName") String tableName, HttpServletRequest request){
        districtingRepository.findMarylandDistrictings();

        return -1;
    }
}
