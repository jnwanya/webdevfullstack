package com.devopsbuddy.test.integration;

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
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jnwanya on
 * Thu, 28 Dec, 2017.
 */
public abstract class AbstractIntegrationTest {

    @Autowired
    protected PlanRepository planRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected RoleRepository roleRepository;

    protected Plan createBasicPlan(PlanEnums planEnums){
        return new Plan(planEnums);
    }
    protected Role createBasicRole(RoleEnums roleEnums){
        return new Role(roleEnums);
    }

    protected User createUser(String username, String email){
        Plan basicPlan = new Plan(PlanEnums.BASIC);
        planRepository.save(basicPlan);

        User basicUser = UserUtils.createBasicUser(username, email);
        basicUser.setPlan(basicPlan);

        Role basicRole = new Role(RoleEnums.BASIC);
        roleRepository.save(basicRole);

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(basicUser, basicRole));

        basicUser.getUserRoles().addAll(userRoles);
        basicUser = userRepository.save(basicUser);

        return basicUser;
    }
    protected User createUser(TestName testName){
       return createUser(testName.getMethodName(), testName.getMethodName()+".devopsbuddy.com");
    }
}
