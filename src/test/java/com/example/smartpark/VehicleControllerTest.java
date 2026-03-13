package com.example.smartpark;

import com.example.smartpark.model.dto.*;
import com.example.smartpark.model.entity.Vehicle;
import com.example.smartpark.model.entity.utils.*;
import com.example.smartpark.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.smartpark.controller.VehicleController;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VehicleService vehicleService;

    @Test
    void testRegisterVehicle() throws Exception {
        VehicleDTO dto = new VehicleDTO("ABC-123", "Car", "John Doe");
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("ABC-123");
        vehicle.setType(VehicleType.CAR);
        vehicle.setOwner_name("John Doe");

        Mockito.when(vehicleService.registerVehicle(any(VehicleDTO.class))).thenReturn(vehicle);

        mockMvc.perform(post("/api/v1/vehicle")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.licensePlate").value("ABC-123"))
                .andExpect(jsonPath("$.type").value("CAR"))
                .andExpect(jsonPath("$.owner_name").value("John Doe"));
    }

    @Test
    void testCheckInVehicle() throws Exception {
        CheckInDTO dto = new CheckInDTO("ABC-123", "LOT-1");
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("ABC-123");

        Mockito.when(vehicleService.checkInVehicleToLot(any(CheckInDTO.class))).thenReturn(vehicle);

        mockMvc.perform(put("/api/v1/vehicle/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.licensePlate").value("ABC-123"));
    }

    @Test
    void testCheckOutVehicle() throws Exception {
        CheckOutDTO dto = new CheckOutDTO("ABC-123");
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("ABC-123");

        Mockito.when(vehicleService.checkOutVehicleToLot(eq("ABC-123"))).thenReturn(vehicle);

        mockMvc.perform(put("/api/v1/vehicle/check-out")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.licensePlate").value("ABC-123"));
    }

    @Test
    void testGetVehiclesInLot() throws Exception {
        ParkingLotStatusRequestDTO dto = new ParkingLotStatusRequestDTO("LOT-1");
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("ABC-123");

        Mockito.when(vehicleService.getVehiclesInLot(eq("LOT-1"))).thenReturn(List.of(vehicle));

        mockMvc.perform(post("/api/v1/vehicle/parked")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].licensePlate").value("ABC-123"));
    }
}