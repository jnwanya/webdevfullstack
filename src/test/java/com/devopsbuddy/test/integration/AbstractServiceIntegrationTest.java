package com.devopsbuddy.test.integration;


import com.devopsbuddy.backend.persistence.domains.backend.Role;
import com.devopsbuddy.backend.persistence.domains.backend.User;
import com.devopsbuddy.backend.persistence.domains.backend.UserRole;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlanEnums;
import com.devopsbuddy.enums.RoleEnums;
import com.devopsbuddy.utils.UserUtils;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Jnwanya on
 * Thu, 28 Dec, 2017.
 */
public abstract class AbstractServiceIntegrationTest {

    @Autowired
    protected UserService userService;

    protected User createUser(TestName testName) {
        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";

        Set<UserRole> userRoles = new HashSet<>();
        User basicUser = UserUtils.createBasicUser(username, email);
        userRoles.add(new UserRole(basicUser, new Role(RoleEnums.BASIC)));

        return userService.createUser(basicUser, PlanEnums.BASIC, userRoles);
    }
}
