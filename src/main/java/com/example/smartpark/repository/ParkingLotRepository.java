package com.example.smartpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smartpark.model.entity.ParkingLot;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {
    boolean existsByLotId(String lotId);
}
