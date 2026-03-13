package com.example.smartpark.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.smartpark.model.dto.CheckInDTO;
import com.example.smartpark.model.dto.VehicleDTO;
import com.example.smartpark.model.entity.ParkingLot;
import com.example.smartpark.model.entity.Vehicle;
import com.example.smartpark.model.entity.utils.VehicleType;
import com.example.smartpark.repository.ParkingLotRepository;
import com.example.smartpark.repository.VehicleRepository;
import com.example.smartpark.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ParkingLotRepository parkingLotRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ParkingLotRepository parkingLotRepository) {
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
    }
    
    @Override
    public Vehicle registerVehicle(VehicleDTO dto) {
        if(vehicleRepository.existsByLicensePlate(dto.getLicensePlate())) {
            throw new RuntimeException("Vehicle with License Plate '" + dto.getLicensePlate() + "' already registered.");
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(dto.getLicensePlate());
        
        VehicleType type;
        try {
            type = VehicleType.valueOf(dto.getType().toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid vehicle type: " + dto.getType());
        }
        vehicle.setType(type);
        vehicle.setOwner_name(dto.getOwnerName());

        return vehicleRepository.save(vehicle);
    }

    @SuppressWarnings("null") // validation check in DTO
    @Override
    public Vehicle checkInVehicleToLot(CheckInDTO dto) {
        Vehicle vehicle = vehicleRepository.findById(dto.getLicensePlate()).orElseThrow(() -> new RuntimeException("Vehicle not found."));
        if (vehicle.getParkingLot() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle is already parked.");
        }

        ParkingLot lot = parkingLotRepository.findById(dto.getLotId()).orElseThrow(() -> new RuntimeException("Parking lot not found"));
        if(lot.getOccupiedSpaces() >= lot.getCapacity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parking lot is full.");
        }

        vehicle.setParkingLot(lot);
        lot.setOccupiedSpaces(lot.getOccupiedSpaces() + 1);

        parkingLotRepository.save(lot);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle checkOutVehicleToLot(String licensePlate) {
        if(!licensePlate.isEmpty()) {
            Vehicle vehicle = vehicleRepository.findById(licensePlate).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found"));

            if (vehicle.getParkingLot() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle is not parked in any lot");
            }

            ParkingLot lot = vehicle.getParkingLot();
            lot.setOccupiedSpaces(lot.getOccupiedSpaces() - 1);
            
            vehicle.setParkingLot(null);
            parkingLotRepository.save(lot);
            return vehicleRepository.save(vehicle);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
    }

    @Override
    public List<Vehicle> getVehiclesInLot(String lotId) {
        if(!lotId.isEmpty()) {
            ParkingLot lot = parkingLotRepository.findById(lotId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking lot not found"));
            return vehicleRepository.findAllByParkingLot_LotId(lot.getLotId());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking lot not found");
    }
}
