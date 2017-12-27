package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domains.backend.Plan;
import com.devopsbuddy.backend.persistence.domains.backend.User;
import com.devopsbuddy.backend.persistence.domains.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlanEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Jnwanya on
 * Wed, 27 Dec, 2017.
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(User user, PlanEnums planEnums, Set<UserRole> userRoles) {
        Plan plan = new Plan(planEnums);
        if (!planRepository.exists(plan.getId())) {
            planRepository.save(plan);
        }
        user.setPlan(plan);
        for (UserRole role : userRoles) {
            roleRepository.save(role.getRole());
        }
        user.getUserRoles().addAll(userRoles);
        user = userRepository.save(user);

        return user;
    }
}
