package com.devopsbuddy.test.integration;

import com.devopsbuddy.DevopsbuddyApplication;
import com.devopsbuddy.backend.persistence.domains.backend.Plan;
import com.devopsbuddy.backend.persistence.domains.backend.Role;
import com.devopsbuddy.backend.persistence.domains.backend.User;
import com.devopsbuddy.backend.persistence.domains.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlanEnums;
import com.devopsbuddy.enums.RoleEnums;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jnwanya on
 * Wed, 27 Dec, 2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevopsbuddyApplication.class)
public class RepositoriesIntegrationTest {

    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Before
    public void init(){
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(roleRepository);
    }

    @Test
    public void testCreatePlan(){
        Plan basicPlan = createBasicPlan(PlanEnums.BASIC);
        planRepository.save(basicPlan);
        Plan retrievedPlan = planRepository.findOne(PlanEnums.BASIC.getId());
        Assert.assertNotNull(retrievedPlan);
    }
    @Test
    public void testCreateNewRole(){
        Role userRole = createBasicRole(RoleEnums.BASIC);
        roleRepository.save(userRole);

        Role retrievedRole = roleRepository.findOne(RoleEnums.BASIC.getId());
        Assert.assertNotNull(retrievedRole);
    }
    private Plan createBasicPlan(PlanEnums planEnums){
        return new Plan(planEnums);
    }
    private Role createBasicRole(RoleEnums roleEnums){
        return new Role(roleEnums);
    }


}
