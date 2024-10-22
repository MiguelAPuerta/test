package com.udea.flightsearch.controller;

import com.udea.flightsearch.model.Flight;
import com.udea.flightsearch.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
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
        // Configura el servicio para devolver una lista de vuelos
        List<Flight> flights = Arrays.asList(new Flight(), new Flight());
        when(flightSearchService.getAllFlights()).thenReturn(flights);

        // Llama al método del controlador
        List<Flight> result = flightController.getAllFlights();

        // Verifica que el resultado sea el esperado
        assertEquals(flights, result);
        // Verifica que el servicio fue llamado una vez
        verify(flightSearchService, times(1)).getAllFlights();
    }

    @Test
    public void testGetFlightById() {
        // Configura el servicio para devolver un vuelo específico
        Flight flight = new Flight();
        when(flightSearchService.getFlightById(1L)).thenReturn(Optional.of(flight));

        // Llama al método del controlador
        Optional<Flight> result = flightController.getFlightById(1L);

        // Verifica que el resultado sea el esperado
        assertEquals(Optional.of(flight), result);
        // Verifica que el servicio fue llamado una vez
        verify(flightSearchService, times(1)).getFlightById(1L);
    }

    @Test
    public void testSearchFlights() {
        // Configura el servicio para devolver una lista de vuelos
        List<Flight> flights = Arrays.asList(new Flight(), new Flight());
        when(flightSearchService.searchFlights(
                anyString(), anyString(), anyInt(), any(LocalDate.class), any(LocalDate.class),
                anyDouble(), anyDouble(), any(LocalDate.class), any(LocalDate.class),
                any(LocalTime.class), any(LocalTime.class), anyBoolean(), anyBoolean()))
                .thenReturn(flights);

        // Llama al método del controlador
        List<Flight> result = flightController.searchFlights(
                "Bogotá", "Medellín", 1, LocalDate.now(), LocalDate.now(),
                100.0, 600.0, LocalDate.now(), LocalDate.now(),
                "0:00", "23:59", true, false);

        // Verifica que el resultado sea el esperado
        assertEquals(flights, result);
        // Verifica que el servicio fue llamado una vez con los parámetros esperados
        verify(flightSearchService, times(1)).searchFlights(
                anyString(), anyString(), anyInt(), any(LocalDate.class), any(LocalDate.class),
                anyDouble(), anyDouble(), any(LocalDate.class), any(LocalDate.class),
                any(LocalTime.class), any(LocalTime.class), anyBoolean(), anyBoolean());
    }

    @Test
    public void testSearchRoundTrip() {
        // Configura el servicio para devolver una lista de vuelos de ida y vuelta
        List<List<Flight>> roundTripFlights = Arrays.asList(
                Arrays.asList(new Flight(), new Flight()),
                Arrays.asList(new Flight(), new Flight()));
        when(flightSearchService.searchRoundTrip(
                anyString(), anyString(), anyInt(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(roundTripFlights);

        // Llama al método del controlador
        List<List<Flight>> result = flightController.searchRoundTrip(
                "Bogotá", "Medellín", 1, LocalDate.now(), LocalDate.now());

        // Verifica que el resultado sea el esperado
        assertEquals(roundTripFlights, result);
        // Verifica que el servicio fue llamado una vez con los parámetros esperados
        verify(flightSearchService, times(1)).searchRoundTrip(
                anyString(), anyString(), anyInt(), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    public void testSearchFlightsWithoutOriginAndDestination() {
        // Configura el servicio para devolver una lista vacía
        when(flightSearchService.searchFlights(
                eq(""), eq(""), anyInt(), any(LocalDate.class), any(LocalDate.class),
                anyDouble(), anyDouble(), any(LocalDate.class), any(LocalDate.class),
                any(LocalTime.class), any(LocalTime.class), anyBoolean(), anyBoolean()))
                .thenReturn(Arrays.asList());

        // Llama al método del controlador sin origen y destino
        List<Flight> result = flightController.searchFlights(
                "", "", 1, LocalDate.now(), LocalDate.now(),
                100.0, 600.0, LocalDate.now(), LocalDate.now(),
                "0:00", "23:59", true, false);

        // Verifica que el resultado sea una lista vacía
        assertTrue(result.isEmpty(), "Se esperaba una lista vacía de vuelos");
    }

    @Test
    public void testSearchFlightsWithUnavailableCities() {
        // Configura el servicio para devolver una lista vacía
        when(flightSearchService.searchFlights(
                eq("CiudadNoDisponible"), eq("CiudadNoDisponible"), anyInt(), any(LocalDate.class), any(LocalDate.class),
                anyDouble(), anyDouble(), any(LocalDate.class), any(LocalDate.class),
                any(LocalTime.class), any(LocalTime.class), anyBoolean(), anyBoolean()))
                .thenReturn(Arrays.asList());

        // Llama al método del controlador con ciudades no disponibles
        List<Flight> result = flightController.searchFlights(
                "CiudadNoDisponible", "CiudadNoDisponible", 1, LocalDate.now(), LocalDate.now(),
                100.0, 600.0, LocalDate.now(), LocalDate.now(),
                "0:00", "23:59", true, false);

        // Verifica que el resultado sea una lista vacía
        assertTrue(result.isEmpty(), "Se esperaba una lista vacía de vuelos");
    }

    @Test
    public void testSearchFlightsWithoutAvailability() {
        // Configura el servicio para devolver una lista vacía
        when(flightSearchService.searchFlights(
                eq("Bogotá"), eq("Medellín"), anyInt(), any(LocalDate.class), any(LocalDate.class),
                anyDouble(), anyDouble(), any(LocalDate.class), any(LocalDate.class),
                any(LocalTime.class), any(LocalTime.class), anyBoolean(), anyBoolean()))
                .thenReturn(Arrays.asList());

        // Llama al método del controlador sin disponibilidad de vuelos
        List<Flight> result = flightController.searchFlights(
                "Bogotá", "Medellín", 1, LocalDate.now(), LocalDate.now(),
                100.0, 600.0, LocalDate.now(), LocalDate.now(),
                "0:00", "23:59", true, false);

        // Verifica que el resultado sea una lista vacía
        assertTrue(result.isEmpty(), "Se esperaba una lista vacía de vuelos");
    }

    @Test
    public void testFilterByValidPriceRange() {
        // Lista de vuelos que sabes que existen en la base de datos con los precios que quieres validar
        List<Flight> expectedFlights = Arrays.asList(
                new Flight("FL123", null, null, null, null, null, 
                           BigDecimal.valueOf(150.00), null, null, false, null),
                new Flight("FL124", null, null, null, null, null, 
                           BigDecimal.valueOf(200.00), null, null, false, null)
        );
    
        // Simula el comportamiento del servicio para devolver los vuelos esperados al buscar en el rango de precios dado
        when(flightSearchService.searchFlights(
                anyString(), anyString(), anyInt(), any(LocalDate.class), any(LocalDate.class),
                eq(100.0), eq(250.0), any(LocalDate.class), any(LocalDate.class),
                any(LocalTime.class), any(LocalTime.class), anyBoolean(), anyBoolean()))
                .thenReturn(expectedFlights);
    
        // Ejecuta la búsqueda de vuelos en el controlador con el rango de precios especificado
        List<Flight> result = flightController.searchFlights(
                "Bogotá", "Medellín", 1, LocalDate.now(), LocalDate.now(),
                100.0, 250.0, LocalDate.now(), LocalDate.now(),
                "0:00", "23:59", true, false);
    
        // Verifica que el resultado de la búsqueda coincida con los vuelos esperados
        assertEquals(expectedFlights, result);
        
        // Verifica que el servicio de búsqueda fue llamado una vez con los parámetros esperados
        verify(flightSearchService, times(1)).searchFlights(
                anyString(), anyString(), anyInt(), any(LocalDate.class), any(LocalDate.class),
                eq(100.0), eq(250.0), any(LocalDate.class), any(LocalDate.class),
                any(LocalTime.class), any(LocalTime.class), anyBoolean(), anyBoolean());
    }

    @Test
    public void testProceedWithoutSelectingPassengers() {
        // Intenta proceder con la búsqueda sin seleccionar pasajeros
        Exception exception = assertThrows(RuntimeException.class, () -> {
            flightController.searchFlights(
                    "Bogotá", "Medellín", 0, LocalDate.now(), LocalDate.now(),
                    100.0, 600.0, LocalDate.now(), LocalDate.now(),
                    "0:00", "23:59", true, false);
        });

        // Verifica que se muestra un mensaje de error
        assertEquals("Debe seleccionar al menos un pasajero", exception.getMessage());
    }

    @Test
    public void testSelectMoreThanEightPassengers() {
        // Intenta seleccionar más de 8 pasajeros
        Exception exception = assertThrows(RuntimeException.class, () -> {
            flightController.searchFlights(
                    "Bogotá", "Medellín", 9, LocalDate.now(), LocalDate.now(),
                    100.0, 600.0, LocalDate.now(), LocalDate.now(),
                    "0:00", "23:59", true, false);
        });

        // Verifica que se muestra un mensaje de error
        assertEquals("No se pueden seleccionar más de 8 pasajeros", exception.getMessage());
    }

    @Test
    public void testSearchRoundTripWithInvalidDates() {
        // Intenta realizar una búsqueda de ida y vuelta con fechas inválidas
        Exception exception = assertThrows(RuntimeException.class, () -> {
            flightController.searchRoundTrip(
                    "Bogotá", "Medellín", 1, LocalDate.now().plusDays(1), LocalDate.now());
        });

        // Verifica que se muestra un mensaje de error
        assertEquals("La fecha de regreso no puede ser anterior a la fecha de ida", exception.getMessage());
    }

    @Test
    public void testFilterByInvalidPriceRange() {
        // Intenta filtrar por un rango de precios inválido
        Exception exception = assertThrows(RuntimeException.class, () -> {
            flightController.searchFlights(
                    "Bogotá", "Medellín", 1, LocalDate.now(), LocalDate.now(),
                    300.0, 100.0, LocalDate.now(), LocalDate.now(),
                    "0:00", "23:59", true, false);
        });

        // Verifica que se muestra un mensaje de error
        assertEquals("El precio mínimo no puede ser mayor que el precio máximo", exception.getMessage());
    }

    @Test
    public void testFilterByInvalidDateRange() {
        // Intenta filtrar por un rango de fechas inválido
        Exception exception = assertThrows(RuntimeException.class, () -> {
            flightController.searchFlights(
                    "Bogotá", "Medellín", 1, LocalDate.now().minusDays(1), LocalDate.now(),
                    100.0, 600.0, LocalDate.now(), LocalDate.now(),
                    "0:00", "23:59", true, false);
        });

        // Verifica que se muestra un mensaje de error
        assertEquals("La fecha mínima no puede ser anterior a la fecha actual", exception.getMessage());
    }

    @Test
    public void testFilterByInvalidDepartureTimeRange() {
        // Intenta filtrar por un rango de horario de salida inválido
        Exception exception = assertThrows(RuntimeException.class, () -> {
            flightController.searchFlights(
                    "Bogotá", "Medellín", 1, LocalDate.now(), LocalDate.now(),
                    100.0, 600.0, LocalDate.now(), LocalDate.now(),
                    "23:59", "0:00", true, false);
        });

        // Verifica que se muestra un mensaje de error
        assertEquals("El horario mínimo no puede ser mayor o igual al horario máximo", exception.getMessage());
    }
}