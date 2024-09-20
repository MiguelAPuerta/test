package com.udea.flightsearch.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class PlaneType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planeTypeId;

    private String model;
    private Long seatCapacity;
    private String seatDistribution;

    @OneToMany(mappedBy = "planeType", cascade = CascadeType.ALL)
    private List<Seat> seats = new ArrayList<>();;

    @OneToMany(mappedBy = "planeType", cascade = CascadeType.ALL)
    private List<Flight> flights = new ArrayList<>();;

    public PlaneType() {
    }

    public PlaneType(String model, Long seatCapacity, String seatDistribution) {
        this.model = model;
        this.seatCapacity = seatCapacity;
        this.seatDistribution = seatDistribution;
    }

    public Long getPlaneTypeId() {
        return planeTypeId;
    }

    public void setPlaneTypeId(Long planeTypeId) {
        this.planeTypeId = planeTypeId;
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

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaneType planeType)) return false;
        return Objects.equals(getPlaneTypeId(), planeType.getPlaneTypeId());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
