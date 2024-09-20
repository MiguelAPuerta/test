package com.udea.vueloudea.repository;

import com.udea.vueloudea.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightSearchRepository extends JpaRepository<Flight, Long> {

    // Buscar por origen y destino, tambien ordenando por fecha de salida
    List<Flight> findByOriginContainingIgnoreCaseAndDestinationContainingIgnoreCaseOrderByDepartureTimeAsc(String origin , String destination);

    // Buscar por origen, destino y fecha de salida, tambien ordenando por fecha de salida
    List<Flight> findByOriginContainingIgnoreCaseAndDestinationContainingIgnoreCaseAndDepartureTimeGreaterThanEqualOrderByDepartureTimeAsc(String origin, String destination, LocalDate departureTime);

    // Buscar por origen, destino y fechas de salida y llegada, tambien ordenando por fecha de salida
    List<Flight> findByOriginContainingIgnoreCaseAndDestinationContainingIgnoreCaseAndDepartureTimeGreaterThanAndArrivalTimeLessThanOrderByDepartureTimeAsc(
            String origin, String destination, LocalDate departureTime, LocalDate arrivalTime);

}
