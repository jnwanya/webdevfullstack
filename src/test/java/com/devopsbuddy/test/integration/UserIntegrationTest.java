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
import com.devopsbuddy.utils.UserUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
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
public class UserIntegrationTest extends AbstractIntegrationTest{

    @Rule public TestName testName = new TestName();

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
    @Test
    public void testCreateNewUser() throws Exception{

        String username = testName.getMethodName();
        String email = testName.getMethodName() + ".devopsbuddy.com";

        User newUser = createUser(username, email);
        Assert.assertNotNull(newUser);
        Assert.assertTrue(newUser.getId() != 0);
        Assert.assertNotNull(newUser.getPlan());
        Assert.assertNotNull(newUser.getPlan().getId());
        for (UserRole userRole : newUser.getUserRoles()) {
            Assert.assertNotNull(userRole.getRole());
            Assert.assertNotNull(userRole.getRole().getId());
        }
    }

    @Test
    public void testDeleteUser() throws Exception {
        String username = testName.getMethodName();
        String email = testName.getMethodName() + ".devopsbuddy.com";
        User newUser = createUser(username, email);
        userRepository.delete(newUser.getId());
    }




}
