package com.udea.flightsearch.repository;

import com.udea.flightsearch.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IFlightSearchRepository extends JpaRepository<Flight, Long> {

    // Buscar por el nombre del aeropuerto de origen y el de destino, tambien ordenando por fecha de salida
    List<Flight> findByOrigin_NameContainingIgnoreCaseAndDestination_NameContainingIgnoreCaseOrderByDepartureTimeAsc(String originName, String destinationName);

    // Buscar por el nombre del aeropuerto de origen, el aeropuerto de destino y fecha de salida, tambien ordenando por fecha de salida
    List<Flight> findByOrigin_NameContainingIgnoreCaseAndDestination_NameContainingIgnoreCaseAndDepartureTimeGreaterThanEqualOrderByDepartureTimeAsc(String originName, String destinationName, LocalDate departureTime);

    // Buscar por el nombre del aeropuerto de origen, el aeropuerto de destino y fechas de salida y llegada, tambien ordenando por fecha de salida
    List<Flight> findByOrigin_NameContainingIgnoreCaseAndDestination_NameContainingIgnoreCaseAndDepartureTimeGreaterThanAndArrivalTimeLessThanOrderByDepartureTimeAsc(
            String originName, String destinationName, LocalDate departureTime, LocalDate arrivalTime);

}
