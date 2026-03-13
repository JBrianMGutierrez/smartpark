package com.example.smartpark.service;

import java.util.List;

import com.example.smartpark.model.dto.CheckInDTO;
import com.example.smartpark.model.dto.VehicleDTO;
import com.example.smartpark.model.entity.Vehicle;

public interface VehicleService {
    Vehicle registerVehicle(VehicleDTO dto);
    Vehicle checkInVehicleToLot(CheckInDTO dto);
    Vehicle checkOutVehicleToLot(String licensePlate);
    List<Vehicle> getVehiclesInLot(String lotId);
}
