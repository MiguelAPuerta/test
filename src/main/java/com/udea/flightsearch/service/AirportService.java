package com.udea.flightsearch.service;

import com.udea.flightsearch.model.Airport;
import com.udea.flightsearch.repository.IAirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private IAirportRepository airportRepository;

    // Funcion para obtener todos los aeropuertos
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    // Funcion para obtener un aeropuerto por ID
    public Airport getAirportById(Long id) {
        Optional<Airport> airportOpt = airportRepository.findById(id);
        return airportOpt.orElseThrow(() -> new RuntimeException("Airport not found with id: " + id));
    }

    // Funcion para crear o actualizar un aeropuerto
    public Airport createOrUpdateAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    // Funcion para eliminar un aeropuerto por su ID
    public void deleteAirport(Long id) {
        // Manejo de error: verificar si existe antes de eliminar
        if (!airportRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete Airport. Not found with id: " + id);
        }
        airportRepository.deleteById(id);
    }
}
