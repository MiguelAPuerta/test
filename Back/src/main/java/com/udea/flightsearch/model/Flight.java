package com.udea.flightsearch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Flight {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @NotNull
    private Long flightNumber;

    @ManyToOne()
    @JoinColumn(name = "origin", referencedColumnName = "airportId")
    @NotNull
    private Airport origin;

    @ManyToOne()
    @JoinColumn(name = "destination", referencedColumnName = "airportId")
    @NotNull
    private Airport destination;

    @NotNull
    private LocalDate departureTime;
    @NotNull
    private LocalDate arrivalTime;

    @ManyToOne()
    @JoinColumn(name = "planeType", referencedColumnName = "planeTypeId")
    @NotNull
    private PlaneType planeType;

    @NotNull
    private double price;
    @NotNull
    private BigDecimal taxPercentage;
    private Integer surchargePercentage;
    @NotNull
    private boolean isCanceled;
    private boolean hasScales;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Scale> scales = new ArrayList<>();

    public Flight() {
    }

    public Flight(Long flightId, Long flightNumber, Airport origin, Airport destination, LocalDate departureTime, LocalDate arrivalTime, PlaneType planeType, double price, BigDecimal taxPercentage, Integer surchargePercentage, boolean isCanceled, boolean hasScales) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.planeType = planeType;
        this.price = price;
        this.taxPercentage = taxPercentage;
        this.surchargePercentage = surchargePercentage;
        this.isCanceled = isCanceled;
        this.hasScales = hasScales;
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

    public boolean isHasScales() {
        return hasScales;
    }

    public void setHasScales(boolean hasScales) {
        this.hasScales = hasScales;
    }

    public List<Scale> getScales() {
        return scales;
    }

    public void setScales(List<Scale> scales) {
        this.scales = scales;
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