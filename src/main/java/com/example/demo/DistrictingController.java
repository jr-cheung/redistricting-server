package com.example.demo;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.union.UnaryUnionOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wololo.geojson.*;
import org.wololo.jts2geojson.GeoJSONReader;
import org.wololo.jts2geojson.GeoJSONWriter;

import javax.servlet.http.HttpSession;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class DistrictingController {
    @Autowired
    private PrecinctRepository precinctRepository;

    @GetMapping("/districtingsummary")
    public DistrictingSummary getDistrictingDetails(@RequestParam long distrinctingId, HttpSession session) {
        Job testJob = (Job) session.getAttribute("currentJob");
        Districting districting = testJob.getDistrictingById(distrinctingId);
        return districting.getSummary();
    }

    @GetMapping("/districtingdisplay")
    public GeoJSON compileDistrictGeometries(@RequestParam int districtingID, HttpSession session) {
        Job currentJob = (Job) session.getAttribute("currentJob");
        State stateObj = (State) session.getAttribute("activeState");

        List<District> districts = currentJob.getDistrictingById(districtingID).getDistricts();


        List<Feature> featureList = new ArrayList<>();


        for (District d : districts) {
            List<Long> districtPrecincts = new ArrayList<>();
            String districtPrecinctString = d.getPrecincts();
            districtPrecinctString = districtPrecinctString.replace("[", "").replace("]", "").replace(" ", "");
            String[] numbers = districtPrecinctString.split(",");
            for (int j = 0; j < numbers.length; j++) {
                districtPrecincts.add(Long.parseLong(numbers[j]));
            }

            List<String> geometryStrings = new ArrayList<>();
            String activeState = stateObj.getName();
            if (activeState.equals("Maryland")) {
                geometryStrings = precinctRepository.findMarylandPrecinctsById(districtPrecincts);
            } else if (activeState.equals("Virginia")) {
                geometryStrings = precinctRepository.findVirginiaPrecinctsById(districtPrecincts);
            } else if (activeState.equals("Arizona")) {
                geometryStrings = precinctRepository.findArizonaPrecinctsById(districtPrecincts);
            }
            GeoJSONReader reader = new GeoJSONReader();
            List<Geometry> geometries = geometryStrings.parallelStream().map(s -> reader.read(s).buffer(0)).collect(Collectors.toList());
            UnaryUnionOp unionOp = new UnaryUnionOp(geometries);
            Geometry geo = unionOp.union();
            org.wololo.geojson.Geometry districtGeometry = new GeoJSONWriter().write(geo);

            HashMap<String, Object> properties = new HashMap<>(d.getDemographicPopulation());
            properties.put("area", d.getArea());
            properties.put("compactness", d.getCompactness());
            properties.put("deviationFromAverage", d.getDeviationFromAverage());
            properties.put("deviationFromEnactedArea", d.getDeviationFromEnactedArea());
            properties.put("deviationFromEnactedPop", d.getDeviationFromEnactedPop());
            properties.put("perimeter", d.getPerimeter());
            properties.put("deviationFromIdealPopulation", d.getDeviationFromIdealPop());
//            properties.put("party", d.getPoliticalParty());
//            properties.put("incumbent", d.getIncumbent().getIncumbentName());

            featureList.add(new Feature(districtGeometry, properties));

        }

        GeoJSON districtingGeoJson = new GeoJSONWriter().write(featureList);

//        try {
//            PrintWriter pw = new PrintWriter("marylandDistricting.json");
//            pw.println(districtingGeoJson.toString());
//            pw.flush();
//            pw.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        return districtingGeoJson;
    }

    /* **still need implementation**
    @GetMapping("/boxplotdetail")
    public Boxplot getBoxplotDetails(@RequestParam long distrinctingId, Minority race) {
        return new Boxplot();
    }
     */
}
