package com.example.demo;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PrecinctRepository extends CrudRepository<Precinct, Long> {

    @Query(value = "SELECT geometry FROM padres.MarylandPrecincts WHERE id IN ?1", nativeQuery = true)
    List<String> findMarylandPrecinctsById(Collection<Long> precinctIds);

    @Query(value = "SELECT geometry FROM padres.VirginiaPrecincts WHERE number IN ?1", nativeQuery = true)
    List<String> findVirginiaPrecinctsById(Collection<Long> precinctIds);

    @Query(value = "SELECT geometry FROM padres.ArizonaPrecincts WHERE id IN ?1", nativeQuery = true)
    List<String> findArizonaPrecinctsById(Collection<Long> precinctIds);
}
