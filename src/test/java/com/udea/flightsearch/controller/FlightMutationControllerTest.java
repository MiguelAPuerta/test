package com.udea.flightsearch.controller;

import com.udea.flightsearch.model.Airport;
import com.udea.flightsearch.model.Flight;
import com.udea.flightsearch.model.Plane;
import com.udea.flightsearch.service.AirportService;
import com.udea.flightsearch.service.FlightSearchService;
import com.udea.flightsearch.service.PlaneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FlightMutationControllerTest {

    @InjectMocks
    private FlightMutationController flightMutationController;

    @Mock
    private FlightSearchService flightSearchService;

    @Mock
    private AirportService airportService;

    @Mock
    private PlaneService planeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFlight() {
        Flight flight = new Flight();
        when(airportService.getAirportById(any(Long.class))).thenReturn(new Airport());
        when(planeService.getPlaneById(any(Long.class))).thenReturn(new Plane());
        when(flightSearchService.createOrUpdateFlight(any(Flight.class))).thenReturn(flight);

        Flight result = flightMutationController.createFlight(
                "FL123",
                1L,
                2L,
                "2023-10-10 10:00:00",
                "2023-10-10 12:00:00",
                1L,
                BigDecimal.valueOf(100.00),
                BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(5.00),
                false,
                100
        );

        assertNotNull(result);
        verify(flightSearchService, times(1)).createOrUpdateFlight(any(Flight.class));
    }

    @Test
    public void testUpdateFlight() {
        Flight flight = new Flight();
        when(flightSearchService.getFlightById(any(Long.class))).thenReturn(Optional.of(flight));
        when(airportService.getAirportById(any(Long.class))).thenReturn(new Airport());
        when(planeService.getPlaneById(any(Long.class))).thenReturn(new Plane());
        when(flightSearchService.createOrUpdateFlight(any(Flight.class))).thenReturn(flight);

        Flight result = flightMutationController.updateFlight(
                1L,
                "FL123",
                1L,
                2L,
                "2023-10-10 10:00:00",
                "2023-10-10 12:00:00",
                1L,
                BigDecimal.valueOf(100.00),
                BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(5.00),
                false,
                100
        );

        assertNotNull(result);
        verify(flightSearchService, times(1)).getFlightById(any(Long.class));
        verify(flightSearchService, times(1)).createOrUpdateFlight(any(Flight.class));
    }

    @Test
    public void testUpdateFlightNotFound() {
        when(flightSearchService.getFlightById(any(Long.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            flightMutationController.updateFlight(
                    1L,
                    "FL123",
                    1L,
                    2L,
                    "2023-10-10 10:00:00",
                    "2023-10-10 12:00:00",
                    1L,
                    BigDecimal.valueOf(100.00),
                    BigDecimal.valueOf(10.00),
                    BigDecimal.valueOf(5.00),
                    false,
                    100
            );
        });

        assertEquals("Flight not found with id: 1", exception.getMessage());
        verify(flightSearchService, times(1)).getFlightById(any(Long.class));
        verify(flightSearchService, times(0)).createOrUpdateFlight(any(Flight.class));
    }

    @Test
    public void testDeleteFlight() {
        doNothing().when(flightSearchService).deleteFlight(any(Long.class));

        boolean result = flightMutationController.deleteFlight(1L);

        assertTrue(result);
        verify(flightSearchService, times(1)).deleteFlight(any(Long.class));
    }
}