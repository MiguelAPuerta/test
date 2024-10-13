package com.udea.flightsearch.service;

import com.udea.flightsearch.Specification.FlightSpecification;
import com.udea.flightsearch.model.Flight;
import com.udea.flightsearch.repository.IFlightSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightSearchService {

    @Autowired
    private IFlightSearchRepository flightRepository;

    public List<Flight> searchFlights(String originName,
                                      String destinationName,
                                      Integer passengerAmount,
                                      LocalDate departureDate,
                                      LocalDate arrivalDate,
                                      Double minimumPrice,
                                      Double maximumPrice,
                                      LocalDate minimumDate,
                                      LocalDate maximumDate,
                                      LocalTime minimumTime,
                                      LocalTime maximumTime,
                                      boolean orderByDepartureDateAsc,
                                      boolean orderByPriceAsc
    ) {
        // Use the specification method to search flights
        return flightRepository.findAll(
                FlightSpecification.filterBy(
                        originName,
                        destinationName,
                        passengerAmount,
                        departureDate,
                        arrivalDate,
                        minimumPrice,
                        maximumPrice,
                        minimumDate,
                        maximumDate,
                        minimumTime,
                        maximumTime,
                        orderByDepartureDateAsc,
                        orderByPriceAsc)
        );
    }


    public List<List<Flight>> searchRoundTrip(String originName,
                                      String destinationName,
                                      Integer passengerAmount,
                                      LocalDate departureDate,
                                      LocalDate arrivalDate
    ) {
        // Outbound flights (origin -> destination)
        List<Flight> FirstLeg = flightRepository.findAll(
                FlightSpecification.filterBy(
                        originName,
                        destinationName,
                        passengerAmount,
                        departureDate,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        false,
                        true)
        );

        // Return flights (destination -> origin)
        List<Flight> SecondLeg = flightRepository.findAll(
                    FlightSpecification.filterBy(
                            destinationName,
                            originName,
                            passengerAmount,
                            arrivalDate,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            false,
                            true)
        );

        List<List<Flight>> roundTripFlights = new ArrayList<>();

        roundTripFlights.add(FirstLeg);
        roundTripFlights.add(SecondLeg);

        return roundTripFlights;
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

