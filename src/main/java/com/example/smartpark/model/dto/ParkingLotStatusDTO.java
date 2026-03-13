package com.example.smartpark.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ParkingLotStatusDTO {
    private String lotId;
    private int capacity;
    private int occupiedSpaces;
    private int availableSpaces;
}
