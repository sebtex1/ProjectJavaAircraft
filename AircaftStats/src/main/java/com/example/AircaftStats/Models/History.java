package com.example.AircaftStats.Models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue
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

    @Override
    public String toString() {
        return "History{" +
                "idHistory=" + idHistory +
                ", icao24='" + icao24 +
                ", longitude='" + longitude +
                ", latitude='" + latitude +
                ", time='" + time +
                '}';
    }
}
