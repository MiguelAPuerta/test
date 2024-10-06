package com.udea.flightsearch.service;

import com.udea.flightsearch.Specification.FlightSpecification;
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

    public List<Flight> searchFlights(String originName,
                                      String destinationName,
                                      LocalDate departureTime,
                                      LocalDate arrivalTime,
                                      boolean orderByDepartureTimeAsc,
                                      boolean orderByPriceAsc
    ) {
        // Use the specification method to search flights
        return flightRepository.findAll(
                FlightSpecification.filterBy(originName, destinationName, departureTime, arrivalTime, orderByDepartureTimeAsc, orderByPriceAsc)
        );
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

