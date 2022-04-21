package com.example.AircaftStats.Repositories;

import com.example.AircaftStats.Models.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, String> {

}
