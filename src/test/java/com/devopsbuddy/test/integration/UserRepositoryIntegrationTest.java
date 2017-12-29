package com.devopsbuddy.test.integration;

import com.devopsbuddy.DevopsbuddyApplication;
import com.devopsbuddy.backend.persistence.domains.backend.Plan;
import com.devopsbuddy.backend.persistence.domains.backend.Role;
import com.devopsbuddy.backend.persistence.domains.backend.User;
import com.devopsbuddy.backend.persistence.domains.backend.UserRole;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * Created by Jnwanya on
 * Wed, 27 Dec, 2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevopsbuddyApplication.class)
public class UserRepositoryIntegrationTest extends AbstractIntegrationTest{

    @Rule public TestName testName = new TestName();

    @Before
    public void init(){
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(roleRepository);
    }

    @Test
    public void testCreatePlan(){
        Plan basicPlan = createBasicPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);
        Plan retrievedPlan = planRepository.findOne(PlansEnum.BASIC.getId());
        Assert.assertNotNull(retrievedPlan);
    }
    @Test
    public void testCreateNewRole(){
        Role userRole = createBasicRole(RolesEnum.BASIC);
        roleRepository.save(userRole);

        Role retrievedRole = roleRepository.findOne(RolesEnum.BASIC.getId());
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

    @Test
    public void testGetUserByEmail() throws Exception {
        User user = createUser(testName);

        User newlyFoundUser = userRepository.findByEmail(user.getEmail());
        Assert.assertNotNull(newlyFoundUser);
        Assert.assertNotNull(newlyFoundUser.getId());
    }

    @Test
    public void testUpdateUserPassword() throws Exception {
        User user = createUser(testName);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());

        String newPassword = UUID.randomUUID().toString();

        userRepository.updateUserPassword(user.getId(), newPassword);

        user = userRepository.findOne(user.getId());
        Assert.assertEquals(newPassword, user.getPassword());

    }




}
