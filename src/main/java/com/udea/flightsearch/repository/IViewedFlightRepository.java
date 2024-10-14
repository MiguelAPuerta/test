package com.udea.flightsearch.repository;

import com.udea.flightsearch.model.ViewedFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IViewedFlightRepository extends JpaRepository<ViewedFlight, Long> {
}