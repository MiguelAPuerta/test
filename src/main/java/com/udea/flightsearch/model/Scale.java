package com.udea.flightsearch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Scale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scaleId;

    @ManyToOne()
    @JoinColumn(name = "primaryFlightId", referencedColumnName = "flightId")
    @NotNull
    private Flight primaryFlight;

    @ManyToOne()
    @JoinColumn(name = "connectingFlightId", referencedColumnName = "flightId")
    @NotNull
    private Flight connectingFlight; // The flight that connects after the stopover

    @ManyToOne()
    @JoinColumn(name = "airportId", referencedColumnName = "airportId")
    private Airport stopoverAirport;

    @NotNull
    private Long position;
    @NotNull
    private LocalDate departureDate;
    @NotNull
    private LocalDate arrivalDate;
    @NotNull
    private LocalTime departureTime;
    @NotNull
    private LocalTime arrivalTime;
    @NotNull

    // Other properties, getters, setters

    public Scale() {
    }

    public Scale(Flight primaryFlight, Flight connectingFlight, Airport stopoverAirport, Long position, LocalDate departureDate, LocalDate arrivalDate, LocalTime departureTime, LocalTime arrivalTime, LocalDate stopoverDuration) {
        this.primaryFlight = primaryFlight;
        this.connectingFlight = connectingFlight;
        this.stopoverAirport = stopoverAirport;
        this.position = position;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Long getScaleId() {
        return scaleId;
    }

    public void setScaleId(Long scaleId) {
        this.scaleId = scaleId;
    }

    public @NotNull Flight getPrimaryFlight() {
        return primaryFlight;
    }

    public void setPrimaryFlight(@NotNull Flight primaryFlight) {
        this.primaryFlight = primaryFlight;
    }

    public @NotNull Flight getConnectingFlight() {
        return connectingFlight;
    }

    public void setConnectingFlight(@NotNull Flight connectingFlight) {
        this.connectingFlight = connectingFlight;
    }

    public Airport getStopoverAirport() {
        return stopoverAirport;
    }

    public void setStopoverAirport(Airport stopoverAirport) {
        this.stopoverAirport = stopoverAirport;
    }

    public @NotNull Long getPosition() {
        return position;
    }

    public void setPosition(@NotNull Long position) {
        this.position = position;
    }

    public @NotNull LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(@NotNull LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public @NotNull LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(@NotNull LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public @NotNull LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(@NotNull LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public @NotNull LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(@NotNull LocalTime arrivalTime) {
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
