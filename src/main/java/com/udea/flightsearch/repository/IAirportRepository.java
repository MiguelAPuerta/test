package com.udea.flightsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.udea.flightsearch.model.Airport;

import java.util.Optional;

@Repository
public interface IAirportRepository extends JpaRepository<Airport, Long> {

    boolean existsByIataCode(String iataCode);

    Optional<Airport> findByIataCodeContainingIgnoreCase(String iataCode);

    void deleteByIataCode(String iataCode);
}
