package com.example.APIAircraft.Repositories;

import com.example.APIAircraft.Models.Flight;
import com.example.APIAircraft.Models.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, String> {

}
