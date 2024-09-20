package com.udea.flightsearch.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @ManyToOne()
    @JoinColumn(name = "planeTypeId", referencedColumnName = "planeTypeId")
    private PlaneType planeType;

    private String seatNumber;

    @ManyToOne()
    @JoinColumn(name = "seatClass", referencedColumnName = "classId")
    private SeatClass seatClass;

    public Seat() {
    }

    public Seat(PlaneType planeType, String seatNumber, SeatClass seatClass) {
        this.planeType = planeType;
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public PlaneType getPlaneType() {
        return planeType;
    }

    public void setPlaneType(PlaneType planeType) {
        this.planeType = planeType;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(seatId, seat.seatId);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
