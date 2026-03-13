package com.example.smartpark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smartpark.model.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    boolean existsByLicensePlate(String licenseId);
    List<Vehicle> findAllByParkingLot_LotId(String lotId);
}
