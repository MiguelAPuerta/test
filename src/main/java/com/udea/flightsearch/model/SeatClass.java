package com.udea.flightsearch.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class SeatClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    private String name;
    private String description;

    @OneToMany(mappedBy = "seatClass", cascade = CascadeType.ALL)
    private List<Seat> seats = new ArrayList<>();;

    public SeatClass() {
    }

    public SeatClass(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeatClass seatClass)) return false;
        return Objects.equals(getClassId(), seatClass.getClassId());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
