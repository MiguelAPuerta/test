package com.udea.flightsearch.controller;
import com.udea.flightsearch.model.Flight;
import com.udea.flightsearch.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.graphql.data.method.annotation.Argument;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FlightControllerTest {

    @Mock
    private FlightSearchService flightSearchService;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllFlights() {
        List<Flight> flights = Arrays.asList(new Flight(), new Flight());
        when(flightSearchService.getAllFlights()).thenReturn(flights);

        List<Flight> result = flightController.getAllFlights();

        assertEquals(flights, result);
        verify(flightSearchService, times(1)).getAllFlights();
    }

    @Test
    public void testGetFlightById() {
        Flight flight = new Flight();
        when(flightSearchService.getFlightById(1L)).thenReturn(Optional.of(flight));

        Optional<Flight> result = flightController.getFlightById(1L);

        assertEquals(Optional.of(flight), result);
        verify(flightSearchService, times(1)).getFlightById(1L);
    }

    @Test
    public void testSearchFlights() {
        List<Flight> flights = Arrays.asList(new Flight(), new Flight());
        when(flightSearchService.searchFlights(
                anyString(), anyString(), anyInt(), any(LocalDate.class), any(LocalDate.class),
                anyDouble(), anyDouble(), any(LocalDate.class), any(LocalDate.class),
                any(LocalTime.class), any(LocalTime.class), anyBoolean(), anyBoolean()))
                .thenReturn(flights);

        List<Flight> result = flightController.searchFlights(
                "Bogotá", "Medellín", 1, LocalDate.now(), LocalDate.now(),
                100.0, 600.0, LocalDate.now(), LocalDate.now(),
                "0:00", "23:59", true, false);

        assertEquals(flights, result);
        verify(flightSearchService, times(1)).searchFlights(
                anyString(), anyString(), anyInt(), any(LocalDate.class), any(LocalDate.class),
                anyDouble(), anyDouble(), any(LocalDate.class), any(LocalDate.class),
                any(LocalTime.class), any(LocalTime.class), anyBoolean(), anyBoolean());
    }

    @Test
    public void testSearchRoundTrip() {
        List<List<Flight>> roundTripFlights = Arrays.asList(
                Arrays.asList(new Flight(), new Flight()),
                Arrays.asList(new Flight(), new Flight()));
        when(flightSearchService.searchRoundTrip(
                anyString(), anyString(), anyInt(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(roundTripFlights);

        List<List<Flight>> result = flightController.searchRoundTrip(
                "Bogotá", "Medellín", 1, LocalDate.now(), LocalDate.now());

        assertEquals(roundTripFlights, result);
        verify(flightSearchService, times(1)).searchRoundTrip(
                anyString(), anyString(), anyInt(), any(LocalDate.class), any(LocalDate.class));
    }
}