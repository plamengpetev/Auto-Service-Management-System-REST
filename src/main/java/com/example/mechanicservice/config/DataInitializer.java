package com.example.mechanicservice.config;

import com.example.mechanicservice.model.Mechanic;
import com.example.mechanicservice.repository.MechanicRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final MechanicRepository mechanicRepository;

    public DataInitializer(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    @PostConstruct
    public void init() {
        if (mechanicRepository.count() == 0) {
            mechanicRepository.save(Mechanic.builder()
                    .name("John Peterson")
                    .specialization("Engine Specialist")
                    .available(true)
                    .build());

            mechanicRepository.save(Mechanic.builder()
                    .name("George Dimitrov")
                    .specialization("Suspension Specialist")
                    .available(true)
                    .build());

            mechanicRepository.save(Mechanic.builder()
                    .name("Glen Gregory")
                    .specialization("Electronics Specialist")
                    .available(true)
                    .build());

            System.out.println("Default mechanics added to database");
        }
    }
}
