package com.udea.flightsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.udea.flightsearch.model.Flight;
import com.udea.flightsearch.service.FlightSearchService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
            @Argument Integer passengerAmount,
            @Argument LocalDate departureDate,
            @Argument LocalDate arrivalDate,
            //Search between price values
            @Argument Double minimumPrice,
            @Argument Double maximumPrice,
            //Search between dates
            @Argument LocalDate minimumDate,
            @Argument LocalDate maximumDate,
            //Search between different times of a day
            @Argument String minimumTime,
            @Argument String maximumTime,
            //ordering values, true or false, ordering by departure time is true by default
            @Argument Boolean orderByDepartureDateAsc,
            @Argument Boolean orderByPriceAsc) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
        if (passengerAmount == null) {
            passengerAmount = 1;
        }
        if (minimumTime == null) {
            minimumTime = "0:00";
        }
        if (maximumTime == null) {
            maximumTime = "23:59";
        }
        if (orderByDepartureDateAsc == null) {
            orderByDepartureDateAsc = false;
        }
        if (orderByPriceAsc == null) {
            orderByPriceAsc = false;
        }
        return flightSearchService.searchFlights(
                originName,
                destinationName,
                passengerAmount,
                departureDate,
                arrivalDate,
                //Search between price values
                minimumPrice,
                maximumPrice,
                //Search between dates
                minimumDate,
                maximumDate,
                //Search between different times of a day
                LocalTime.parse(minimumTime, timeFormatter),
                LocalTime.parse(maximumTime, timeFormatter),
                //both of this values should be false or true, not null
                orderByDepartureDateAsc,
                orderByPriceAsc);
    }

    @QueryMapping
    public List<List<Flight>> searchRoundTrip(
            @Argument String originName,
            @Argument String destinationName,
            @Argument Integer passengerAmount,
            @Argument LocalDate departureDate,
            @Argument LocalDate arrivalDate)
            {
        return flightSearchService.searchRoundTrip(
                originName,
                destinationName,
                passengerAmount,
                departureDate,
                arrivalDate);
    }
}
