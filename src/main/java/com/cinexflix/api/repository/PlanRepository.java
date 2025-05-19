package com.cinexflix.api.repository;

import com.cinexflix.api.model.Plan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanRepository extends MongoRepository<Plan, String> {
}
