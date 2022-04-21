package com.example.AircaftStats.Repositories;

import com.example.AircaftStats.Models.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, String> {
}
