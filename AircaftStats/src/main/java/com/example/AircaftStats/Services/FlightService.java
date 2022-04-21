package com.example.AircaftStats.Services;

import com.example.AircaftStats.Models.Flight;
import com.example.AircaftStats.Repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Service
@Configurable
public class FlightService {
    FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) { this.flightRepository = flightRepository; }

    public void insertFlight(Flight flight) { flightRepository.save(flight); }
    public void insertAllFlight(Iterable<Flight> allFlights) { flightRepository.saveAll(allFlights); }
}
