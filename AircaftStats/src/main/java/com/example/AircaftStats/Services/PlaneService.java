package com.example.AircaftStats.Services;

import com.example.AircaftStats.Models.Plane;
import com.example.AircaftStats.Repositories.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Service
@Configurable
public class PlaneService {
    PlaneRepository planeRepository;

    @Autowired
    public PlaneService(PlaneRepository planeRepository) { this.planeRepository = planeRepository; }

    public void insertPlane(Plane plane) {
        planeRepository.save(plane);
    }
}
