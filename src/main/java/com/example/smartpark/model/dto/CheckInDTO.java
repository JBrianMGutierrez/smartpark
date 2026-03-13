package com.example.smartpark.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CheckInDTO {
    
    @NotBlank @NotNull
    private String licensePlate;
    @NotBlank @NotNull
    private String lotId;
}
