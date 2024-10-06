package com.udea.flightsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.udea.flightsearch.model.Flight;
import com.udea.flightsearch.service.FlightSearchService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class FlightController {

    @Autowired
    private FlightSearchService flightSearchService;

    @QueryMapping
    public List<Flight> getAllFlights() {
        return flightSearchService.getAllFlights();
    }

    @QueryMapping
    public Optional<Flight> getFlightById(@Argument Long id) {
        return flightSearchService.getFlightById(id);
    }

    @QueryMapping
    public List<Flight> searchFlights(
            @Argument String originName,
            @Argument String destinationName,
            @Argument LocalDate departureTime,
            @Argument LocalDate arrivalTime,
            @Argument boolean orderByDepartureTimeAsc,
            @Argument boolean orderByPriceAsc) {
        return flightSearchService.searchFlights(
                originName,
                destinationName,
                departureTime,
                arrivalTime,
                orderByDepartureTimeAsc,
                orderByPriceAsc);
    }
}
