package com.example.APIAircraft.Services;

import com.example.APIAircraft.Models.Flight;
import com.example.APIAircraft.Repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) { this.flightRepository = flightRepository; }

    public Flight getFlightById(String id) { return flightRepository.findById(id).get(); }
    public List<Flight> getAllFlights() { return flightRepository.findAll(); }
}
