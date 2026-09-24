package com.example.demo.controller;

import com.example.demo.model.Airline;
import com.example.demo.repository.IAirlineRepository;
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
@RequestMapping("/airlines")
@RequiredArgsConstructor
public class AirlineController {

    private final IAirlineRepository airlineRepository;

    @GetMapping
    public List<Airline> findAllAirlines() {

        return airlineRepository.findAll();
    }


    @GetMapping("/route")
    public List<Airline> findByRoute(@RequestParam String origin,@RequestParam String destination) {
    
        return airlineRepository.findDistinctByAirplanesFlightsOriginAirportNameAndAirplanesFlightsDestinationAirportName(origin, destination);
    
    }

    @GetMapping("/city")
    public List<Airline> findByCity(@RequestParam String city) {

        return airlineRepository.findDistinctByAirplanesFlightsOriginAirportCityOrAirplanesFlightsDestinationAirportCity(city, city);

    }

    @GetMapping("/arrivals")
    public List<Airline> findArrivals(
    
        @RequestParam String airport,
    
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
    
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
    
            return airlineRepository.findDistinctByAirplanesFlightsDestinationAirportNameAndAirplanesFlightsArrivalDateBetween(airport, Timestamp.valueOf(start), Timestamp.valueOf(end));

    }

}
