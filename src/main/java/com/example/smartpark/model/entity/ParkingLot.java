package com.example.smartpark.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="parking_lot")
@Data @NoArgsConstructor @AllArgsConstructor
public class ParkingLot {
    @Id
    @Column(length=50, unique = true)
    private String lotId;
    private String location;
    private Integer capacity = 1;
    private Integer occupiedSpaces = 0;
}
