package com.udea.flightsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.udea.flightsearch.model.Scale;

@Repository
public interface IScaleRepository extends JpaRepository<Scale, Long> {
}