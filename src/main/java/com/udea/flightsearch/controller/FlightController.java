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
        exceptionHandler(departureDate,
                        arrivalDate,
                        passengerAmount,
                        minimumPrice,
                        maximumPrice,
                        minimumDate,
                        maximumDate,
                        minimumTime,
                        maximumTime,
                        timeFormatter);
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
        exceptionHandler(departureDate,
                        arrivalDate,
                        passengerAmount,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
        return flightSearchService.searchRoundTrip(
                originName,
                destinationName,
                passengerAmount,
                departureDate,
                arrivalDate);
    }

    private void exceptionHandler (
                              LocalDate departureDate,
                              LocalDate arrivalDate,
                              Integer passengerAmount,
                              Double minimumPrice,
                              Double maximumPrice,
                              LocalDate minimumDate,
                              LocalDate maximumDate,
                              String minimumTime,
                              String maximumTime,
                              DateTimeFormatter timeFormatter) {
        // Checks if the number of passengers is 0 or null
        if (passengerAmount == null || passengerAmount == 0) {
            throw new RuntimeException("Debe seleccionar al menos un pasajero");
        }
        // Checks if the number of passengers is above 8
        if (passengerAmount > 8) {
            throw new RuntimeException("No se pueden seleccionar más de 8 pasajeros");
        }
        // Checks if the minimum of the range of prices is below the maximum
        if (minimumPrice != null && maximumPrice != null && minimumPrice > maximumPrice){
            throw new RuntimeException("El precio mínimo no puede ser mayor que el precio máximo");
        }
        // Checks if the departure Date and minimum Date are before today
        if ((departureDate != null && departureDate.isBefore(LocalDate.now())) || (minimumDate != null && minimumDate.isBefore(LocalDate.now()))){
            throw new RuntimeException("La fecha mínima no puede ser anterior a la fecha actual");
        }
        // Checks if the departure Date is before the arrival Date
        if (departureDate != null && arrivalDate != null && departureDate.isAfter(arrivalDate)){
            throw new RuntimeException("La fecha de regreso no puede ser anterior a la fecha de ida");
        }
        // Checks if the minimum Date is before the maximum Date
        if (minimumDate != null && maximumDate != null && minimumDate.isAfter(maximumDate)){
            throw new RuntimeException("La fecha mínima no puede ser anterior a la fecha máxima");
        }
        // Checks if the minimum of the time range is below the maximum
        if (minimumTime != null && maximumTime != null) {
            LocalTime minTime = LocalTime.parse(minimumTime, timeFormatter);
            LocalTime maxTime = LocalTime.parse(maximumTime, timeFormatter);
            if (minTime.isAfter(maxTime) || minTime.equals(maxTime)) {
                throw new RuntimeException("El horario mínimo no puede ser mayor o igual al horario máximo");
            }
        }
    }
}
