package com.example.demo.repository;

import com.example.demo.model.Airport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAirportRepository extends JpaRepository<Airport, Long> {

    List<Airport> findDistinctByArrivingFlightsOriginAirportName(String originName);
    
}
