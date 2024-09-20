package com.udea.flightsearch.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airportId")
    private Long airportId;

    @ManyToOne()
    @JoinColumn(name = "iataCode", referencedColumnName = "iataCode")
    private City city;

    private String name;

    @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL)
    private List<Scale> scales = new ArrayList<>();

    public Airport() {
    }

    public Airport(City city, String name) {
        this.city = city;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Scale> getScales() {
        return scales;
    }

    public void setScales(List<Scale> scales) {
        this.scales = scales;
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
