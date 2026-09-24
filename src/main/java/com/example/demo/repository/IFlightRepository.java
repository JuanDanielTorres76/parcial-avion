package com.example.demo.repository;

import com.example.demo.model.Flight;

import java.util.List;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IFlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findTop5ByArrivalDateBetweenOrderByEstimatedPassengersDesc(Timestamp start, Timestamp end);

}