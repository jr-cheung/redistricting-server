package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CountyRepository extends CrudRepository<County, Long> {

    @Query(value = "SELECT * FROM padres.MarylandCounties", nativeQuery = true)
    List<County> findMarylandCounties();

    @Query(value = "SELECT * FROM padres.VirginiaCounties", nativeQuery = true)
    List<County> findVirginiaCounties();

    @Query(value = "SELECT * FROM padres.ArizonaCounties", nativeQuery = true)
    List<County> findArizonaCounties();
}
