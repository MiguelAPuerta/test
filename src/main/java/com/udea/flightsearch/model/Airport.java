package com.udea.flightsearch.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id", nullable = false)
    private Long airportId;

    @ManyToOne()
    @JoinColumn(name = "city_id", referencedColumnName = "city_id", nullable = false)
    private City city;

    @Column(name = "iata_code", nullable = false, unique = true, length = 3)
    private String iataCode;
    @Column(name = "name_airport", nullable = false, length = 100)
    private String nameAirport;

    @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL)
    private List<Scale> scales = new ArrayList<>();

    @OneToMany(mappedBy = "origin", cascade = CascadeType.ALL)
    private List<Flight> origin = new ArrayList<>();

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Flight> destination = new ArrayList<>();

    public Airport() {
    }

    public Airport(City city, String iataCode, String nameAirport) {
        this.city = city;
        this.iataCode = iataCode;
        this.nameAirport = nameAirport;
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getNameAirport() {
        return nameAirport;
    }

    public void setNameAirport(String nameAirport) {
        this.nameAirport = nameAirport;
    }

    public List<Scale> getScales() {
        return scales;
    }

    public void setScales(List<Scale> scales) {
        this.scales = scales;
    }

    public List<Flight> getOrigin() {
        return origin;
    }

    public void setOrigin(List<Flight> origin) {
        this.origin = origin;
    }

    public List<Flight> getDestination() {
        return destination;
    }

    public void setDestination(List<Flight> destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport airport)) return false;
        return Objects.equals(getAirportId(), airport.getAirportId());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
