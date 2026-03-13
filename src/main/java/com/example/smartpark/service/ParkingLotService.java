package com.example.smartpark.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.smartpark.model.dto.ParkingLotDTO;
import com.example.smartpark.model.dto.ParkingLotStatusDTO;
import com.example.smartpark.model.entity.ParkingLot;

public interface ParkingLotService {
    ParkingLot registerParkingLot(ParkingLotDTO dto);
    ParkingLotStatusDTO getParkingLotStatus(String lotId);
    Page<ParkingLot> getParkingLots(Pageable pageable);
    String hello();
}
