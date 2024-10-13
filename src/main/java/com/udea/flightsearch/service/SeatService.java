package com.udea.flightsearch.service;

import com.udea.flightsearch.model.Seat;
import com.udea.flightsearch.repository.ISeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private ISeatRepository seatRepository;

    // Funcion para obtener todas los Asientos
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    // Funcion para obtener un asiento por ID
    public Seat getSeatById(Long id) {
        Optional<Seat> seatOpt = seatRepository.findById(id);
        return seatOpt.orElseThrow(() -> new RuntimeException("Seat not found with id: " + id));
    }

    // Funcion para crear o actualizar un asiento
    public Seat createOrUpdateSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    // Funcion para eliminar un asiento por su ID
    public void deleteSeat(Long id) {
        // Manejo de error: verificar si existe antes de eliminar
        if (!seatRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete Seat. Not found with id: " + id);
        }
        seatRepository.deleteById(id);
    }
}
