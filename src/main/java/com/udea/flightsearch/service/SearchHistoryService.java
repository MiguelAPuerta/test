package com.udea.flightsearch.service;

import com.udea.flightsearch.model.SearchHistory;
import com.udea.flightsearch.repository.ISearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchHistoryService {

    @Autowired
    private ISearchHistoryRepository searchHistoryRepository;

    // Funcion para obtener todas los historiales de busqueda
    public List<SearchHistory> getAllSearchHistories() {
        return searchHistoryRepository.findAll();
    }

    // Funcion para obtener un historial de busqueda por su ID
    public SearchHistory getSearchHistoryById(Long id) {
        Optional<SearchHistory> searchHistoryOpt = searchHistoryRepository.findById(id);
        return searchHistoryOpt.orElseThrow(() -> new RuntimeException("SearchHistory not found with id: " + id));
    }

    // Funcion para crear o actualizar un historial de busqueda
    public SearchHistory createOrUpdateSearchHistory(SearchHistory searchHistory) {
        return searchHistoryRepository.save(searchHistory);
    }

    // Funcion para eliminar un historial de busqueda por su ID
    public void deleteSearchHistory(Long id) {
        // Manejo de error: verificar si existe antes de eliminar
        if (!searchHistoryRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete SearchHistory. Not found with id: " + id);
        }
        searchHistoryRepository.deleteById(id);
    }
}
