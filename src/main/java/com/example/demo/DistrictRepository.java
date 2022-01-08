package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Collection;


// Not tested
public interface DistrictRepository extends CrudRepository<District, Integer>{
    @Query(value = "SELECT * FROM padres.MarylandDistricts3 WHERE districtingId IN ?1", nativeQuery = true)
    List<District> findMarylandDistrictsbyDistrictingIds(Collection<Long> districtingIds);

    @Query(value = "SELECT * FROM padres.VirginiaDistricts3 WHERE districtingId IN ?1", nativeQuery = true)
    List<District> findVirginiaDistrictsbyDistrictingIds(Collection<Long> districtingIds);

    @Query(value = "SELECT * FROM padres.ArizonaDistricts WHERE districtingId IN ?1", nativeQuery = true)
    List<District> findArizonaDistrictsbyDistrictingIds(Collection<Long> districtingIds);
}
