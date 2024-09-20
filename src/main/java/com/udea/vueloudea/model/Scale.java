package com.udea.vueloudea.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Scale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scaleId;


    @ManyToOne()
    @JoinColumn(name = "flightId", referencedColumnName = "flightId")
    private Flight flight;

    @ManyToOne()
    @JoinColumn(name = "airportId", referencedColumnName = "airportId")
    private Airport airport;

    private Long position;
    private LocalDate departureTime;
    private LocalDate arrivalTime;

    public Scale() {
    }

    public Scale(Flight flight, Airport airport, Long position, LocalDate departureTime, LocalDate arrivalTime) {
        this.flight = flight;
        this.airport = airport;
        this.position = position;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Long getScaleId() {
        return scaleId;
    }

    public void setScaleId(Long scaleId) {
        this.scaleId = scaleId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public LocalDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDate departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDate arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scale scale)) return false;
        return Objects.equals(getScaleId(), scale.getScaleId());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
