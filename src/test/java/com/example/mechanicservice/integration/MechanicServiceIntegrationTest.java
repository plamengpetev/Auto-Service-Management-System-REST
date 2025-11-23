package com.example.mechanicservice.integration;

import com.example.mechanicservice.exception.NoAvailableMechanicException;
import com.example.mechanicservice.model.Mechanic;
import com.example.mechanicservice.repository.MechanicRepository;
import com.example.mechanicservice.service.MechanicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MechanicServiceIntegrationTest {

    @Autowired
    private MechanicRepository repo;

    @Autowired
    private MechanicService service;

    @Test
    void testAssignMechanic_Integration() {
        repo.deleteAll();

        Mechanic m = Mechanic.builder()
                .name("Peter")
                .specialization("Brakes")
                .available(true)
                .build();

        repo.save(m); // <-- ID се генерира правилно от Hibernate

        Mechanic assigned = service.assignMechanic();

        assertNotNull(assigned);
        assertFalse(assigned.isAvailable());
    }

    @Test
    void testAssignMechanic_NoAvailableMechanics() {
        repo.deleteAll();

        assertThrows(NoAvailableMechanicException.class,
                () -> service.assignMechanic());
    }
}
