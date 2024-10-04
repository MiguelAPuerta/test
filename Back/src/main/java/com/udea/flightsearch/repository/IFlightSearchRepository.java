package com.udea.flightsearch.repository;

import com.udea.flightsearch.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Repository
public interface IFlightSearchRepository extends JpaRepository<Flight, Long> {

    //HU 1.1.1 Busqueda por origen y destino
    // Buscar por el nombre del aeropuerto de origen y el de destino, tambien ordenando por fecha de salida
    List<Flight> findByOrigin_City_NameContainingIgnoreCaseAndDestination_City_NameContainingIgnoreCaseOrderByDepartureTimeAsc(String originName, String destinationName);

    //HU 1.1.2 Busqueda por fecha
    // Buscar por el nombre del aeropuerto de origen, el aeropuerto de destino y fecha de salida
    List<Flight> findByOrigin_City_NameContainingIgnoreCaseAndDestination_City_NameContainingIgnoreCaseAndDepartureTimeEquals(String originName, String destinationName, LocalDate departureTime);

    // Buscar por el nombre del aeropuerto de origen, el aeropuerto de destino y fechas de salida y llegada
    List<Flight> findByOrigin_City_NameContainingIgnoreCaseAndDestination_City_NameContainingIgnoreCaseAndDepartureTimeEqualsAndArrivalTimeEquals(
            String originName, String destinationName, LocalDate departureTime, LocalDate arrivalTime);

    //HU 1.3.1 Ordenamiento por precio
    // Buscar por el nombre del aeropuerto de origen y el de destino, tambien ordenado por precio
    List<Flight> findByOrigin_City_NameContainingIgnoreCaseAndDestination_City_NameContainingIgnoreCaseOrderByPriceAsc(
            String originName, String destinationName);

    // Buscar por el nombre del aeropuerto de origen, el aeropuerto de destino y fecha de salida, tambien ordenado por precio
    List<Flight> findByOrigin_City_NameContainingIgnoreCaseAndDestination_City_NameContainingIgnoreCaseAndDepartureTimeEqualsOrderByPriceAsc(
            String originName, String destinationName, LocalDate departureTime);

    // Buscar por el nombre del aeropuerto de origen, el aeropuerto de destino y fechas de salida y llegada, tambien ordenado por precio
    List<Flight> findByOrigin_City_NameContainingIgnoreCaseAndDestination_City_NameContainingIgnoreCaseAndDepartureTimeEqualsAndArrivalTimeEqualsOrderByPriceAsc(
            String originName, String destinationName, LocalDate departureTime, LocalDate arrivalTime);

}
