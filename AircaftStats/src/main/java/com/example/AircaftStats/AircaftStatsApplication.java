package com.example.AircaftStats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AircaftStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AircaftStatsApplication.class, args);
	}

}
