package com.cinexflix.api.controller;

import com.cinexflix.api.model.Plan;
import com.cinexflix.api.repository.PlanRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/planes")
@CrossOrigin(origins = "*") // Para pruebas
public class PlanController {

    private final PlanRepository planRepository;

    public PlanController(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @GetMapping
    public List<Plan> obtenerPlanes() {
        return planRepository.findAll();
    }
}
