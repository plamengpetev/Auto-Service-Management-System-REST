package com.example.mechanicservice.web;

import com.example.mechanicservice.model.Mechanic;
import com.example.mechanicservice.repository.MechanicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MechanicControllerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MechanicRepository repo;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID mechanicId;

    @BeforeEach
    void setup() {
        repo.deleteAll();

        Mechanic m = Mechanic.builder()
                .name("Mike")
                .specialization("Tires")
                .available(true)
                .build();

        repo.save(m);      // <-- Hibernate генерира ID
        mechanicId = m.getId();
    }


    @Test
    void testGetAllMechanics() throws Exception {
        mockMvc.perform(get("/api/mechanics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Mike"));
    }

    @Test
    void testAssignMechanic() throws Exception {
        mockMvc.perform(post("/api/mechanics/assign"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(false));
    }

    @Test
    void testUpdateAvailability() throws Exception {
        String json = """
                {"available": false}
                """;

        mockMvc.perform(put("/api/mechanics/" + mechanicId + "/availability")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}
