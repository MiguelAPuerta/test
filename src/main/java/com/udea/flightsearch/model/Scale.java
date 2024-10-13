package com.udea.flightsearch.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Scale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scale_id", nullable = false)
    private Long scaleId;

    @ManyToOne()
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne()
    @JoinColumn(name = "airport_id", referencedColumnName = "airport_id", nullable = false)
    private Airport airport;

    @Column(name = "scale_order", nullable = false)
    private Integer scaleOrder;

    @Column(name = "departure_date", nullable = false)
    private Timestamp departureDate;
    @Column(name = "arrival_date", nullable = false)
    private Timestamp arrivalDate;

    @ManyToOne()
    @JoinColumn(name = "plane_id", referencedColumnName = "plane_id", nullable = false)
    private Plane plane;

    @PrePersist
    @PreUpdate
    public void validateDates() {
        if (departureDate.after(arrivalDate)) {
            throw new IllegalArgumentException("Departure date must be before arrival date");
        }
    }

    public Scale() {
    }

    public Scale(Flight flight, Airport airport, Timestamp departureDate, Timestamp arrivalDate, Plane plane, Integer scaleOrder) {
        this.flight = flight;
        this.airport = airport;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.plane = plane;
        this.scaleOrder = scaleOrder;
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

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Integer getScaleOrder() {
        return scaleOrder;
    }

    public void setScaleOrder(Integer scaleOrder) {
        this.scaleOrder = scaleOrder;
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
