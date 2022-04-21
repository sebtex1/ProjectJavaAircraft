package com.example.AircaftStats.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @Column(name = "id_flight")
    private String icao24;
    private Float longitude;
    private Float latitude;
    @Column(name = "on_ground")
    private boolean onGround;
    @Column(name = "true_track")
    private Float trueTrack;

    public Flight(String icao24, Float longitude, Float latitude, boolean onGround, Float trueTrack) {
        this.icao24 = icao24;
        this.longitude = longitude;
        this.latitude = latitude;
        this.onGround = onGround;
        this.trueTrack = trueTrack;
    }

    public Flight() {}

    @Override
    public String toString() {
        return "Flight{" +
                "icao24=" + icao24 +
                ", longitude='" + longitude +
                ", latitude='" + latitude +
                ", onGround='" + onGround +
                ", trueTrack='" + trueTrack +
                '}';
    }
}
