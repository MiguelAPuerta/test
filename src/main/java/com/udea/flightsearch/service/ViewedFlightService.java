package com.udea.flightsearch.service;

import com.udea.flightsearch.model.ViewedFlight;
import com.udea.flightsearch.repository.IViewedFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViewedFlightService {

    @Autowired
    private IViewedFlightRepository viewedFlightRepository;

    // Funcion para obtener todas los vuelos vistos
    public List<ViewedFlight> getAllViewedFlights() {
        return viewedFlightRepository.findAll();
    }

    // Funcion para obtener un vuelo vistos por su ID
    public ViewedFlight getViewedFlightById(Long id) {
        Optional<ViewedFlight> viewedFlightOpt = viewedFlightRepository.findById(id);
        return viewedFlightOpt.orElseThrow(() -> new RuntimeException("ViewedFlight not found with id: " + id));
    }

    // Funcion para crear o actualizar un vuelo vistos
    public ViewedFlight createOrUpdateViewedFlight(ViewedFlight viewedFlight) {
        return viewedFlightRepository.save(viewedFlight);
    }

    // Funcion para eliminar un vuelo vistos por su ID
    public void deleteViewedFlight(Long id) {
        // Manejo de error: verificar si existe antes de eliminar
        if (!viewedFlightRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete ViewedFlight. Not found with id: " + id);
        }
        viewedFlightRepository.deleteById(id);
    }
}
