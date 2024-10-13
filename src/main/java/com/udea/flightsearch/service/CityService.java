package com.udea.flightsearch.service;

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

    // Funcion para obtener una ciudad por iataCode
    public City getCityByIataCode(String iataCode) {
        Optional<City> cityOpt = cityRepository.findByIataCodeContainingIgnoreCase(iataCode);
        return cityOpt.orElseThrow(() -> new RuntimeException("City not found with iataCode: " + iataCode));
    }

    // Funcion para crear o actualizar una ciudad
    public City createOrUpdateCity(City city) {
        return cityRepository.save(city);
    }

    // Funcion para eliminar una ciudad por su IataCode
    public void deleteCity(String iataCode) {
        // Manejo de error: verificar si existe antes de eliminar
        if (!cityRepository.existsByIataCode(iataCode)) {
            throw new RuntimeException("Cannot delete City. Not found with iataCode: " + iataCode);
        }
        cityRepository.deleteByIataCode(iataCode);
    }
}
