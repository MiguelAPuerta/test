package com.udea.flightsearch.repository;

import com.udea.flightsearch.model.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
}