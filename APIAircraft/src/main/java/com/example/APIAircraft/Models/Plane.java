package com.example.APIAircraft.Models;

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

    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }
}

