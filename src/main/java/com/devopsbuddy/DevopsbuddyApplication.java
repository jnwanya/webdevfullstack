package com.devopsbuddy;

import com.devopsbuddy.backend.persistence.domains.backend.Role;
import com.devopsbuddy.backend.persistence.domains.backend.User;
import com.devopsbuddy.backend.persistence.domains.backend.UserRole;
import com.devopsbuddy.backend.service.PlanService;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	@Autowired
	private PlanService planService;

	@Value("${webmaster.username}")
	private String webmasterUsername;

	@Value("${webmaster.password}")
	private String webmasterPassword;

	@Value("${webmaster.email}")
	private String webmasterEmail;

	@Override
	public void run(String... strings) throws Exception {

		planService.createPlan(PlansEnum.BASIC.getId());
		planService.createPlan(PlansEnum.PRO.getId());

		User basicUser = UserUtils.createBasicUser(webmasterUsername, webmasterEmail);
		basicUser.setPassword(webmasterPassword);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(basicUser, new Role(RolesEnum.ADMIN)));
		LOG.debug("Creating user with username {}", basicUser.getUsername());
		userService.createUser(basicUser, PlansEnum.PRO, userRoles);
		LOG.info("User {} created.", basicUser.getUsername());
	}
}
