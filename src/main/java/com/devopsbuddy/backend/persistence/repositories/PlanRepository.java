package com.devopsbuddy.backend.persistence.repositories;

import com.devopsbuddy.backend.persistence.domains.backend.Plan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jnwanya on
 * Wed, 27 Dec, 2017.
 */
@Repository
public interface PlanRepository extends CrudRepository<Plan, Integer> {
}
