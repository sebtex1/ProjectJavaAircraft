package com.example.APIAircraft.Controller;

import com.example.APIAircraft.Models.Flight;
import com.example.APIAircraft.Services.FlightService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) { this.flightService = flightService; }

    @RequestMapping(path = "/flights")
    public String getAllFlights() {
        JSONArray allFlights = new JSONArray(flightService.getAllFlights());
        return allFlights.toString();
    }

    @RequestMapping(path = "/flights/{id}")
    public String getFlightById(@PathVariable("id") String id) throws JsonProcessingException {
        JSONObject flight = new JSONObject(flightService.getFlightById(id));
        return flight.toString();
    }
}
