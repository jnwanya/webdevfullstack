package com.devopsbuddy.test.integration;

import com.devopsbuddy.DevopsbuddyApplication;
import com.devopsbuddy.backend.persistence.domains.backend.Role;
import com.devopsbuddy.backend.persistence.domains.backend.User;
import com.devopsbuddy.backend.persistence.domains.backend.UserRole;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlanEnums;
import com.devopsbuddy.enums.RoleEnums;
import com.devopsbuddy.utils.UserUtils;
import org.junit.Assert;
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
 * Thu, 28 Dec, 2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevopsbuddyApplication.class)
public class UserIntegrationTest {

    @Autowired
    private UserService userService;

    @Rule
    public TestName testName = new TestName();

    @Test
    public void testCreateNewUser(){

        String username = testName.getMethodName();
        String email = testName.getMethodName() + ".devopsbuddy.com";

        User basicUser = UserUtils.createBasicUser(username, email);
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(basicUser, new Role(RoleEnums.PRO));
        userRoles.add(userRole);

        User createdUser = userService.createUser(basicUser, PlanEnums.PRO, userRoles);
        Assert.assertTrue(createdUser.getId() != 0);
        Assert.assertNotNull(createdUser.getPlan());

    }
}
