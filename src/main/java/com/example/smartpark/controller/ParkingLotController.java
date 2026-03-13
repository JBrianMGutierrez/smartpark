package com.example.smartpark.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartpark.model.dto.PagingRequestDTO;
import com.example.smartpark.model.dto.ParkingLotDTO;
import com.example.smartpark.model.dto.ParkingLotStatusDTO;
import com.example.smartpark.model.dto.ParkingLotStatusRequestDTO;
import com.example.smartpark.model.entity.ParkingLot;
import com.example.smartpark.service.ParkingLotService;




@RestController
@RequestMapping("api/v1/parking-lot")
public class ParkingLotController {
    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @GetMapping
    public String hello() {
        return parkingLotService.hello();
    }

    @PostMapping
    public ParkingLot registerParkingLot(@RequestBody ParkingLotDTO dto) {
        return parkingLotService.registerParkingLot(dto);
    }
    
    @PostMapping("/status")
    public ParkingLotStatusDTO getParkingLotStatus(@RequestBody ParkingLotStatusRequestDTO dto) {
        return parkingLotService.getParkingLotStatus(dto.getLotId());
    }

    @GetMapping("/list")
    public Page<ParkingLot> getParkingLots(@RequestBody PagingRequestDTO request) {
        PageRequest pageable = PageRequest.of(request.getPage(), request.getSize());
        return parkingLotService.getParkingLots(pageable);
    }
}
