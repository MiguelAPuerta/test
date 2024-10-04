package com.udea.flightsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.udea.flightsearch.model.SeatClass;

@Repository
public interface ISeatClassRepository extends JpaRepository<SeatClass, Long> {
}