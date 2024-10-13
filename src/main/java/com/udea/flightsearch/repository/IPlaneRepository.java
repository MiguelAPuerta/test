package com.udea.flightsearch.repository;

import com.udea.flightsearch.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlaneRepository extends JpaRepository<Plane, Long> {
}
