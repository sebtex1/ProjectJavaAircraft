package com.example.APIAircraft.Controller;

import com.example.APIAircraft.Services.HistoryService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) { this.historyService = historyService; }

    @RequestMapping(path = "/history")
    public String getAllHistory() {
        JSONArray allHistory = new JSONArray(historyService.getAllHistory());
        return allHistory.toString();
    }

    @RequestMapping(path = "/history/id")
    public String getHistoryById(@RequestParam("id") UUID id) {
        JSONObject history = new JSONObject(historyService.getHistoryById(id));
        return history.toString();
    }
}
