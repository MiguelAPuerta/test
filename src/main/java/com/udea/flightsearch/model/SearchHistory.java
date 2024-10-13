package com.udea.flightsearch.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_id", nullable = false)
    private Long searchId;

    @ManyToOne()
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
    private Account account;

    @Column(name = "search_date", nullable = false)
    private Timestamp searchDate;

    @ManyToOne()
    @JoinColumn(name = "origin", referencedColumnName = "airport_id", nullable = false)
    private Airport origin;

    @ManyToOne()
    @JoinColumn(name = "destination", referencedColumnName = "airport_id", nullable = false)
    private Airport destination;

    @Column(name = "departure_date")
    private Timestamp departureDate;
    @Column(name = "arrival_date")
    private Timestamp arrivalDate;

    @Column(name = "num_passengers")
    private Integer numPassengers;
    @Column(name = "price_range_min", precision = 10, scale = 2)
    private BigDecimal priceRangeMin;
    @Column(name = "price_range_max", precision = 10, scale = 2)
    private BigDecimal priceRangeMax;
    @Column(name = "results_count")
    private Integer resultsCount;

    public SearchHistory() {
    }

    public SearchHistory(Account account, Timestamp searchDate, Airport origin, Airport destination, Timestamp departureDate, Timestamp arrivalDate, Integer numPassengers, BigDecimal priceRangeMin, BigDecimal priceRangeMax, Integer resultsCount) {
        this.account = account;
        this.searchDate = searchDate;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.numPassengers = numPassengers;
        this.priceRangeMin = priceRangeMin;
        this.priceRangeMax = priceRangeMax;
        this.resultsCount = resultsCount;
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Timestamp getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Timestamp searchDate) {
        this.searchDate = searchDate;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public Timestamp getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Integer getNumPassengers() {
        return numPassengers;
    }

    public void setNumPassengers(Integer numPassengers) {
        this.numPassengers = numPassengers;
    }

    public BigDecimal getPriceRangeMin() {
        return priceRangeMin;
    }

    public void setPriceRangeMin(BigDecimal priceRangeMin) {
        this.priceRangeMin = priceRangeMin;
    }

    public BigDecimal getPriceRangeMax() {
        return priceRangeMax;
    }

    public void setPriceRangeMax(BigDecimal priceRangeMax) {
        this.priceRangeMax = priceRangeMax;
    }

    public Integer getResultsCount() {
        return resultsCount;
    }

    public void setResultsCount(Integer resultsCount) {
        this.resultsCount = resultsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchHistory searchHistory)) return false;
        return Objects.equals(getSearchId(), searchHistory.getSearchId());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
