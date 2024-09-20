package com.udea.vueloudea.service;

import com.udea.vueloudea.model.Flight;
import com.udea.vueloudea.repository.FlightSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightSearchService {

    @Autowired
    private FlightSearchRepository flightSearchRepository;

    public List<Flight> searchFlights(String origin, String destination, LocalDate departureTime, LocalDate arrivalTime) {

        String key = (origin != null ? "1" : "0") +
                (destination != null ? "1" : "0") +
                (departureTime != null ? "1" : "0") +
                (arrivalTime != null ? "1" : "0");


        switch (key) {

            case "1100":
                return flightSearchRepository.findByOriginContainingIgnoreCaseAndDestinationContainingIgnoreCaseOrderByDepartureTimeAsc(origin, destination);

            case "1110":
                return flightSearchRepository.findByOriginContainingIgnoreCaseAndDestinationContainingIgnoreCaseAndDepartureTimeGreaterThanEqualOrderByDepartureTimeAsc(origin, destination, departureTime);

            case "1111":
                return flightSearchRepository.findByOriginContainingIgnoreCaseAndDestinationContainingIgnoreCaseAndDepartureTimeGreaterThanAndArrivalTimeLessThanOrderByDepartureTimeAsc(origin, destination, departureTime, arrivalTime);

            default:
                throw new IllegalArgumentException("No se encontró una combinación válida de parámetros.");

        }
    }
}

