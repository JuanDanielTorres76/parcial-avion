package com.example.demo.controller;

import com.example.demo.model.Airport;
import com.example.demo.model.Flight;
import com.example.demo.repository.IFlightRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final IFlightRepository flightRepository;

    @GetMapping
    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    @GetMapping("/top-airports")
    public List<Airport> findTopAirports(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return flightRepository.findTop5ByArrivalDateBetweenOrderByEstimatedPassengersDesc(Timestamp.valueOf(start), Timestamp.valueOf(end)).stream().map(Flight::getDestinationAirport).distinct().toList();
    }
}
