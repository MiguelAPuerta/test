package com.udea.flightsearch.repository;

import com.udea.flightsearch.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlightSearchRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {
}
