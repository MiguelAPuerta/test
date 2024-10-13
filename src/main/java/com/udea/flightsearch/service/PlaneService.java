package com.udea.flightsearch.service;

import com.udea.flightsearch.model.Plane;
import com.udea.flightsearch.repository.IPlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneService {

    @Autowired
    private IPlaneRepository planeRepository;

    // Funcion para obtener todos los aviones
    public List<Plane> getAllPlane() {
        return planeRepository.findAll();
    }

    // Funcion para obtener un avion por ID
    public Plane getPlaneById(Long id) {
        Optional<Plane> planeOpt = planeRepository.findById(id);
        return planeOpt.orElseThrow(() -> new RuntimeException("Plane not found with id: " + id));
    }

    // Funcion para crear o actualizar un avion
    public Plane createOrUpdatePlane(Plane plane) {
        return planeRepository.save(plane);
    }

    // Funcion para eliminar un avion por su ID
    public void deletePlane(Long id) {
        // Manejo de error: verificar si existe antes de eliminar
        if (!planeRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete Plane. Not found with id: " + id);
        }
        planeRepository.deleteById(id);
    }
}
