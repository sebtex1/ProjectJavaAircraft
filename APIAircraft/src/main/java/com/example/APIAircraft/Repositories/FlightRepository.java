package com.example.APIAircraft.Repositories;

import com.example.APIAircraft.Models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

}
