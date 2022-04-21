package com.example.APIAircraft.Services;

import com.example.APIAircraft.Models.Plane;
import com.example.APIAircraft.Repositories.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneService {

    PlaneRepository planeRepository;

    @Autowired
    public PlaneService(PlaneRepository planeRepository) { this.planeRepository = planeRepository; }

    public Plane getPlaneById(String id) { return planeRepository.findById(id).get(); }
    public List<Plane> getAllPlane() { return planeRepository.findAll(); }

}
