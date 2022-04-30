package com.example.APIAircraft.Models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "history")
public class History {

    @Id
    @Column(name = "id_history", columnDefinition = "uniqueidentifier")
    private UUID idHistory;
    @Column(name = "id_flight")
    private String icao24;
    private Float longitude;
    private Float latitude;
    private long time;
    @Column(name = "true_track")
    private Float trueTrack;

    public History(String icao24, Float longitude, Float latitude, long time, Float trueTrack) {
        this.icao24 = icao24;
        this.longitude = longitude;
        this.latitude = latitude;
        this.time = time;
        this.trueTrack = trueTrack;
    }

    public History() {}

    public UUID getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(UUID idHistory) {
        this.idHistory = idHistory;
    }

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Float getTrueTrack() {
        return trueTrack;
    }

    public void setTrueTrack(Float trueTrack) {
        this.trueTrack = trueTrack;
    }
}

