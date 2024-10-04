package com.udea.flightsearch.service;

import com.udea.flightsearch.model.Flight;
import com.udea.flightsearch.repository.IFlightSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FlightSearchService {

    @Autowired
    private IFlightSearchRepository flightRepository;

    public List<Flight> searchFlights(String originName, String destinationName, LocalDate departureTime, LocalDate arrivalTime) {

        String key = (originName != null ? "1" : "0") +
                (destinationName != null ? "1" : "0") +
                (departureTime != null ? "1" : "0") +
                (arrivalTime != null ? "1" : "0");

        switch (key) {

            case "1100":
                return flightRepository.findByOrigin_City_NameContainingIgnoreCaseAndDestination_City_NameContainingIgnoreCaseOrderByDepartureTimeAsc(originName, destinationName);

            case "1110":
                return flightRepository.findByOrigin_City_NameContainingIgnoreCaseAndDestination_City_NameContainingIgnoreCaseAndDepartureTimeEquals(originName, destinationName, departureTime);

            case "1111":
                return flightRepository.findByOrigin_City_NameContainingIgnoreCaseAndDestination_City_NameContainingIgnoreCaseAndDepartureTimeEqualsAndArrivalTimeEquals(originName, destinationName, departureTime, arrivalTime);

            default:
                throw new IllegalArgumentException("No se encontró una combinación válida de parámetros.");
        }
    }

    // Funcion para obtener todos los vuelos
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Funcion para obtener un vuelo por ID
    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    // Funcion para crear o actualizar un vuelo
    public Flight createOrUpdateFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // Funcion para eliminar un vuelo por su ID
    public void deleteFlight(Long id) {
        // Manejo de error: verificar si existe antes de eliminar
        if (!flightRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete Flight. Not found with id: " + id);
        }
        flightRepository.deleteById(id);
    }
}

