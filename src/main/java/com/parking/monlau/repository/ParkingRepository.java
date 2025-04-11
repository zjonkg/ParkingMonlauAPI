package com.parking.monlau.repository;


import com.parking.monlau.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParkingRepository extends JpaRepository<Parking, Long> {}