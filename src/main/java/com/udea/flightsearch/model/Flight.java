package com.udea.flightsearch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @NotNull
    private Long flightNumber;

    @ManyToOne()
    @JoinColumn(name = "origin", referencedColumnName = "airportId", nullable = false)
    private Airport origin;

    @ManyToOne()
    @JoinColumn(name = "destination", referencedColumnName = "airportId", nullable = false)
    private Airport destination;

    @NotNull
    private LocalDate departureDate;
    @NotNull
    private LocalDate arrivalDate;
    @NotNull
    private LocalTime departureTime;
    @NotNull
    private LocalTime arrivalTime;

    @ManyToOne()
    @JoinColumn(name = "planeType", referencedColumnName = "planeTypeId", nullable = false)
    private PlaneType planeType;

    @NotNull
    private double price;
    @NotNull
    private BigDecimal taxPercentage;
    private Integer surchargePercentage;
    @NotNull
    private boolean isCanceled;

    @OneToMany(mappedBy = "primaryFlight", cascade = CascadeType.ALL)
    private List<Scale> primaryFlights = new ArrayList<>();

    @OneToMany(mappedBy = "connectingFlight", cascade = CascadeType.ALL)
    private List<Scale> connectingFlight = new ArrayList<>();

    public Flight() {
    }

    public Flight(Long flightId, Long flightNumber, Airport origin, Airport destination, LocalDate departureDate, LocalDate arrivalDate, LocalTime departureTime, LocalTime arrivalTime, PlaneType planeType, double price, BigDecimal taxPercentage, Integer surchargePercentage, boolean isCanceled, boolean hasScales) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.planeType = planeType;
        this.price = price;
        this.taxPercentage = taxPercentage;
        this.surchargePercentage = surchargePercentage;
        this.isCanceled = isCanceled;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
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

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
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

    public PlaneType getPlaneType() {
        return planeType;
    }

    public void setPlaneType(PlaneType planeType) {
        this.planeType = planeType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public Integer getSurchargePercentage() {
        return surchargePercentage;
    }

    public void setSurchargePercentage(Integer surchargePercentage) {
        this.surchargePercentage = surchargePercentage;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public List<Scale> getPrimaryFlights() {
        return primaryFlights;
    }

    public void setPrimaryFlights(List<Scale> primaryFlights) {
        this.primaryFlights = primaryFlights;
    }

    public List<Scale> getConnectingFlight() {
        return connectingFlight;
    }

    public void setConnectingFlight(List<Scale> connectingFlight) {
        this.connectingFlight = connectingFlight;
    }

    //IDIOMS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight flight)) return false;
        return Objects.equals(getFlightId(), flight.getFlightId());
    }

    @Override
    public int hashCode() {
        return 0;
    }

}