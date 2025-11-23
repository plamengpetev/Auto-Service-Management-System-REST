package com.example.mechanicservice.service;

import com.example.mechanicservice.exception.NoAvailableMechanicException;
import com.example.mechanicservice.model.Mechanic;
import com.example.mechanicservice.repository.MechanicRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MechanicServiceTest {

    @Test
    void testAssignMechanic_WhenAvailable() {
        MechanicRepository repo = mock(MechanicRepository.class);
        MechanicService service = new MechanicService(repo);

        Mechanic m = new Mechanic();
        m.setId(UUID.randomUUID());
        m.setAvailable(true);
        m.setName("John");
        m.setSpecialization("Engine");

        when(repo.findByAvailableTrue()).thenReturn(List.of(m));

        Mechanic assigned = service.assignMechanic();

        assertNotNull(assigned);
        assertFalse(assigned.isAvailable());
        verify(repo).save(any(Mechanic.class));
    }

    @Test
    void testAssignMechanic_NoMechanics_Throws() {
        MechanicRepository repo = mock(MechanicRepository.class);
        MechanicService service = new MechanicService(repo);

        when(repo.findByAvailableTrue()).thenReturn(List.of());

        assertThrows(NoAvailableMechanicException.class, service::assignMechanic);
    }
}
