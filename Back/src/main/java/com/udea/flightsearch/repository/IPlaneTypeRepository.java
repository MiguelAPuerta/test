package com.udea.flightsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.udea.flightsearch.model.PlaneType;

@Repository
public interface IPlaneTypeRepository extends JpaRepository<PlaneType, Long> {
}
