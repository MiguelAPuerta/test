package com.udea.flightsearch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class City {

    @Id
    private String iataCode;

    @NotNull
    private String name;
    private String country;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Airport> airports = new ArrayList<>();

    public City() {
    }

    public City(String iataCode, String name, String country) {
        this.iataCode = iataCode;
        this.name = name;
        this.country = country;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(getIataCode(), city.getIataCode());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
