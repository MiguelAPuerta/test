package com.udea.flightsearch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id", nullable = false)
    private Long flightId;

    @Column(name = "flight_number", nullable = false, length = 9)
    private String flightNumber;

    @ManyToOne()
    @JoinColumn(name = "origin", referencedColumnName = "airport_id", nullable = false)
    private Airport origin;

    @ManyToOne()
    @JoinColumn(name = "destination", referencedColumnName = "airport_id", nullable = false)
    private Airport destination;

    @Column(name = "departure_date", nullable = false)
    private Timestamp departureDate;
    @Column(name = "arrival_date", nullable = false)
    private Timestamp arrivalDate;

    @ManyToOne()
    @JoinColumn(name = "plane_id", referencedColumnName = "plane_id", nullable = false)
    private Plane plane;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    @Column(name = "tax_percentage", nullable = false, precision = 5, scale = 2)
    private BigDecimal taxPercentage;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    @Column(name = "surcharge_percentage", nullable = false, precision = 5, scale = 2)
    private BigDecimal surchargePercentage;

    @Column(name = "is_canceled", nullable = false)
    private boolean isCanceled;
    @Column(name = "sell_seats")
    private Integer sellSeats;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Scale> scales = new ArrayList<>();

    @PrePersist
    @PreUpdate
    public void validateDates() {
        if (departureDate.after(arrivalDate)) {
            throw new IllegalArgumentException("Departure date must be before arrival date");
        }
    }



    public Flight() {
    }

    public Flight(String flightNumber, Airport origin, Airport destination, Timestamp departureDate, Timestamp arrivalDate, Plane plane, BigDecimal price, BigDecimal taxPercentage, BigDecimal surchargePercentage, boolean isCanceled, Integer sellSeats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.plane = plane;
        this.price = price;
        this.taxPercentage = taxPercentage;
        this.surchargePercentage = surchargePercentage;
        this.isCanceled = isCanceled;
        this.sellSeats = sellSeats;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
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

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane planeId) {
        this.plane = planeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public BigDecimal getSurchargePercentage() {
        return surchargePercentage;
    }

    public void setSurchargePercentage(BigDecimal surchargePercentage) {
        this.surchargePercentage = surchargePercentage;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public Integer getSellSeats() {
        return sellSeats;
    }

    public void setSellSeats(Integer sellSeats) {
        this.sellSeats = sellSeats;
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