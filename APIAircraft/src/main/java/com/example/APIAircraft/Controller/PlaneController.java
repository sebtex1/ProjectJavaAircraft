package com.example.APIAircraft.Controller;

import com.example.APIAircraft.Services.PlaneService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaneController {

    @Autowired
    PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) { this.planeService = planeService; }

    @RequestMapping(path = "/planes")
    public String getAllPlanes() {
        JSONArray allPlanes = new JSONArray(planeService.getAllPlane());
        return allPlanes.toString();
    }

    @RequestMapping(path = "/planes/{id}")
    public String getPlaneById(@PathVariable("id") String id) {
        JSONObject plane = new JSONObject(planeService.getPlaneById(id));
        return plane.toString();
    }
}
