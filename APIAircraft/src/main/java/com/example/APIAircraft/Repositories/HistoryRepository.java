package com.example.APIAircraft.Repositories;

import com.example.APIAircraft.Models.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, UUID> {

    @Query("SELECT h.time FROM History h GROUP BY h.time ORDER BY time desc")
    List<Long> getTimes();
    List<History> findByTime(long time);
}
