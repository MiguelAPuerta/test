package com.udea.flightsearch.service;

import com.udea.flightsearch.model.SeatClass;
import com.udea.flightsearch.repository.ISeatClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatClassService {

    @Autowired
    private ISeatClassRepository seatClassRepository;

    // Funcion para obtener todas las clases para un asiento
    public List<SeatClass> getAllSeatClasses() {
        return seatClassRepository.findAll();
    }

    // Funcion para obtener una clase de asiento por ID
    public SeatClass getSeatClassById(Long id) {
        Optional<SeatClass> seatClassOpt = seatClassRepository.findById(id);
        return seatClassOpt.orElseThrow(() -> new RuntimeException("SeatClass not found with id: " + id));
    }

    // Funcion para crear o actualizar una clase de asiento
    public SeatClass createOrUpdateSeatClass(SeatClass seatClass) {
        return seatClassRepository.save(seatClass);
    }

    // Funcion para eliminar una clase de asiento por su ID
    public void deleteSeatClass(Long id) {
        // Manejo de error: verificar si existe antes de eliminar
        if (!seatClassRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete SeatClass. Not found with id: " + id);
        }
        seatClassRepository.deleteById(id);
    }
}
