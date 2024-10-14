package com.udea.flightsearch.controller;

import com.udea.flightsearch.model.*;
import com.udea.flightsearch.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

@Controller
public class FlightMutationController {

    @Autowired
    private FlightSearchService flightSearchService;
    @Autowired
    private AirportService airportService;
    @Autowired
    private PlaneService planeService;

    @MutationMapping
    public Flight createFlight(@Argument String flightNumber,
                               @Argument Long originId,
                               @Argument Long destinationId,
                               @Argument String departureDate,
                               @Argument String arrivalDate,
                               @Argument Long planeId,
                               @Argument BigDecimal price,
                               @Argument BigDecimal taxPercentage,
                               @Argument BigDecimal surchargePercentage,
                               @Argument Boolean isCanceled,
                               @Argument Integer sellSeats) {

        Flight flight = new Flight();
        flight.setFlightNumber(flightNumber);
        flight.setOrigin(airportService.getAirportById(originId));
        flight.setDestination(airportService.getAirportById(destinationId));
        flight.setDepartureDate(Timestamp.valueOf(departureDate));
        flight.setArrivalDate(Timestamp.valueOf(arrivalDate));
        flight.setPlane(planeService.getPlaneById(planeId));
        flight.setPrice(price);
        flight.setTaxPercentage(taxPercentage);
        flight.setSurchargePercentage(surchargePercentage);
        flight.setCanceled(isCanceled);
        flight.setSellSeats(sellSeats);
        return flightSearchService.createOrUpdateFlight(flight);
    }

    @MutationMapping
    public Flight updateFlight(@Argument Long id,
                               @Argument String flightNumber,
                               @Argument Long originId,
                               @Argument Long destinationId,
                               @Argument String departureDate,
                               @Argument String arrivalDate,
                               @Argument Long planeId,
                               @Argument BigDecimal price,
                               @Argument BigDecimal taxPercentage,
                               @Argument BigDecimal surchargePercentage,
                               @Argument Boolean isCanceled,
                               @Argument Integer sellSeats) {

        Optional<Flight> existingFlightOpt = flightSearchService.getFlightById(id);
        if (existingFlightOpt.isPresent()) {
            Flight existingFlight = existingFlightOpt.get();
            if (flightNumber != null) existingFlight.setFlightNumber(flightNumber);
            if (originId != null) existingFlight.setOrigin(airportService.getAirportById(originId));
            if (destinationId != null) existingFlight.setDestination(airportService.getAirportById(destinationId));
            if (departureDate != null) existingFlight.setDepartureDate(Timestamp.valueOf(departureDate));
            if (arrivalDate != null) existingFlight.setArrivalDate(Timestamp.valueOf(arrivalDate));
            if (planeId != null) existingFlight.setPlane(planeService.getPlaneById(planeId));
            if (price != null) existingFlight.setPrice(price);
            if (taxPercentage != null) existingFlight.setTaxPercentage(taxPercentage);
            if (surchargePercentage != null) existingFlight.setSurchargePercentage(surchargePercentage);
            if (isCanceled != null) existingFlight.setCanceled(isCanceled);
            if (sellSeats != null) existingFlight.setSellSeats(sellSeats);

            return flightSearchService.createOrUpdateFlight(existingFlight);
        } else {
            throw new RuntimeException("Flight not found with id: " + id);
        }
    }

    @MutationMapping
    public boolean deleteFlight(@Argument Long id) {
        flightSearchService.deleteFlight(id);
        return true;
    }
}