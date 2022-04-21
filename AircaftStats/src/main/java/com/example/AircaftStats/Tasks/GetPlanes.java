package com.example.AircaftStats.Tasks;

import com.example.AircaftStats.Models.Plane;
import com.example.AircaftStats.Services.PlaneService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.Buffer;

@Component
public class GetPlanes {

    @Autowired
    PlaneService planeService;
    private static HttpURLConnection connection;

    @Scheduled(fixedDelay = 10000)
    public void getPlanes() {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL("https://YnovSebTex:18051998St&@opensky-network.org/api/states/all");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                    reader.close();

            } else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                    reader.close();
                }
            //System.out.println(responseContent.toString());
            parse(responseContent.toString());
        } catch (ProtocolException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            connection.disconnect();
        }

        // Methode depuis Java 11 (java.net.http.HttpClient)
        /* HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://YnovSebTex:18051998St&@opensky-network.org/api/states/all")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(GetPlanes::parse)
                .join(); */
    }

    public String parse(String responseBody) {
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
