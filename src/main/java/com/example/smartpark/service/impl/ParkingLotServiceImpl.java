package com.example.smartpark.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.smartpark.model.dto.ParkingLotDTO;
import com.example.smartpark.model.dto.ParkingLotStatusDTO;
import com.example.smartpark.model.entity.ParkingLot;
import com.example.smartpark.repository.ParkingLotRepository;
import com.example.smartpark.service.ParkingLotService;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public String hello() {
        return "Hello World";
    }

    @Override
    public ParkingLot registerParkingLot(ParkingLotDTO dto) {
        if(parkingLotRepository.existsByLotId(dto.getLotId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking lot with ID '" + dto.getLotId() + "' already exists.");
        }
        ParkingLot lot = new ParkingLot();
        lot.setLotId(dto.getLotId());
        lot.setLocation(dto.getLocation());
        lot.setCapacity(dto.getCapacity());
        lot.setOccupiedSpaces(0);

        return parkingLotRepository.save(lot);
    }

    @Override
    public ParkingLotStatusDTO getParkingLotStatus(String lotId) {
        ParkingLot lot = parkingLotRepository.findById(lotId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking lot not found"));
        int availableSpaces = lot.getCapacity() - lot.getOccupiedSpaces();

        return new ParkingLotStatusDTO(
            lot.getLotId(),
            lot.getCapacity(),
            lot.getOccupiedSpaces(),
            availableSpaces
        );
    }

    @Override
    public Page<ParkingLot> getParkingLots(Pageable pageable) {
        return parkingLotRepository.findAll(pageable);
    }
}
