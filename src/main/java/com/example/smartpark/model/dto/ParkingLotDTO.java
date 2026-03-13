package com.example.smartpark.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ParkingLotDTO {
    
    @NotBlank @Size(max=50)
    private String lotId;

    @NotBlank
    private String location;

    @Min(1)
    private Integer capacity;
}
