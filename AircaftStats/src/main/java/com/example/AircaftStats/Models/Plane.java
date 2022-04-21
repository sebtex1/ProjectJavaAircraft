package com.example.AircaftStats.Models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "planes")
public class Plane {

    @Id
    @Column(name = "id_plane")
    private String icao24;
    @Column(name = "plane_name")
    private String callSign;
    private Float longitude;
    private Float latitude;
    @Column(name = "on_ground")
    private boolean onGround;
    @Column(name = "true_track")
    private Float trueTrack;


    public Plane(String icao24, String callSign, Float longitude, Float latitude, boolean onGround, Float trueTrack) {
        this.icao24 = icao24;
        this.callSign = callSign;
        this.longitude = longitude;
        this.latitude = latitude;
        this.onGround = onGround;
        this.trueTrack = trueTrack;
    }

    public Plane() {}

    @Override
    public String toString() {
        return "Plane{" +
                "icao24=" + icao24 +
                ", callSign='" + callSign + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", onGround=" + onGround +
                ", trueTrack=" + trueTrack +
                '}';
    }
}
