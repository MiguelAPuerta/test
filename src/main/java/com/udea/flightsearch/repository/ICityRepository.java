package com.udea.flightsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.udea.flightsearch.model.City;

import java.util.Optional;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {
}