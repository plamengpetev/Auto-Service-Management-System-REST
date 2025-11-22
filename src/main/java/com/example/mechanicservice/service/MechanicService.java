package com.example.mechanicservice.service;

import com.example.mechanicservice.exception.NoAvailableMechanicException;
import com.example.mechanicservice.model.Mechanic;
import com.example.mechanicservice.repository.MechanicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MechanicService {

    private static final Logger logger = LoggerFactory.getLogger(MechanicService.class);

    private final MechanicRepository mechanicRepository;

    public MechanicService(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    public Mechanic assignMechanic() {
        logger.info("Attempting to assign an available mechanic");

        List<Mechanic> available = mechanicRepository.findByAvailableTrue();

        if (available.isEmpty()) {
            logger.warn("No available mechanics found for assignment");
            throw new NoAvailableMechanicException("No available mechanic at the moment");
        }

        Mechanic mechanic = available.get(0);
        mechanic.setAvailable(false);

        mechanicRepository.save(mechanic);

        logger.info("Mechanic [{}] assigned successfully", mechanic.getId());
        return mechanic;
    }

    public List<Mechanic> getAllMechanics() {
        logger.info("Fetching all mechanics");
        return mechanicRepository.findAll();
    }

    public Mechanic getById(UUID id) {
        logger.info("Fetching mechanic [{}]", id);

        return mechanicRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Mechanic [{}] not found", id);
                    return new RuntimeException("Mechanic not found");
                });
    }

    public void updateAvailability(UUID mechanicId, boolean available) {
        logger.info("Updating availability of mechanic [{}] to [{}]", mechanicId, available);

        Mechanic mechanic = mechanicRepository.findById(mechanicId)
                .orElseThrow(() -> {
                    logger.warn("Mechanic [{}] not found while updating availability", mechanicId);
                    return new RuntimeException("Mechanic not found");
                });

        mechanic.setAvailable(available);
        mechanicRepository.save(mechanic);

        logger.info("Mechanic [{}] availability updated to [{}]", mechanicId, available);
    }
}
