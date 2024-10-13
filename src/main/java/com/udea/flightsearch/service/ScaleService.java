package com.udea.flightsearch.service;

import com.udea.flightsearch.model.Scale;
import com.udea.flightsearch.repository.IScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScaleService {

    @Autowired
    private IScaleRepository scaleRepository;

    // Funcion para obtener todas las escalas
    public List<Scale> getAllScales() {
        return scaleRepository.findAll();
    }

    // Funcion para obtener una escala por ID
    public Scale getScaleById(Long id) {
        Optional<Scale> scaleOpt = scaleRepository.findById(id);
        return scaleOpt.orElseThrow(() -> new RuntimeException("Scale not found with id: " + id));
    }

    // Funcion para crear o actualizar una escala
    public Scale createOrUpdateScale(Scale scale) {
        return scaleRepository.save(scale);
    }

    // Funcion para eliminar una escala por su ID
    public void deleteScale(Long id) {
        // Manejo de error: verificar si existe antes de eliminar
        if (!scaleRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete Scale. Not found with id: " + id);
        }
        scaleRepository.deleteById(id);
    }
}
