package com.udea.flightsearch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plane_id", nullable = false)
    private Long planeId;

    @Column(name = "model", nullable = false, length = 50)
    private String model;
    @Column(name = "seat_capacity", nullable = false)
    private Long seatCapacity;
    @Column(name = "seat_Distribution", nullable = false, length = 50)
    private String seatDistribution;

    @OneToMany(mappedBy = "plane", cascade = CascadeType.ALL)
    private List<Flight> flights = new ArrayList<>();

    @OneToMany(mappedBy = "plane", cascade = CascadeType.ALL)
    private List<Scale> scales = new ArrayList<>();

    public Plane() {
    }

    public Plane(String model, Long seatCapacity, String seatDistribution) {
        this.model = model;
        this.seatCapacity = seatCapacity;
        this.seatDistribution = seatDistribution;
    }

    public Long getPlaneId() {
        return planeId;
    }

    public void setPlaneId(Long planeId) {
        this.planeId = planeId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Long seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public String getSeatDistribution() {
        return seatDistribution;
    }

    public void setSeatDistribution(String seatDistribution) {
        this.seatDistribution = seatDistribution;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
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
        if (!(o instanceof Plane plane)) return false;
        return Objects.equals(getPlaneId(), plane.getPlaneId());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
