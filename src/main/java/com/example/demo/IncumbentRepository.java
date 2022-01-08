package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IncumbentRepository extends CrudRepository<Incumbent, Long> {
    @Query(value = "SELECT * FROM padres.Incumbent", nativeQuery = true)
    List<Incumbent> findIncumbents();

    @Query(value = "SELECT * FROM padres.Incumbent WHERE state = ?1 ", nativeQuery = true)
    List<Incumbent> findIncumbentsByState(String state);
}
