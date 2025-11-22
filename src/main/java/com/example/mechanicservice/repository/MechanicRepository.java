package com.example.mechanicservice.repository;

import com.example.mechanicservice.model.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, UUID> {

    List<Mechanic> findByAvailableTrue();
}
