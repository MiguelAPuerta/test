package com.udea.flightsearch.service;

import com.udea.flightsearch.model.Airport;
import com.udea.flightsearch.model.City;
import com.udea.flightsearch.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private ICityRepository cityRepository;

    // Funcion para obtener todas las ciudades
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    // Funcion para obtener una ciudad por su ID
    public City getAirportById(Long id) {
        Optional<City> cityOpt = cityRepository.findById(id);
        return cityOpt.orElseThrow(() -> new RuntimeException("City not found with id: " + id));
    }

    // Funcion para crear o actualizar una ciudad
    public City createOrUpdateCity(City city) {
        return cityRepository.save(city);
    }

    // Funcion para eliminar una ciudad por su ID
    public void deleteAirport(Long id) {
        // Manejo de error: verificar si existe antes de eliminar
        if (!cityRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete City. Not found with id: " + id);
        }
        cityRepository.deleteById(id);
    }
}
