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
    final String icao24;
    @Column(name = "plane_name")
    final String callSign;
    final Float longitude;
    final Float latitude;
    @Column(name = "on_ground")
    final boolean onGround;
    @Column(name = "true_track")
    final Float trueTrack;

    public Plane(String icao24, String callSign, Float longitude, Float latitude, boolean onGround, Float trueTrack) {
        this.icao24 = icao24;
        this.callSign = callSign;
        this.longitude = longitude;
        this.latitude = latitude;
        this.onGround = onGround;
        this.trueTrack = trueTrack;
    }

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
