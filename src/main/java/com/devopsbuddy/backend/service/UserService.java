package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domains.backend.PasswordResetToken;
import com.devopsbuddy.backend.persistence.domains.backend.Plan;
import com.devopsbuddy.backend.persistence.domains.backend.User;
import com.devopsbuddy.backend.persistence.domains.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PasswordResetTokenRepository;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlansEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public User createUser(User user, PlansEnum plansEnum, Set<UserRole> userRoles) {

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        Plan plan = new Plan(plansEnum);
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

    /**
     * Returns a user by username or null if a user could not be found.
     * @param username The username to be found
     * @return A user by username or null if a user could not be found.
     */
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Returns a user for the given email or null if a user could not be found.
     * @param email The email associated to the user to find.
     * @return a user for the given email or null if a user could not be found.
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void updateUserPassword(long userId, String password) {
        password = passwordEncoder.encode(password);
        userRepository.updateUserPassword(userId, password);
        LOG.debug("Password updated successfully for user id {} ", userId);

        Set<PasswordResetToken> resetTokens = passwordResetTokenRepository.findAllByUserId(userId);
        if (!resetTokens.isEmpty()) {
            passwordResetTokenRepository.delete(resetTokens);
        }
    }
}
