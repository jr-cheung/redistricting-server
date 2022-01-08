package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

// Not tested
public interface DistrictingRepository extends CrudRepository<Districting, Integer> {
    @Query(value = "SELECT * FROM padres.MarylandDistrictings3 WHERE id IN (SELECT MIN(id) FROM padres.MarylandDistrictings3 GROUP BY compactness, efficiencyGap, equalVAP, equalPopulation)", nativeQuery = true)
    List<Districting> findMarylandDistrictings();

    @Query(value = "SELECT * FROM padres.VirginiaDistrictings3 WHERE id IN (SELECT MIN(id) FROM padres.VirginiaDistrictings3 GROUP BY compactness, efficiencyGap, equalVAP, equalPopulation)", nativeQuery = true)
    List<Districting> findVirginiaDistrictings();

    @Query(value = "SELECT * FROM padres.ArizonaDistrictings WHERE id IN (SELECT MIN(id) FROM padres.ArizonaDistrictings GROUP BY compactness, efficiencyGap, equalVAP, equalPopulation) AND id < 5000", nativeQuery = true)
    List<Districting> findArizonaDistrictings();
}
