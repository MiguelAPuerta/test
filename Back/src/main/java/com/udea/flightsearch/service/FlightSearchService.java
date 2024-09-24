package com.udea.flightsearch.service;

import com.udea.flightsearch.model.Flight;
import com.udea.flightsearch.repository.IFlightSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightSearchService {

    @Autowired
    private IFlightSearchRepository IFlightSearchRepository;

    public List<Flight> searchFlights(String originName, String destinationName, LocalDate departureTime, LocalDate arrivalTime) {

        String key = (originName != null ? "1" : "0") +
                (destinationName != null ? "1" : "0") +
                (departureTime != null ? "1" : "0") +
                (arrivalTime != null ? "1" : "0");


        switch (key) {

            case "1100":
                return IFlightSearchRepository.findByOrigin_NameContainingIgnoreCaseAndDestination_NameContainingIgnoreCaseOrderByDepartureTimeAsc(originName, destinationName);

            case "1110":
                return IFlightSearchRepository.findByOrigin_NameContainingIgnoreCaseAndDestination_NameContainingIgnoreCaseAndDepartureTimeGreaterThanEqualOrderByDepartureTimeAsc(originName, destinationName, departureTime);

            case "1111":
                return IFlightSearchRepository.findByOrigin_NameContainingIgnoreCaseAndDestination_NameContainingIgnoreCaseAndDepartureTimeGreaterThanAndArrivalTimeLessThanOrderByDepartureTimeAsc(originName, destinationName, departureTime, arrivalTime);

            default:
                throw new IllegalArgumentException("No se encontró una combinación válida de parámetros.");

        }
    }
}

