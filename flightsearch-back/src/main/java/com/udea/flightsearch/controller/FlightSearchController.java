package com.udea.flightsearch.controller;

import com.udea.flightsearch.model.Flight;
import com.udea.flightsearch.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "http://localhost:3000")
public class FlightSearchController {

    @Autowired
    private FlightSearchService flightSearchService;

    @GetMapping("/search")
    public List<Flight> searchFlights(@RequestParam(value = "origin", required = false) String origin,
                                       @RequestParam(value = "destination", required = false) String destination,
                                       @RequestParam(value = "startDate", required = false) LocalDate departureTime,
                                       @RequestParam(value = "endDate", required = false) LocalDate arrivalTime) {
        return flightSearchService.searchFlights(origin, destination, departureTime, arrivalTime);
    }
}
