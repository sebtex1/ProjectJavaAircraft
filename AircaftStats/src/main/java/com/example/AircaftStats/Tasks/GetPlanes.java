package com.example.AircaftStats.Tasks;

import com.example.AircaftStats.Models.Flight;
import com.example.AircaftStats.Models.History;
import com.example.AircaftStats.Models.Plane;
import com.example.AircaftStats.Services.FlightService;
import com.example.AircaftStats.Services.HistoryService;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetPlanes {

    @Autowired
    PlaneService planeService;
    @Autowired
    FlightService flightService;
    @Autowired
    HistoryService historyService;
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
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

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
    }

    public String parse(String responseBody) {
        List<Plane> planeList = new ArrayList<>();
        List<Flight> flightList = new ArrayList<>();
        List<History> historyList = new ArrayList<>();
        JSONObject data = new JSONObject(responseBody);
        long time = data.getLong("time");
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

            Plane planeObject = new Plane(icao24, callSign);
            planeList.add(planeObject);
            Flight flightObject = new Flight(icao24, longitude, latitude, onGround, trueTrack);
            flightList.add(flightObject);
            History historyObject = new History(icao24, longitude, latitude, time);
            historyList.add(historyObject);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime nowStart = LocalDateTime.now();
        System.out.println("Starting save " + dtf.format(nowStart));
        Iterable<Plane> allPlanes = planeList;
        planeService.insertALLPlanes(allPlanes);
        LocalDateTime nowPlane = LocalDateTime.now();
        System.out.println("done save all planes" + dtf.format(nowPlane));
        Iterable<Flight> allFlights = flightList;
        flightService.insertAllFlight(allFlights);
        LocalDateTime nowFlight = LocalDateTime.now();
        System.out.println("done save all flights" + dtf.format(nowFlight));
        Iterable<History> allHistory = historyList;
        historyService.insertAllHistory(allHistory);
        LocalDateTime nowHistory = LocalDateTime.now();
        System.out.println("done save all the history" + dtf.format(nowHistory));
        LocalDateTime nowEnd = LocalDateTime.now();
        System.out.println("Starting save " + dtf.format(nowEnd));
        System.out.println("Time spend = " + (nowEnd.getMinute() - nowStart.getMinute()) + "minutes " +
                (nowEnd.getSecond() - nowStart.getSecond()) + "seconds "
                + (nowEnd.getNano() - nowStart.getNano()) + "nano");
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
