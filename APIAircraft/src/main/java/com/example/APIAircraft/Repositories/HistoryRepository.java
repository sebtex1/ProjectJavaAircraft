package com.example.APIAircraft.Repositories;

import com.example.APIAircraft.Models.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, UUID> {

}
