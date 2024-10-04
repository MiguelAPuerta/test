package com.udea.flightsearch.service;

import com.udea.flightsearch.model.PlaneType;
import com.udea.flightsearch.repository.IPlaneTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneTypeService {

    @Autowired
    private IPlaneTypeRepository planeTypeRepository;

    // Funcion para obtener todos los Tipos de avion
    public List<PlaneType> getAllPlaneTypes() {
        return planeTypeRepository.findAll();
    }

    // Funcion para obtener un tipo de avion por ID
    public PlaneType getPlaneTypeById(Long id) {
        Optional<PlaneType> planeTypeOpt = planeTypeRepository.findById(id);
        return planeTypeOpt.orElseThrow(() -> new RuntimeException("PlaneType not found with id: " + id));
    }

    // Funcion para crear o actualizar un tipo de avion
    public PlaneType createOrUpdatePlaneType(PlaneType planeType) {
        return planeTypeRepository.save(planeType);
    }

    // Funcion para eliminar un tipo de avion por su ID
    public void deletePlaneType(Long id) {
        // Manejo de error: verificar si existe antes de eliminar
        if (!planeTypeRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete PlaneType. Not found with id: " + id);
        }
        planeTypeRepository.deleteById(id);
    }
}
