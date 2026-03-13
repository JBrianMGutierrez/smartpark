package com.example.smartpark.model.entity;

import com.example.smartpark.model.entity.utils.VehicleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="vehicle")
@Data @NoArgsConstructor @AllArgsConstructor
public class Vehicle {

    @Id
    @Column(length=50, unique = true)
    @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "License plate can only contain letters, numbers, and dashes")
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type;

    private String owner_name;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private ParkingLot parkingLot;
}
