package com.udea.flightsearch.controller;

import com.udea.flightsearch.model.*;
import com.udea.flightsearch.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Controller
public class FlightMutationController {

    @Autowired
    private FlightSearchService flightSearchService;
    @Autowired
    private AirportService airportService;
    @Autowired
    private PlaneTypeService planeTypeService;

    @MutationMapping
    public Flight createFlight(@Argument Long flightNumber,
                               @Argument Long originId,
                               @Argument Long destinationId,
                               @Argument String departureDate,
                               @Argument String arrivalDate,
                               @Argument String departureTime,
                               @Argument String arrivalTime,
                               @Argument Long planeTypeId,
                               @Argument Double price,
                               @Argument BigDecimal taxPercentage,
                               @Argument Integer surchargePercentage,
                               @Argument Boolean isCanceled) {

        Flight flight = new Flight();
        flight.setFlightNumber(flightNumber);
        flight.setOrigin(airportService.getAirportById(originId));
        flight.setDestination(airportService.getAirportById(destinationId));
        flight.setDepartureDate(LocalDate.parse(departureDate));
        flight.setArrivalDate(LocalDate.parse(arrivalDate));
        flight.setDepartureTime(LocalTime.parse(departureTime));
        flight.setArrivalTime(LocalTime.parse(arrivalTime));
        flight.setPlaneType(planeTypeService.getPlaneTypeById(planeTypeId));
        flight.setPrice(price);
        flight.setTaxPercentage(taxPercentage);
        flight.setSurchargePercentage(surchargePercentage);
        flight.setCanceled(isCanceled);
        return flightSearchService.createOrUpdateFlight(flight);
    }

    @MutationMapping
    public Flight updateFlight(@Argument Long id,
                               @Argument Long flightNumber,
                               @Argument Long originId,
                               @Argument Long destinationId,
                               @Argument String departureDate,
                               @Argument String arrivalDate,
                               @Argument Long planeTypeId,
                               @Argument Double price,
                               @Argument BigDecimal taxPercentage,
                               @Argument Integer surchargePercentage,
                               @Argument Boolean isCanceled,
                               @Argument Boolean hasScales) {

        Optional<Flight> existingFlightOpt = flightSearchService.getFlightById(id);
        if (existingFlightOpt.isPresent()) {
            Flight existingFlight = existingFlightOpt.get();
            if (flightNumber != null) existingFlight.setFlightNumber(flightNumber);
            if (originId != null) existingFlight.setOrigin(airportService.getAirportById(originId));
            if (destinationId != null) existingFlight.setDestination(airportService.getAirportById(destinationId));
            if (departureDate != null) existingFlight.setDepartureDate(LocalDate.parse(departureDate));
            if (arrivalDate != null) existingFlight.setArrivalDate(LocalDate.parse(arrivalDate));
            if (planeTypeId != null) existingFlight.setPlaneType(planeTypeService.getPlaneTypeById(planeTypeId));
            if (price != null) existingFlight.setPrice(price);
            if (taxPercentage != null) existingFlight.setTaxPercentage(taxPercentage);
            if (surchargePercentage != null) existingFlight.setSurchargePercentage(surchargePercentage);
            if (isCanceled != null) existingFlight.setCanceled(isCanceled);

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