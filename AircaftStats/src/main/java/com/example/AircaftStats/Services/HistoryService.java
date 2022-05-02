package com.example.AircaftStats.Services;

import com.example.AircaftStats.Models.History;
import com.example.AircaftStats.Repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Service
@Configurable
public class HistoryService {
    HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) { this.historyRepository = historyRepository; }

    public void insertAllHistory(Iterable<History> allHistory) {
        historyRepository.saveAll(allHistory);
    }
}
