package com.example.AircaftStats.Tasks;

import com.example.AircaftStats.Models.Plane;
import com.example.AircaftStats.Services.PlaneService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class GetPlanes {

    @Autowired
    static PlaneService planeService;

    @Scheduled(fixedDelay = 10000)
    public void increment() {
        // Methode depuis Java 11 (java.net.http.HttpClient)
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://YnovSebTex:18051998St&@opensky-network.org/api/states/all")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(GetPlanes::parse)
                .join();
    }

    public static String parse(String responseBody) {
        JSONObject data = new JSONObject(responseBody);
        JSONArray planes = new JSONArray(data.getJSONArray("states"));
        for (int i = 0; i < planes.length(); i++) {
            JSONArray plane = planes.getJSONArray(i);
            String icao24 = plane.getString(0);
            String callSign = plane.getString(1);
            // Float au lieu de float pour handle null
            Float longitude = getFloat(plane, 5, null);
            Float latitude = getFloat(plane, 6, null);
            boolean onGround = plane.getBoolean(8);
            Float trueTrack = getFloat(plane, 10, null); // Sens de l'avion (nord = 0° sud = 180°)

            Plane planeObject = new Plane(icao24, callSign, longitude, latitude, onGround, trueTrack);

            System.out.println(planeObject);
            planeService.insertPlane(planeObject);
        }
        return null;
    }

    public static Float getFloat(JSONArray plane, int index, Float defaultValue) { // au cas où un float est null
        try {
            return plane.getFloat(index);
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
