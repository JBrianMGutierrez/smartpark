package com.example.smartpark.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartpark.model.dto.CheckInDTO;
import com.example.smartpark.model.dto.CheckOutDTO;
import com.example.smartpark.model.dto.ParkingLotStatusRequestDTO;
import com.example.smartpark.model.dto.VehicleDTO;
import com.example.smartpark.model.entity.Vehicle;
import com.example.smartpark.service.VehicleService;




@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public Vehicle registerVehicle(@RequestBody VehicleDTO dto) {
        return vehicleService.registerVehicle(dto);
    }

    @PutMapping("/check-in")
    public Vehicle checkInVehicle(@RequestBody CheckInDTO dto) {
        return vehicleService.checkInVehicleToLot(dto);
    }

    @PutMapping("/check-out")
    public Vehicle checkoutVehicle(@RequestBody CheckOutDTO dto) {
        return vehicleService.checkOutVehicleToLot(dto.getLicensePlate());
    }

    @GetMapping("/parked")
    public List<Vehicle> getVehiclesInLot(@RequestBody ParkingLotStatusRequestDTO dto) {
        return vehicleService.getVehiclesInLot(dto.getLotId());
    }
    
}
