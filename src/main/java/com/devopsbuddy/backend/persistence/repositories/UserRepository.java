package com.devopsbuddy.backend.persistence.repositories;

import com.devopsbuddy.backend.persistence.domains.backend.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Jnwanya on
 * Wed, 27 Dec, 2017.
 */
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Long>{

    /**
     * Returns a user given a username or null if not found.
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * Returns a user given an email or null if not found.
     * @param email
     * @return user
     */
    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User u set u.password = :password where u.id = :userId")
    void updateUserPassword(@Param("userId") long userId, @Param("password") String password);
}
