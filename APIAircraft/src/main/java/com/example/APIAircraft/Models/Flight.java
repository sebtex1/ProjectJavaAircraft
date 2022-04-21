package com.example.APIAircraft.Models;

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

    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public Float getTrueTrack() {
        return trueTrack;
    }

    public void setTrueTrack(Float trueTrack) {
        this.trueTrack = trueTrack;
    }
}
