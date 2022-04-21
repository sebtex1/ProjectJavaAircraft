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


    public Plane(String icao24, String callSign) {
        this.icao24 = icao24;
        this.callSign = callSign;
    }

    public Plane() {}

    @Override
    public String toString() {
        return "Plane{" +
                "icao24=" + icao24 +
                ", callSign='" + callSign +
                "' }";
    }
}
