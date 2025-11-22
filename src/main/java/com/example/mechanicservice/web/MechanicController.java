package com.example.mechanicservice.web;

import com.example.mechanicservice.model.Mechanic;
import com.example.mechanicservice.service.MechanicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/mechanics")
public class MechanicController {

    private final MechanicService mechanicService;

    public MechanicController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    @GetMapping
    public List<Mechanic> getAll() {
        return mechanicService.getAllMechanics();
    }

    @GetMapping("/{id}")
    public Mechanic getById(@PathVariable("id") UUID id) {
        return mechanicService.getById(id);
    }

    @PostMapping("/assign")
    public Mechanic assignMechanic() {
        return mechanicService.assignMechanic();
    }

    @PutMapping("/{id}/availability")
    public void updateAvailability(
            @PathVariable("id") UUID id,
            @RequestBody Map<String, Boolean> body) {
        mechanicService.updateAvailability(id, body.get("available"));
    }
}
