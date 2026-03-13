package com.example.smartpark;

import com.example.smartpark.controller.ParkingLotController;
import com.example.smartpark.model.dto.ParkingLotDTO;
import com.example.smartpark.model.dto.ParkingLotStatusDTO;
import com.example.smartpark.model.dto.ParkingLotStatusRequestDTO;
import com.example.smartpark.model.entity.ParkingLot;
import com.example.smartpark.service.ParkingLotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ParkingLotController.class)
class ParkingLotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingLotService parkingLotService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void hello_returnsHelloWorld() throws Exception {
        when(parkingLotService.hello()).thenReturn("Hello World");

        mockMvc.perform(get("/api/v1/parking-lot"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }

    @Test
    void registerParkingLot_success() throws Exception {
        ParkingLotDTO dto = new ParkingLotDTO("LOT1", "Main Street", 10);
        ParkingLot lot = new ParkingLot("LOT1", "Main Street", 10, 0); // initialize all fields

        when(parkingLotService.registerParkingLot(any(ParkingLotDTO.class)))
            .thenReturn(lot);

        mockMvc.perform(post("/api/v1/parking-lot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lotId").value("LOT1")) // now it exists
                .andExpect(jsonPath("$.location").value("Main Street"))
                .andExpect(jsonPath("$.capacity").value(10))
                .andExpect(jsonPath("$.occupiedSpaces").value(0));
    }

    @Test
    void getParkingLotStatus_success() throws Exception {
        ParkingLotStatusRequestDTO req = new ParkingLotStatusRequestDTO();
        req.setLotId("LOT1");

        ParkingLotStatusDTO status = new ParkingLotStatusDTO("LOT1", 10, 3, 7);
        when(parkingLotService.getParkingLotStatus("LOT1")).thenReturn(status);

        mockMvc.perform(post("/api/v1/parking-lot/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.availableSpaces").value(7));
    }
}