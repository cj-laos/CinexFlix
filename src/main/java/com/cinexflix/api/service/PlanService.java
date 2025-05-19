package com.cinexflix.api.service;

import com.cinexflix.api.model.Plan;
import com.cinexflix.api.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public List<Plan> obtenerTodosLosPlanes() {
        return planRepository.findAll();
    }
}
