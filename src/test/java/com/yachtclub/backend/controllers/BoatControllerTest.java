package com.yachtclub.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yachtclub.backend.dtos.BoatDTO;
import com.yachtclub.backend.services.interfaces.BoatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BoatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private BoatService boatService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void testCreateBoat() throws Exception {
        BoatDTO boatDTO = new BoatDTO();
        boatDTO.setOwnerId(1L);
        boatDTO.setVesselNumber("AB-123");
        boatDTO.setName("The Black Pearl");
        boatDTO.setType("Sailboat");
        boatDTO.setLength(10.0);
        boatDTO.setBeam(3.5);
        boatDTO.setDraft(1.5);

        when(boatService.create(any(BoatDTO.class))).thenReturn(boatDTO);

        mockMvc.perform(post("/api/boats")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boatDTO)))
                .andExpect(status().isCreated());
    }
}
