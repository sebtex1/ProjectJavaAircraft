package com.example.APIAircraft.Services;

import com.example.APIAircraft.Models.History;
import com.example.APIAircraft.Repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HistoryService {

    HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) { this.historyRepository = historyRepository; }

    public History getHistoryById(UUID id) { return historyRepository.findById(id).get(); }
    public List<History> getAllHistory() { return historyRepository.findAll(); }
}
