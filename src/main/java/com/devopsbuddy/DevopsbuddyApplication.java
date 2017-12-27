package com.devopsbuddy;

import com.devopsbuddy.backend.persistence.domains.backend.Role;
import com.devopsbuddy.backend.persistence.domains.backend.User;
import com.devopsbuddy.backend.persistence.domains.backend.UserRole;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlanEnums;
import com.devopsbuddy.enums.RoleEnums;
import com.devopsbuddy.utils.UsersUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DevopsbuddyApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DevopsbuddyApplication.class, args);
	}

	private static final Logger LOG = LoggerFactory.getLogger(DevopsbuddyApplication.class);

	@Autowired
	private UserService userService;

	@Override
	public void run(String... strings) throws Exception {
         User basicUser = UsersUtils.createBasicUser();
		 Set<UserRole> userRoles = new HashSet<>();
		 userRoles.add(new UserRole(basicUser, new Role(RoleEnums.BASIC)));
		 LOG.debug("Creating user with username {}", basicUser.getUsername());
		 userService.createUser(basicUser, PlanEnums.BASIC, userRoles);
		 LOG.info("User {} created.", basicUser.getUsername());
	}
}
